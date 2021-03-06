package isel.ps.employbox.model.entities.curricula.childs;

import com.github.jayield.rapper.DomainObject;
import com.github.jayield.rapper.annotations.Id;
import com.github.jayield.rapper.annotations.Version;

import java.sql.Timestamp;
import java.time.Instant;

public class AcademicBackground extends CurriculumChild implements DomainObject<Long> {
    @Id(isIdentity = true)
    private final long academicBackgroundKey;
    private long accountId;
    private long curriculumId;
    private final Instant beginDate;
    private final Instant endDate;
    private final String studyArea;
    private final String institution;
    private final String degreeObtained;
    @Version
    private final long version;

    public AcademicBackground(){
        accountId = 0;
        curriculumId = 0;
        beginDate = null;
        endDate = null;
        studyArea = null;
        institution = null;
        degreeObtained = null;
        version = 0;
        academicBackgroundKey = 0;
    }

    public AcademicBackground(
            long userId,
            long curriculumId,
            Timestamp beginDate,
            Timestamp endDate,
            String studyArea,
            String institution,
            String degreeObtained,
            long version,
            long academicBackgroundKey) {
        this.accountId = userId;
        this.curriculumId = curriculumId;
        if(beginDate != null)
            this.beginDate = beginDate.toInstant();
        else
            this.beginDate = null;
        if(endDate != null)
            this.endDate = endDate.toInstant();
        else
            this.endDate = null;

        this.studyArea = studyArea;
        this.institution = institution;
        this.degreeObtained = degreeObtained;
        this.version = version;
        this.academicBackgroundKey = academicBackgroundKey;
    }

    @Override
    public Long getIdentityKey() {
        return academicBackgroundKey;
    }

    public long getVersion() {
        return version;
    }

    public long getAccountId() {
        return accountId;
    }

    public long getCurriculumId() {
        return curriculumId;
    }

    public Instant getBeginDate() {
        return beginDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public String getStudyArea() {
        return studyArea;
    }

    public String getDegreeObtained() {
        return degreeObtained;
    }

    public String getInstitution() {
        return institution;
    }

    public void setCurriculumId(long curriculumId){
        this.curriculumId = curriculumId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }
}
