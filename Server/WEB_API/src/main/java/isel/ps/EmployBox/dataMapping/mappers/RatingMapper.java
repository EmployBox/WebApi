package isel.ps.EmployBox.dataMapping.mappers;

import isel.ps.EmployBox.dataMapping.exceptions.DataMapperException;
import javafx.util.Pair;
import isel.ps.EmployBox.model.Rating;
import isel.ps.EmployBox.util.Streamable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RatingMapper extends AbstractMapper<Rating, String> {
    public RatingMapper() {
        super(
                Rating.class,
                PreparedStatement.class,
                RatingMapper::prepareInsertStatement,
                RatingMapper::prepareUpdateStatement,
                RatingMapper::prepareDeleteStatement
        );
    }

    public Streamable<Rating> findRatingsForAccount(long accountID) {
        return findWhere(new Pair<>("accountIdFrom", accountID));
    }

    public Streamable<Rating> findModeratedRatingsForModerator(long moderatorID) {
        return findWhere(new Pair<>("moderatorId", moderatorID));
    }

    @Override
    Rating mapper(ResultSet rs) throws DataMapperException {
        try{
            long accountIdFrom = rs.getLong(1);
            long accountIdTo = rs.getLong(2);
            long moderatorId = rs.getLong(3);
            double ratingValue = rs.getDouble(4);
            boolean status = rs.getBoolean(5);
            long version = rs.getLong(6);

            Rating rating = Rating.load(accountIdFrom, accountIdTo, moderatorId, ratingValue, status, version);
            identityMap.put(rating.getIdentityKey(), rating);

            return rating;
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static Rating prepareInsertStatement(PreparedStatement statement, Rating obj){
        try{
            statement.setLong(1, obj.getAccountIdFrom());
            statement.setLong(2, obj.getAccountIdTo());
            statement.setLong(3, obj.getModeratorId());
            statement.setDouble(4, obj.getRatingValue());
            statement.setBoolean(5, obj.isApproved());
            executeUpdate(statement);

            long version = getVersion(statement);

            return new Rating(obj.getAccountIdFrom(), obj.getAccountIdTo(), obj.getModeratorId(), obj.getRatingValue(), obj.isApproved(), version);
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static Rating prepareUpdateStatement(PreparedStatement statement, Rating obj){
        try{
            statement.setLong(1, obj.getModeratorId());
            statement.setDouble(2, obj.getRatingValue());
            statement.setBoolean(3, obj.isApproved());
            statement.setLong(4, obj.getAccountIdFrom());
            statement.setLong(5, obj.getAccountIdTo());
            statement.setLong(6, obj.getVersion());
            executeUpdate(statement);

            long version = getVersion(statement);

            return new Rating(obj.getAccountIdFrom(), obj.getAccountIdTo(), obj.getModeratorId(), obj.getRatingValue(), obj.isApproved(), version);
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }

    private static Rating prepareDeleteStatement(PreparedStatement statement, Rating obj){
        try{
            statement.setLong(1, obj.getAccountIdFrom());
            statement.setLong(2, obj.getAccountIdTo());
            statement.setLong(3, obj.getVersion());
            executeUpdate(statement);
            return null;
        } catch (SQLException e) {
            throw new DataMapperException(e);
        }
    }
}
