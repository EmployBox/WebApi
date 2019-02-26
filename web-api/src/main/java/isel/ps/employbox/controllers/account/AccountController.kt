package isel.ps.employbox.controllers.account

import isel.ps.employbox.model.binders.AccountBinder
import isel.ps.employbox.model.entities.Account
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutAccount
import isel.ps.employbox.services.AccountService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

@RestController
@RequestMapping("/accounts")
class AccountController(private val accountBinder: AccountBinder, private val accountService: AccountService) {

    @GetMapping
    fun getAccounts(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ): Mono<HalCollectionPage<Account>> {
        return Mono.fromFuture(accountService.getAllAccounts(page, pageSize, orderColumn, orderClause).thenCompose({ res -> accountBinder.bindOutput(res, AccountController::class.java) }))
    }

    @GetMapping("/{accountId}")
    fun getAccount(@PathVariable accountId: Long): Mono<OutAccount> {
        return Mono.fromFuture(accountService.getAccount(accountId)
                .thenCompose(???({ accountBinder.bindOutput() })))
    }

    @GetMapping("/self")
    fun getAccount(authentication: Authentication): Mono<OutAccount> {
        val principal = authentication.principal as Account
        val future = accountService.getAccount(principal.getEmail())
                .thenCompose(???({ accountBinder.bindOutput() }))
        return Mono.fromFuture<OutAccount>(future)
    }
}
