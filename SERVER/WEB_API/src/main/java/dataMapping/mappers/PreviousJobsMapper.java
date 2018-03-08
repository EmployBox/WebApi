package dataMapping.mappers;

import dataMapping.exceptions.DataMapperException;
import dataMapping.utils.MapperSettings;
import javafx.util.Pair;
import model.PreviousJobs;
import util.Streamable;

import java.sql.*;

public class PreviousJobsMapper extends AbstractMapper<PreviousJobs, String> {
    public Streamable<PreviousJobs> findForUserAndCurriculum(long accountId, long curriculumId){
        return findWhere(new Pair<>("userId", accountId), new Pair<>("curriculumId", curriculumId));
    }

    public PreviousJobsMapper(){
        super(
                PreviousJobs.class,
                PreparedStatement.class,
                PreviousJobsMapper::prepareInsertStatement,
                PreviousJobsMapper::prepareUpdateStatement,
                PreviousJobsMapper::prepareDeleteStatement
        );
    }

    @Override
    PreviousJobs mapper(ResultSet rs) throws DataMapperException {
        try{
            long accountId = rs.getLong(1);
            long curriculumId = rs.getLong(2);
            Date beginDate = rs.getDate(3);
            Date endDate = rs.getDate(4);
            String companyName = rs.getString(5);
            String workload = rs.getString(6);
            String role = rs.getString(7);
            long version = rs.getLong(8);

            PreviousJobs previousJobs = PreviousJobs.load(accountId, curriculumId, beginDate, endDate, companyName, workload, role, version);
            identityMap.put(String.format("%d %d", accountId, curriculumId), previousJobs);

            return previousJobs;
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static void prepareInsertStatement (PreparedStatement statement, PreviousJobs obj) {
        try{
            statement.setLong(1, obj.getUserId());
            statement.setLong(2, obj.getCurriculumId());
            statement.setDate(3, obj.getBeginDate());
            statement.setDate(4, obj.getEndDate());
            statement.setString(5, obj.getCompanyName());
            statement.setString(6, obj.getWorkLoad());
            statement.setString(7, obj.getRole());
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static void prepareUpdateStatement(PreparedStatement statement, PreviousJobs obj) {
        try{
            statement.setDate(1, obj.getBeginDate());
            statement.setDate(2, obj.getEndDate());
            statement.setString(3, obj.getCompanyName());
            statement.setString(4, obj.getWorkLoad());
            statement.setString(5, obj.getRole());
            statement.setLong(6, obj.getUserId());
            statement.setLong(7, obj.getCurriculumId());
            statement.setLong(8, obj.getVersion());
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static void prepareDeleteStatement (PreparedStatement statement, PreviousJobs obj) {
        try{
            statement.setLong(1, obj.getUserId());
            statement.setLong(2, obj.getCurriculumId());
            statement.setLong(3, obj.getVersion());
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }
}