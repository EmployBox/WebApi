package isel.ps.employbox.controllers.user

import isel.ps.employbox.exceptions.BadRequestException
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*


import isel.ps.employbox.ErrorMessages.BAD_REQUEST_IDS_MISMATCH
import pt.isel.ps.employbox.model.UserAccountModel
import pt.isel.ps.employbox.service.UserAccountService

@RestController
@RequestMapping("/accounts/users")
class UserAccountController(
        private val service: UserAccountService
) {

    @GetMapping
    fun getAllUsers(
            @RequestParam(required = false, defaultValue = "0") page: Int,
            @RequestParam(required = false, defaultValue = "10") pageSize: Int,
            @RequestParam(required = false) name: String?,
            @RequestParam(required = false) summary: String?,
            @RequestParam(required = false) ratingLow: Int?,
            @RequestParam(required = false) ratingHigh: Int?,
            @RequestParam(required = false, defaultValue = "id") orderColumn: String,
            @RequestParam(required = false, defaultValue = "ASC") orderClause: String
    ) = service.findAllBy(name, summary, ratingLow, ratingHigh, orderColumn, orderClause, page, pageSize)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long) = service.retrieve(id)


    @PostMapping
    fun createUser(@RequestBody inUserAccount: UserAccountModel) = service.save(inUserAccount)

    @PutMapping("/{id}")
    fun updateUser(
            @PathVariable id: Long,
            @RequestBody inUserAccount: UserAccountModel,
            authentication: Authentication
    ) =
            if(inUserAccount.id != id)
                throw BadRequestException(BAD_REQUEST_IDS_MISMATCH)
            else service.save(inUserAccount)


    @DeleteMapping("/{id}")
    fun deleteUser(@PathVariable id: Long, authentication: Authentication) = service.remove(id)

}