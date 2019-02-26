package isel.ps.employbox.controllers.account

import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.model.AccountModel
import pt.isel.ps.employbox.service.AccountService

@RestController
@RequestMapping("/accounts")
class AccountController(
        private val service: AccountService
) {

    @GetMapping
    fun getAccounts(
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) =
            service.getByParams(page, pageSize, orderColumn, orderClause)

    @GetMapping("/{accountId}")
    fun getAccount(@PathVariable accountId: Long) =
            service.retrieve(accountId)

    @GetMapping("/self")
    fun getAccount(authentication: Authentication) =
            service.getByEmail((authentication.principal as AccountModel).email!!)
}
