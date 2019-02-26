package isel.ps.employbox.controllers.account.follows

import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.service.AccountService


@RestController
@RequestMapping("/accounts/{accountId}/followed")
class FollowedController(
        private val service: AccountService
) {

    @GetMapping
    fun getTheAccountsWichThisAccountIsFollowed(
            @PathVariable accountId: Long,
            @RequestParam(defaultValue = "0") page: Int,
            @RequestParam(defaultValue = "10") pageSize: Int,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String,
            @RequestParam(required = false) accountToCheck: Long?
    ) =
            if(accountToCheck == null)
                service.getFolloweds(accountId, page, pageSize, orderColumn, orderClause)
            else
                service.checkIfFollowing(accountId, accountToCheck)

//    @PutMapping
//    fun addNewFollowRelation(
//            @PathVariable accountId: Long,
//            authentication: Authentication): Mono<Void> {
//        return followService.followNewAccount(accountId, authentication.name)
//    }
//
//    @DeleteMapping
//    fun deleteAFollowRelation(
//            @PathVariable accountId: Long,
//            authentication: Authentication): Mono<Void> {
//        return followService.deleteFollower(accountId, authentication.name)
//    }
}
