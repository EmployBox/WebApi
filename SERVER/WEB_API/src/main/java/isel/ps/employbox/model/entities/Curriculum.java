package isel.ps.employbox.model.entities;

import com.github.jayield.rapper.ColumnName;
import com.github.jayield.rapper.DomainObject;
import com.github.jayield.rapper.Id;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class Curriculum implements DomainObject<Long> {

    @Id(isIdentity = true)
    private long curriculumId;

    private final long accountId;
    private final String title;
    private final long version;

    @ColumnName(foreignName = "curriculumId" )
    private final CompletableFuture<List<PreviousJobs>> previousJobs;

    @ColumnName(foreignName = "curriculumId" )
    private final CompletableFuture<List<AcademicBackground>> academicBackground;

    @ColumnName(foreignName = "curriculumId" )
    private final CompletableFuture<List<Project>> projects;

    @ColumnName(foreignName = "curriculumId" )
    private final CompletableFuture<List<CurriculumExperience>> experiences;

    public Curriculum(){
        accountId = 0;
        title = null;
        version = 0;
        previousJobs = null;
        academicBackground = null;
        projects = null;
        experiences = null;
    }

    public Curriculum(long userId,
                      long curriculumId,
                      String title,
                      long version,
                      CompletableFuture<List<PreviousJobs>> previousJobs,
                      CompletableFuture<List<AcademicBackground>> academicBackground,
                      CompletableFuture<List<Project>> projects,
                      CompletableFuture<List<CurriculumExperience>> experiences)
    {
        this.accountId = userId;
        this.curriculumId = curriculumId;
        this.title = title;
        this.version = version;
        this.previousJobs =  previousJobs;
        this.academicBackground = academicBackground;
        this.projects = projects;
        this.experiences = experiences;
    }

    public Curriculum(long accountId, long curriculumId, String title) {
        this.accountId = accountId;
        this.curriculumId = curriculumId;
        this.title= title;
        this.previousJobs = null;
        this.academicBackground = null;
        this.projects = null;
        this.experiences = null;
        this.version = -1;
    }

    @Override
    public Long getIdentityKey() {
        return curriculumId;
    }

    public long getVersion() {
        return version;
    }

    public long getAccountId() {
        return accountId;
    }

    public String getTitle() {
        return title;
    }

    public CompletableFuture<List<PreviousJobs>> getPreviousJobs() {
        return previousJobs;
    }

    public CompletableFuture<List<AcademicBackground>> getAcademicBackground() {
        return academicBackground;
    }

    public CompletableFuture<List<Project>> getProjects() {
        return projects;
    }

    public CompletableFuture<List<CurriculumExperience>> getExperiences() {
        return experiences;
    }
}
