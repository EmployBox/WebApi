package isel.ps.employbox.services;

import com.github.jayield.rapper.DataRepository;
import com.github.jayield.rapper.utils.UnitOfWork;
import isel.ps.employbox.ErrorMessages;
import isel.ps.employbox.exceptions.ResourceNotFoundException;
import isel.ps.employbox.model.binders.CollectionPage;
import isel.ps.employbox.model.entities.Company;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@Service
public class CompanyService {
    private DataRepository<Company, Long> companyRepo;
    private AccountService accountService;

    public CompanyService(DataRepository<Company, Long> companyRepo, AccountService accountService){
        this.companyRepo = companyRepo;
        this.accountService = accountService;
    }

    public CompletableFuture<CollectionPage<Company>> getCompanies(int page, int pageSize) {
        return ServiceUtils.getCollectionPageFuture(companyRepo, page, pageSize);
    }

    public CompletableFuture<Company> getCompany(long cid) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return companyRepo.findById(unitOfWork, cid)
                .thenCompose( res -> unitOfWork.commit().thenApply( aVoid -> res))
                .thenApply(optionalCompany -> optionalCompany.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.RESOURCE_NOTFOUND_COMPANY)));
    }

    public CompletableFuture<Company> createCompany(Company company) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return companyRepo.create(unitOfWork, company)
                .thenCompose( res -> unitOfWork.commit().thenApply( aVoid -> company));
    }

    public Mono<Void> updateCompany(Company company, String email) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return Mono.fromFuture(
                accountService.getAccount(company.getIdentityKey(), email)
                        .thenCompose(account -> companyRepo.update(unitOfWork, company))
                        .thenCompose( res -> unitOfWork.commit())
        );
    }

    public Mono<Void> deleteCompany(long cid, String email) {
        UnitOfWork unitOfWork = new UnitOfWork();
        return Mono.fromFuture(
                accountService.getAccount(cid, email)
                        .thenCompose(account -> companyRepo.deleteById(unitOfWork, cid))
                        .thenCompose( res -> unitOfWork.commit())
        );
    }
}
