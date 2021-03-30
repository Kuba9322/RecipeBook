package pl.example.recipeBook.domain.vote;



import pl.example.recipeBook.domain.common.BaseDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VoteDao extends BaseDao {

    public int countByRecipeId(int recipeId){
        final String sql = """
                SELECT 
                (SELECT COUNT(recipe_id) FROM vote WHERE recipe_id = ? AND type = 'UP')
                -
                (SELECT COUNT(recipe_id) FROM vote WHERE recipe_id = ? AND type = 'DOWN')
                AS
                vote_count;
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, recipeId);
            preparedStatement.setInt(2,recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(Vote vote) {
        final String sql = """
                INSERT INTO
                    vote (user_id, recipe_id, type, date_added)
                VALUES
                    (?, ?, ?, ?)
                ON DUPLICATE KEY UPDATE
                    type = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vote.getUserId());
            statement.setInt(2, vote.getRecipeId());
            statement.setString(3, vote.getType().toString());
            statement.setObject(4, vote.getDateAdded());
            statement.setString(5, vote.getType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}