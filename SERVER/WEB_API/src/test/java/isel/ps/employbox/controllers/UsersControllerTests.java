package isel.ps.employbox.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jayield.rapper.DataRepository;
import com.github.jayield.rapper.utils.ConnectionManager;
import isel.ps.employbox.EmployBoxAPIApp;
import isel.ps.employbox.model.binder.UserBinder;
import isel.ps.employbox.model.entities.UserAccount;
import isel.ps.employbox.model.input.InUser;
import isel.ps.employbox.security.AuthFilter;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.restdocs.JUnitRestDocumentation;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.SQLException;

import static junit.framework.TestCase.assertTrue;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {EmployBoxAPIApp.class, AuthFilter.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerTests {

    @Rule
    public JUnitRestDocumentation restDocumentation = new JUnitRestDocumentation();

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private UserBinder userBinder;

    @Autowired
    private DataRepository<UserAccount, Long> userRepo;

    private MockMvc mockMvc;
    //private WebTestClient webTestClient;
    private Connection con;
    private long userAccountId;

    @Before
    public void setUp() throws SQLException {
        ConnectionManager connectionManager = ConnectionManager.getConnectionManager(
                "jdbc:hsqldb:file:" + URLDecoder.decode(this.getClass().getClassLoader().getResource("testdb").getPath()) + "/testdb",
                "SA", "");
        con = connectionManager.getConnection();

        con.prepareCall("{call deleteDB()}").execute();
        con.prepareCall("{call populateDB()}").execute();

        con.commit();

        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .apply(documentationConfiguration(restDocumentation))
                //.alwaysDo(document("{method-name}", preprocessRequest(prettyPrint()), preprocessResponse(prettyPrint())))
                .build();

        /*webTestClient = WebTestClient.bindToController(UserController.class)
                .configureClient()
                .filter(documentationConfiguration(restDocumentation))
                .build();*/

        InUser user = new InUser();
        user.setEmail("teste@gmail.com");
        user.setPassword("password");
        user.setName("Bruno");
        UserAccount userAccount = userBinder.bindInput(user);

        assertTrue(userRepo.create(userAccount).join());

        user.setEmail("lol@hotmail.com");
        user.setPassword("teste123");
        user.setName("Maria");
        userAccount = userBinder.bindInput(user);

        assertTrue(userRepo.create(userAccount).join());
        userAccountId = userAccount.getIdentityKey();

        con.commit();
    }

    @After
    public void after() throws SQLException {
        con.close();
    }

    @Test
    public void testCreateUser() throws Exception {
        InUser user = new InUser();
        user.setEmail("someEmail@hotmail.com");
        user.setName("Manuel");
        user.setPassword("1234");
        user.setSummary("Sou um tipo simpatico");

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        mockMvc.perform(
                post("/accounts/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json)
        )
                .andExpect(status().isOk())
                .andDo(document("createUser"));

        /*webTestClient
                .post()
                .uri("/accounts/users")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .consumeWith(document("createUser"));*/

        assertTrue(userRepo.findWhere(new Pair<>("email", "someEmail@hotmail.com")).join().size() != 0);
    }

    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(
                get("/accounts/users")
                        .with(httpBasic("teste@gmail.com", "password"))
        )
                .andExpect(status().isOk())
                .andDo(document("getAllUsers"));
    }

    @Test
    public void testGetUser() throws Exception {
        mockMvc.perform(
                get("/accounts/users/" + userAccountId)
                        .with(httpBasic("teste@gmail.com", "password"))
        )
                .andExpect(status().isOk())
                .andDo(document("getUser"));
    }
}
