package isel.ps.employbox.controllers.account.follows

import isel.ps.employbox.model.binders.AccountBinder
import isel.ps.employbox.model.binders.CollectionPage
import isel.ps.employbox.model.entities.Account
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.services.FollowService
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/accounts/{accountId}/following")
class FollowingController(private val accountBinder: AccountBinder, private val followService: FollowService) {

    @GetMapping
    fun getTheAccountsWichThisAccountIsFollower(
            @PathVariable accountId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String,
            @RequestParam(required = false) accountToCheck: Long?
    ): Mono<HalCollectionPage<Account>> {
        val future: CompletableFuture<CollectionPage<Account>>
        if (accountToCheck == null)
            future = followService.getAccountFollowers(accountId, page, pageSize, orderColumn, orderClause)
        else
            future = followService.checkIfAccountIsFollowerOf(accountId, accountToCheck)

        return Mono.fromFuture<HalCollectionPage<Account>>(future.thenCompose<Any> { accountCollectionPage -> accountBinder.bindOutput(accountCollectionPage, this.javaClass, accountId) })
    }
}
