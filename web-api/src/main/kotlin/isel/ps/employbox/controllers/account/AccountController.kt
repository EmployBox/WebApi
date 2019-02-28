package isel.ps.employbox.controllers.account

import com.google.code.siren4j.converter.ReflectingConverter
import com.google.code.siren4j.resource.CollectionResource
import isel.ps.employbox.resources.AccountResource
import org.modelmapper.ModelMapper
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import pt.isel.ps.employbox.model.AccountModel
import pt.isel.ps.employbox.service.AccountService

@RestController
@RequestMapping("/accounts")
class AccountController(
        private val service: AccountService,
        private val mapper : ModelMapper
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
            mapper.map(service.retrieve(accountId), AccountResource::class.java)
                    .let { ReflectingConverter.newInstance().toEntity(it) }

    @GetMapping("/self")
    fun getAccount(authentication: Authentication) =
            service.getByEmail((authentication.principal as AccountModel).email!!)
}
