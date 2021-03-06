package isel.ps.employbox.model.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import isel.ps.employbox.controllers.account.AccountController;
import isel.ps.employbox.controllers.CompanyController;
import isel.ps.employbox.controllers.jobs.JobController;
import isel.ps.employbox.controllers.UserAccountControllers.UserAccountController;

import static isel.ps.employbox.model.output.OutputDto.HOSTNAME;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class RootPage{
    @JsonProperty
    private final Self self;
    @JsonProperty
    private final AccountsCollectionLink accounts;
    @JsonProperty
    private final CompanyCollectionLink companies;
    @JsonProperty
    private final UsersCollectionLinks users;
    @JsonProperty
    private final JobsCollectionLinks jobs;
    @JsonProperty
    private final AccountLoginLink login;

    @JsonCreator
    public RootPage() {
        this.self = new Self();
        this.accounts = new AccountsCollectionLink();
        this.companies = new CompanyCollectionLink();
        this.users = new UsersCollectionLinks();
        this.jobs = new JobsCollectionLinks();
        this.login = new AccountLoginLink();
    }

    class Self{
        @JsonProperty
        final String href = HOSTNAME;
    }

    class AccountLoginLink {
        @JsonProperty
        private final _Links _links = new _Links();

        private class _Links {
            @JsonProperty
            private Self self = new Self();

            private class Self {
                @JsonProperty
                final String href = HOSTNAME + linkTo(AccountController.class).slash("self").withSelfRel().getHref();
            }
        }
    }

    class AccountsCollectionLink {
        @JsonProperty
        private final _Links _links = new _Links();

        private class _Links {
            @JsonProperty
            private Self self = new Self();

            private class Self {
                @JsonProperty
                final String href = HOSTNAME + linkTo(AccountController.class).withSelfRel().getHref();
            }
        }
    }

    class CompanyCollectionLink {
        @JsonProperty
        private final _Links _links = new _Links();

        private class _Links {
            @JsonProperty
            private Self self = new Self();

            private class Self {
                @JsonProperty
                final String href = HOSTNAME + linkTo(CompanyController.class).withSelfRel().getHref();
            }
        }
    }

    class UsersCollectionLinks {
        @JsonProperty
        private final _Links _links = new _Links();

        class _Links {
            @JsonProperty
            private Self self = new Self();

            private class Self {
                @JsonProperty
                final String href = HOSTNAME + linkTo(UserAccountController.class).withSelfRel().getHref();
            }
        }
    }

    class JobsCollectionLinks {
        @JsonProperty
        private final _Links _links = new _Links();

        class _Links {
            @JsonProperty
            private Self self = new Self();

            private class Self {
                @JsonProperty
                final String href = HOSTNAME + linkTo(JobController.class).withSelfRel().getHref();
            }
        }
    }
}
