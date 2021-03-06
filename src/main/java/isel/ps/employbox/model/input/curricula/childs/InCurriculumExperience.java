package isel.ps.employbox.model.input.curricula.childs;

public class InCurriculumExperience {

    private long curriculumExperienceId;
    private long accountId;
    private long curriculumId;
    private String competence;
    private int years;
    private long version;

    public long getCurriculumExperienceId() {
        return curriculumExperienceId;
    }

    public void setCurriculumExperienceId(long curriculumExperienceId) {
        this.curriculumExperienceId = curriculumExperienceId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getCurriculumId() {
        return curriculumId;
    }

    public void setCurriculumId(long curriculumId) {
        this.curriculumId = curriculumId;
    }

    public String getCompetence() {
        return competence;
    }

    public void setCompetence(String competence) {
        this.competence = competence;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }
}
