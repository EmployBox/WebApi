package isel.ps.employbox.controllers.account.FollowsControllers;

import isel.ps.employbox.model.binders.AccountBinder;
import isel.ps.employbox.model.binders.CollectionPage;
import isel.ps.employbox.model.entities.Account;
import isel.ps.employbox.model.output.Collections.HalCollectionPage;
import isel.ps.employbox.services.FollowService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/accounts/{accountId}/following")
public class FollowingController {

    private final AccountBinder accountBinder;
    private final FollowService followService;

    public FollowingController(AccountBinder accountBinder, FollowService followService) {
        this.accountBinder = accountBinder;
        this.followService = followService;
    }

    @GetMapping
    public Mono<HalCollectionPage<Account>> getTheAccountsWichThisAccountIsFollower(
            @PathVariable long accountId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(required = false) String orderColumn,
            @RequestParam(required = false, defaultValue = "ASC") String orderClause,
            @RequestParam(required = false) Long accountToCheck
    ){
        CompletableFuture<CollectionPage<Account>> future;
                if(accountToCheck==null)
                    future = followService.getAccountFollowers(accountId, page, pageSize, orderColumn, orderClause);
                else
                    future = followService.checkIfAccountIsFollowerOf(accountId, accountToCheck);

        return Mono.fromFuture(future.thenCompose(accountCollectionPage -> accountBinder.bindOutput(accountCollectionPage, this.getClass(), accountId)));
    }
}
