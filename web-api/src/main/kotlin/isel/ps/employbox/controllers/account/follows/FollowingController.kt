package isel.ps.employbox.controllers.account.follows

import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.service.AccountService


@RestController
@RequestMapping("/accounts/{accountId}/following")
class FollowingController(
        private val service: AccountService
) {

    @GetMapping
    fun getTheAccountsWichThisAccountIsFollower(
            @PathVariable accountId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String,
            @RequestParam(required = false) accountToCheck: Long?
    ) = service.getFollowers(accountId, page, pageSize, orderColumn, orderClause)
}
