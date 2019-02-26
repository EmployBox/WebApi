package isel.ps.employbox

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonProperty
import isel.ps.employbox.controllers.CompanyController
import isel.ps.employbox.controllers.account.AccountController
import isel.ps.employbox.controllers.jobs.JobController
import isel.ps.employbox.controllers.user.UserAccountController
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo


/**
 * @author tiago.ribeiro
 */
class RootPage @JsonCreator
constructor() {
    @JsonProperty
    private val self: Self
    @JsonProperty
    private val accounts: AccountsCollectionLink
    @JsonProperty
    private val companies: CompanyCollectionLink
    @JsonProperty
    private val users: UsersCollectionLinks
    @JsonProperty
    private val jobs: JobsCollectionLinks
    @JsonProperty
    private val login: AccountLoginLink

    val HOSTNAME = "http://localhost:8080"


    init {
        this.self = Self()
        this.accounts = AccountsCollectionLink()
        this.companies = CompanyCollectionLink()
        this.users = UsersCollectionLinks()
        this.jobs = JobsCollectionLinks()
        this.login = AccountLoginLink()
    }

    internal inner class Self {
        @JsonProperty
        val href = HOSTNAME
    }

    internal inner class AccountLoginLink {
        @JsonProperty
        private val _links = _Links()

        private inner class _Links {
            @JsonProperty
            private val self = Self()

            private inner class Self {
                @JsonProperty
                internal val href = HOSTNAME + ControllerLinkBuilder.linkTo(AccountController::class.java).slash("self").withSelfRel().getHref()
            }
        }
    }

    internal inner class AccountsCollectionLink {
        @JsonProperty
        private val _links = _Links()

        private inner class _Links {
            @JsonProperty
            private val self = Self()

            private inner class Self {
                @JsonProperty
                internal val href = HOSTNAME + linkTo(AccountController::class.java).withSelfRel().getHref()
            }
        }
    }

    internal inner class CompanyCollectionLink {
        @JsonProperty
        private val _links = _Links()

        private inner class _Links {
            @JsonProperty
            private val self = Self()

            private inner class Self {
                @JsonProperty
                internal val href = HOSTNAME + linkTo(CompanyController::class.java).withSelfRel().getHref()
            }
        }
    }

    internal inner class UsersCollectionLinks {
        @JsonProperty
        private val _links = _Links()

        internal inner class _Links {
            @JsonProperty
            private val self = Self()

            private inner class Self {
                @JsonProperty
                internal val href = HOSTNAME + linkTo(UserAccountController::class.java).withSelfRel().getHref()
            }
        }
    }

    internal inner class JobsCollectionLinks {
        @JsonProperty
        private val _links = _Links()

        internal inner class _Links {
            @JsonProperty
            private val self = Self()

            private inner class Self {
                @JsonProperty
                internal val href = HOSTNAME + linkTo(JobController::class.java).withSelfRel().getHref()
            }
        }
    }
}
