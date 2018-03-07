package dataMapping.mappers;

import dataMapping.exceptions.DataMapperException;
import dataMapping.utils.MapperSettings;
import javafx.util.Pair;
import model.*;
import util.Streamable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static dataMapping.utils.MapperRegistry.getMapper;

public class CurriculumMapper extends AbstractMapper<Curriculum, String>{
    private static final String SELECT_QUERY = "SELECT userId, curriculumId, title, [version] FROM Curriculum";
    private static final String INSERT_QUERY = "INSERT INTO Curriculum (userId, CurriculumId) VALUES (?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM Curriculum WHERE AccountId = ? AND CurriculumId = ? AND [version] = ?";

    public CurriculumMapper() {
        super(
                new MapperSettings<>(INSERT_QUERY, PreparedStatement.class, CurriculumMapper::prepareInsertStatement),
                null,
                new MapperSettings<>(DELETE_QUERY, PreparedStatement.class, CurriculumMapper::prepareDeleteStatement)
        );
    }

    public Streamable<Curriculum> findCurriculumsForAccount(long accountId){
        return findWhere(new Pair<>("userId", accountId));
    }

    @Override
    Curriculum mapper(ResultSet rs) throws DataMapperException {
        try{
            long accountId = rs.getLong("userId");
            long curriculumId = rs.getLong("curriculumId");
            String title = rs.getString("title");
            long version = rs.getLong("[version]");

            Streamable<PreviousJobs> previousJobs = ((PreviousJobsMapper) getMapper(PreviousJobs.class)).findForUserAndCurriculum(accountId, curriculumId);
            Streamable<AcademicBackground> academicBackground = ((AcademicBackgroundMapper) getMapper(AcademicBackground.class)).findForUserAndCurriculum(accountId, curriculumId);
            Streamable<Project> project = ((ProjectMapper) getMapper(Project.class)).findForUserAndCurriculum(accountId, curriculumId);
            Streamable<CurriculumExperience> curriculumExperiences = ((CurriculumExperienceMapper) getMapper(JobExperience.class)).findExperiences(accountId, curriculumId);

            Curriculum curriculum = Curriculum.load(accountId, curriculumId, title, version, previousJobs, academicBackground, project, curriculumExperiences);
            identityMap.put(curriculum.getIdentityKey(), curriculum);

            return curriculum;
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    @Override
    String getSelectQuery() {
        return SELECT_QUERY;
    }

    private static void prepareInsertStatement(PreparedStatement statement, Curriculum obj){
        try{
            statement.setLong(1, obj.getAccountId());
            statement.setLong(2, obj.getCurriculumId());
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static void prepareDeleteStatement(PreparedStatement statement, Curriculum obj){
        try{
            statement.setLong(1, obj.getAccountId());
            statement.setLong(2, obj.getCurriculumId());
            statement.setLong(3, obj.getVersion());
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }
}