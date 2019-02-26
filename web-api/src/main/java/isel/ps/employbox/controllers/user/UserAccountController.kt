package isel.ps.employbox.controllers.UserAccountControllers

import isel.ps.employbox.exceptions.BadRequestException
import isel.ps.employbox.model.binders.UserBinder
import isel.ps.employbox.model.entities.UserAccount
import isel.ps.employbox.model.input.InUserAccount
import isel.ps.employbox.model.output.Collections.HalCollectionPage
import isel.ps.employbox.model.output.OutUser
import isel.ps.employbox.services.UserAccountService
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Mono

import java.util.concurrent.CompletableFuture

import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.service.UserAccountService

@RestController
@RequestMapping("/accounts/users")
class UserAccountController(
        private val service: UserAccountService
) {

    @GetMapping
    fun getAllUsers(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) name: String,
            @RequestParam(required = false) summary: String,
            @RequestParam(required = false) ratingLow: Int?,
            @RequestParam(required = false) ratingHigh: Int?,
            @RequestParam(required = false) orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ): Mono<HalCollectionPage<UserAccount>> {
        val future = userAccountService.getAllUsers(page, pageSize, name, summary, ratingLow, ratingHigh, orderColumn, orderClause)
                .thenCompose({ userAccountCollectionPage -> userBinder.bindOutput(userAccountCollectionPage, this.javaClass) })
        return Mono.fromFuture<HalCollectionPage<UserAccount>>(future)
    }

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): Mono<OutUser> {
        val future = userAccountService.getUser(id)
                .thenCompose(???({ userBinder.bindOutput() }))
        return Mono.fromFuture<OutUser>(future)
    }


    @PostMapping
    fun createUser(@RequestBody inUserAccount: InUserAccount): Mono<OutUser> {
        val userAccount = userBinder.bindInput(inUserAccount)
        val future = userAccountService.createUser(userAccount)
                .thenCompose(???({ userBinder.bindOutput() }))
        return Mono.fromFuture<OutUser>(future)
    }

    @PutMapping("/{id}")
    fun updateUser(
            @PathVariable id: Long,
            @RequestBody inUserAccount: InUserAccount,
            authentication: Authentication
    ): Mono<Void> {
        if (inUserAccount.id != id) throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
        val userAccount = userBinder.bindInput(inUserAccount)
        return userAccountService.updateUser(userAccount, authentication.name)
    }


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long, authentication: Authentication): Mono<Void> {
        return userAccountService.deleteUser(id, authentication.name)
    }

}