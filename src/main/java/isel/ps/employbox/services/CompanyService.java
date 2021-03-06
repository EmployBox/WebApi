package isel.ps.employbox.services;

import com.github.jayield.rapper.mapper.DataMapper;
import com.github.jayield.rapper.mapper.conditions.Condition;
import com.github.jayield.rapper.mapper.conditions.EqualAndCondition;
import com.github.jayield.rapper.mapper.conditions.EqualOrCondition;
import com.github.jayield.rapper.mapper.conditions.LikeCondition;
import com.github.jayield.rapper.unitofwork.UnitOfWork;
import isel.ps.employbox.ErrorMessages;
import isel.ps.employbox.exceptions.ResourceNotFoundException;
import isel.ps.employbox.model.binders.CollectionPage;
import isel.ps.employbox.model.entities.Account;
import isel.ps.employbox.model.entities.Company;
import isel.ps.employbox.model.entities.Follows;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static com.github.jayield.rapper.mapper.MapperRegistry.getMapper;
import static isel.ps.employbox.services.ServiceUtils.evaluateOrderClauseConditions;
import static isel.ps.employbox.services.ServiceUtils.evaluateRatingConditions;
import static isel.ps.employbox.services.ServiceUtils.handleExceptions;

@Service
public class CompanyService {
    private AccountService accountService;

    public CompanyService( AccountService accountService){
        this.accountService = accountService;
    }

    public CompletableFuture<CollectionPage<Company>> getCompanies(
            int page,
            int pageSize,
            Integer ratingLow,
            Integer ratingHigh,
            String name,
            String specialization,
            Integer yearFounded,
            String address,
            String orderColumn,
            String orderClause
    ) {
        List<Condition> conds = new ArrayList<>();

        evaluateRatingConditions(ratingLow, ratingHigh, conds);
        conds.add(new LikeCondition("name", name));
        conds.add(new LikeCondition("accountType", "CMP"));

        conds.add(new LikeCondition("specialization", specialization));
        conds.add(new LikeCondition("address", address));
        conds.add(new EqualAndCondition<>("yearFounded", yearFounded));

        conds = conds.stream()
                .filter(stringPair -> stringPair.getValue() != null)
                .collect(Collectors.toList());

        evaluateOrderClauseConditions(orderColumn, orderClause, conds);

        return ServiceUtils.getCollectionPageFuture(Company.class, page, pageSize, conds.toArray(new Condition[conds.size()]));
    }

    public CompletableFuture<Company> getCompany(long cid) {
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<Company, Long> companyMapper = getMapper(Company.class, unitOfWork);
        CompletableFuture<Company> future = companyMapper.findById(cid)
                .thenCompose(res -> unitOfWork.commit().thenApply(aVoid -> res))
                .thenApply(optionalCompany -> optionalCompany.orElseThrow(() -> new ResourceNotFoundException(ErrorMessages.RESOURCE_NOTFOUND_COMPANY)));
        return handleExceptions(future, unitOfWork);
    }

    public CompletableFuture<Company> createCompany(Company company) {
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<Company, Long> companyMapper = getMapper(Company.class, unitOfWork);
        CompletableFuture<Company> future = companyMapper.create( company)
                .thenCompose(res -> unitOfWork.commit().thenApply(aVoid -> company));
        return handleExceptions(future, unitOfWork);
    }

    public Mono<Void> updateCompany(Company company, String email) {
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<Company, Long> companyMapper = getMapper(Company.class, unitOfWork);
        CompletableFuture<Void> future = accountService.getAccount(company.getIdentityKey(), email)
                .thenCompose(account -> companyMapper.update( company))
                .thenCompose(res -> unitOfWork.commit());
        return Mono.fromFuture(
                handleExceptions(future, unitOfWork)
        );
    }

    public Mono<Void> deleteCompany(long cid, String email) {
        UnitOfWork unitOfWork = new UnitOfWork();
        DataMapper<Company, Long> companyMapper = getMapper(Company.class, unitOfWork);
        DataMapper<Follows, Follows.FollowKey> followsMapper = getMapper(Follows.class, unitOfWork);

        CompletableFuture<List<Follows>> [] follows  = new CompletableFuture[2];

        CompletableFuture<Void> future = accountService.getAccount(cid, email)
                .thenCompose( account -> {
                            follows[0] = followsMapper.find(new EqualAndCondition<Long>("accountIdFollower", account.getIdentityKey()));
                            follows[1] = followsMapper.find(new EqualAndCondition<Long>("accountIdFollowed", account.getIdentityKey()));

                            return CompletableFuture.allOf(follows);
                        }
                )
                .thenCompose(
                        aVoid -> {
                            Set<Follows.FollowKey> list = new HashSet<>();
                            list.addAll(follows[0].join().stream().map(curr -> curr.getIdentityKey()).collect(Collectors.toList()));
                            list.addAll(follows[1].join().stream().map(curr -> curr.getIdentityKey()).collect(Collectors.toList()));
                            return followsMapper.deleteAll(list);
                        })
                .thenCompose(aVoid -> companyMapper.deleteById(cid))
                .thenCompose(res -> unitOfWork.commit());
        return Mono.fromFuture(
                handleExceptions(future, unitOfWork)
        );
    }
}
