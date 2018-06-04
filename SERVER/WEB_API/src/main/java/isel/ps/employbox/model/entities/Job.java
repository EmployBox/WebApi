package isel.ps.employbox.model.entities;

import com.github.jayield.rapper.ColumnName;
import com.github.jayield.rapper.DomainObject;
import com.github.jayield.rapper.Id;
import com.github.jayield.rapper.Version;

import java.sql.Timestamp;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Job implements DomainObject<Long> {
    @Id(isIdentity =  true)
    private long jobId;
    private final String title;
    @ColumnName(name = "accountId")
    private final CompletableFuture<Account> account;
    private final String address;
    private final int wage;
    private final String description;
    private final String schedule;
    private final Timestamp offerBeginDate;
    private final Timestamp offerEndDate;
    private final String offerType;
    @Version
    private final long version;
    @ColumnName(foreignName = "jobId")
    private final CompletableFuture<List<Application>> applications;
    @ColumnName(foreignName = "jobId")
    private final CompletableFuture<List<JobExperience>> experiences;

    public Job(){
        title = null;
        account = null;
        address = null;
        wage = 0;
        description = null;
        schedule = null;
        offerBeginDate = null;
        offerEndDate = null;
        offerType = null;
        applications = null;
        experiences = null;
        version = 0;
    }

    public Job(
            long id,
            String title,
            CompletableFuture<Account> accountID,
            String address,
            int wage,
            String description,
            String schedule,
            Timestamp offerBeginDate,
            Timestamp offerEndDate,
            String offerType,
            long version,
            CompletableFuture<List<Application>> applications,
            CompletableFuture<List<JobExperience>> experiences
    ) {
        this.jobId = id;
        this.title = title;
        this.account = accountID;
        this.address = address;
        this.wage = wage;
        this.description = description;
        this.schedule = schedule;
        this.offerBeginDate = offerBeginDate;
        this.offerEndDate = offerEndDate;
        this.offerType = offerType;
        this.version = version;
        this.applications = applications;
        this.experiences = experiences;
    }

    public Job(
            CompletableFuture<Account> account,
            long jobId,
            String title,
            String address,
            int wage,
            String description,
            String schedule,
            Timestamp offerBeginDate,
            Timestamp offerEndDate,
            String offerType,
            List<JobExperience> experiences,
            long version
    ) {
        this.jobId = jobId;
        this.account = account;
        this.title = title;
        this.address = address;
        this.wage = wage;
        this.description = description;
        this.schedule = schedule;
        this.offerBeginDate = offerBeginDate;
        this.offerEndDate = offerEndDate;
        this.offerType = offerType;
        this.applications = CompletableFuture.completedFuture(null);
        this.experiences = CompletableFuture.completedFuture(experiences);
        this.version = version;
    }

    @Override
    public Long getIdentityKey() {
        return jobId;
    }

    public long getVersion() {
        return version;
    }

    public CompletableFuture<Account> getAccount() {
        return account;
    }

    public int getWage() {
        return wage;
    }

    public String getDescription() {
        return description;
    }

    public String getSchedule() {
        return schedule;
    }

    public Timestamp getOfferBeginDate() {
        return offerBeginDate;
    }

    public Timestamp getOfferEndDate() {
        return offerEndDate;
    }

    public String getOfferType() {
        return offerType;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public CompletableFuture<List<Application>> getApplications() {
        return applications;
    }

    public CompletableFuture<List<JobExperience>> getExperiences() {
        return experiences;
    }
}
