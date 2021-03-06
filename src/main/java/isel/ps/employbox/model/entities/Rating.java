package isel.ps.employbox.model.entities;


import com.github.jayield.rapper.DomainObject;
import com.github.jayield.rapper.annotations.EmbeddedId;
import com.github.jayield.rapper.annotations.Version;
import com.github.jayield.rapper.utils.EmbeddedIdClass;

public class Rating implements DomainObject<Rating.RatingKey> {
    @EmbeddedId
    private final RatingKey ratingKey;

    private final double workLoad;
    private final double wage;
    private final double workEnviroment;

    private final double competence;
    private final double ponctuality;
    private final double assiduity;
    private final double demeanor;

    @Version
    private final long version;

    public Rating(){
        ratingKey = null;
        workLoad = 0;
        wage = 0;
        workEnviroment = 0;
        competence = 0;
        ponctuality = 0;
        assiduity = 0;
        demeanor = 0;
        version = 0;
    }

    public Rating(long accountIDFrom,
                  long accountIDTo,
                  double workLoad,
                  double wage,
                  double workEnvironment,
                  double competence,
                  double pontuality,
                  double assiduity,
                  double demeanor,
                  long version
    ) {
        this.workLoad = workLoad;
        this.wage = wage;
        this.workEnviroment = workEnvironment;
        this.competence = competence;
        this.ponctuality = pontuality;
        this.assiduity = assiduity;
        this.demeanor = demeanor;
        this.version = version;
        ratingKey = new RatingKey(accountIDFrom, accountIDTo);
    }

    public long getAccountIdFrom() {
        return this.ratingKey.accountIdFrom;
    }

    public long getAccountIdTo() {
        return this.ratingKey.accountIdTo;
    }

    public double getWorkLoad() {
        return workLoad;
    }

    public double getWage() {
        return wage;
    }

    public double getWorkEnviroment() {
        return workEnviroment;
    }

    public double getCompetence() {
        return competence;
    }

    public double getPonctuality() {
        return ponctuality;
    }

    public double getAssiduity() {
        return assiduity;
    }

    public double getDemeanor() {
        return demeanor;
    }

    @Override
    public RatingKey getIdentityKey() {
        return ratingKey;
    }

    @Override
    public long getVersion() {
        return version;
    }

    public static class RatingKey extends EmbeddedIdClass {
        private final long accountIdFrom;
        private final long accountIdTo;

        public RatingKey(){
            super();
            accountIdFrom = 0;
            accountIdTo = 0;
        }

        public RatingKey(long accountIdFrom, long accountIdTo) {
            super(accountIdFrom, accountIdTo);
            this.accountIdFrom = accountIdFrom;
            this.accountIdTo = accountIdTo;
        }

        public long getAccountIdFrom() {
            return accountIdFrom;
        }

        public long getAccountIdTo() {
            return accountIdTo;
        }
    }
}
