package isel.ps.employbox.controllers.curricula;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jayield.rapper.mapper.DataMapper;
import com.github.jayield.rapper.mapper.conditions.EqualAndCondition;
import com.github.jayield.rapper.unitofwork.UnitOfWork;
import isel.ps.employbox.exceptions.ResourceNotFoundException;
import isel.ps.employbox.model.entities.Curriculum;
import isel.ps.employbox.model.entities.UserAccount;
import isel.ps.employbox.model.entities.curricula.childs.CurriculumExperience;
import isel.ps.employbox.model.input.curricula.childs.InCurriculumExperience;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.List;

import static com.github.jayield.rapper.mapper.MapperRegistry.getMapper;
import static isel.ps.employbox.DataBaseUtils.prepareDB;
import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.document;
import static org.springframework.restdocs.webtestclient.WebTestClientRestDocumentation.documentationConfiguration;
import static org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity;
import static org.springframework.web.reactive.function.client.ExchangeFilterFunctions.basicAuthentication;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CurriculumExperienceControllerTests {
    private static final Logger logger = LoggerFactory.getLogger(AcademicBackgroundControllerTests.class);
    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();
    @Autowired
    private ApplicationContext context;
    private WebTestClient webTestClient;
    private UserAccount userAccount;
    private Curriculum curriculum;
    private CurriculumExperience curriculumExperience;

    @Before
    public void setUp() {
        prepareDB();
        webTestClient = WebTestClient.bindToApplicationContext(context)
                .apply(springSecurity())
                .configureClient()
                .filter(basicAuthentication())
                .filter(documentationConfiguration(restDocumentation))
                .build();
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<UserAccount, Long> userAccountRepo = getMapper(UserAccount.class, unitOfWork);
        List<UserAccount> userAccounts = userAccountRepo.find( new EqualAndCondition<>("name", "Bruno")).join();
        assertEquals(1, userAccounts.size());
        userAccount = userAccounts.get(0);

        DataMapper<Curriculum, Long> curriculumRepo = getMapper(Curriculum.class, unitOfWork);
        List<Curriculum> curricula = curriculumRepo.find( new EqualAndCondition<>("title", "Engenharia Civil")).join();
        assertEquals(1, curricula.size());
        curriculum = curricula.get(0);

        DataMapper<CurriculumExperience, Long> curriculumExperiencesMapper = getMapper(CurriculumExperience.class, unitOfWork);
        List<CurriculumExperience> curriculumExperiences = curriculumExperiencesMapper.find( new EqualAndCondition<>("competence", "Knows to do stuff")).join();
        assertEquals(1, curriculumExperiences.size());
        curriculumExperience = curriculumExperiences.get(0);
        unitOfWork.commit().join();
    }

    @After
    public void after() {
        int openedConnections = UnitOfWork.getNumberOfOpenConnections().get();
        logger.info("OPENED CONNECTIONS - {}", openedConnections);
        assertEquals(0, openedConnections);
    }

    @Test
    public void testGetAllCurriculumExperiences(){
        webTestClient
                .get()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("getAllCurriculumExperiences"));
    }

    @Test
    public void testGetCurriculumExperience(){
        webTestClient
                .get()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences/" + curriculumExperience.getIdentityKey())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("getProject"));
    }

    @Test
    @WithMockUser(username = "teste@gmail.com")
    public void testCreateCurriculumExperience() throws Exception {
        InCurriculumExperience inCurriculumExperience = new InCurriculumExperience();
        inCurriculumExperience.setCurriculumId(curriculum.getIdentityKey());
        inCurriculumExperience.setAccountId(userAccount.getIdentityKey());
        inCurriculumExperience.setCompetence("knows to do everything");
        inCurriculumExperience.setYears(3);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(inCurriculumExperience);
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<CurriculumExperience, Long> curriculumExperienceMapper = getMapper(CurriculumExperience.class,unitOfWork);
        webTestClient
                .post()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences")
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(json)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("createCurriculumExperience"));

        assertEquals(1, curriculumExperienceMapper.find( new EqualAndCondition<>("competence", "knows to do everything")).join().size());
        assertEquals(1, curriculumExperienceMapper.find( new EqualAndCondition<>("years", 3)).join().size());
        unitOfWork.commit().join();
    }

    @Test
    @WithMockUser(username = "company1@gmail.com")
    public void testUpdateWrongCurriculumExperience() throws JsonProcessingException {
        InCurriculumExperience inCurriculumExperience = new InCurriculumExperience();
        inCurriculumExperience.setCurriculumExperienceId(curriculumExperience.getIdentityKey());
        inCurriculumExperience.setCurriculumId(curriculum.getIdentityKey());
        inCurriculumExperience.setAccountId(userAccount.getIdentityKey());
        inCurriculumExperience.setCompetence("knows to do everything");
        inCurriculumExperience.setYears(3);
        inCurriculumExperience.setVersion(curriculumExperience.getVersion());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(inCurriculumExperience);

        webTestClient
                .put()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences/" + curriculumExperience.getIdentityKey())
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(json)
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody()
                .consumeWith(document("updateWrongCurriculumExperience"));
    }

    @Test
    @WithMockUser(username = "teste@gmail.com")
    public void testUpdateCurriculumExperience() throws JsonProcessingException {
        InCurriculumExperience inCurriculumExperience = new InCurriculumExperience();
        inCurriculumExperience.setCurriculumExperienceId(curriculumExperience.getIdentityKey());
        inCurriculumExperience.setCurriculumId(curriculum.getIdentityKey());
        inCurriculumExperience.setAccountId(userAccount.getIdentityKey());
        inCurriculumExperience.setCompetence("knows to do everything");
        inCurriculumExperience.setYears(3);
        inCurriculumExperience.setVersion(curriculumExperience.getVersion());

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(inCurriculumExperience);

        webTestClient
                .put()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences/" + curriculumExperience.getIdentityKey())
                .contentType(MediaType.APPLICATION_JSON)
                .syncBody(json)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("updateWrongAcademicBackground"));

        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<CurriculumExperience, Long> curriculumExperienceMapper = getMapper(CurriculumExperience.class, unitOfWork);
        CurriculumExperience updatedCurriculumExperience = curriculumExperienceMapper.findById(curriculumExperience.getIdentityKey()).join().orElseThrow(() -> new ResourceNotFoundException("Curriculum not found"));
        unitOfWork.commit().join();
        assertEquals("knows to do everything", updatedCurriculumExperience.getCompetence());
    }

    @Test
    @WithMockUser
    public void testDeleteWrongProject() {
        webTestClient
                .delete()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences/" + curriculumExperience.getIdentityKey())
                .exchange()
                .expectStatus().isUnauthorized()
                .expectBody()
                .consumeWith(document("deleteWrongProject"));
    }

    @Test
    @WithMockUser(username = "teste@gmail.com")
    public void testDeleteProject(){
        webTestClient
                .delete()
                .uri("/accounts/users/" + userAccount.getIdentityKey() + "/curricula/" + curriculum.getIdentityKey() + "/experiences/" + curriculumExperience.getIdentityKey())
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("deleteProject"));
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<CurriculumExperience, Long> projectRepo = getMapper(CurriculumExperience.class, unitOfWork);
        assertFalse(projectRepo.findById( curriculumExperience.getIdentityKey()).join().isPresent());
        unitOfWork.commit().join();
    }
}
