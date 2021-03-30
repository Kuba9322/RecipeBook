package pl.example.recipeBook.domain.comment;

import pl.example.recipeBook.domain.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {
    public void save(Comment comment){
        final String sql = """
                INSERT INTO
                comment
                (user_id, recipe_id, content, date_added)
                VALUES
                (?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1,comment.getUserId());
            preparedStatement.setInt(2,comment.getRecipeId());
            preparedStatement.setString(3,comment.getContent());
            preparedStatement.setObject(4,comment.getDateAdded());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                comment.setCommentId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Comment> findByRecipeId (int recipeId){
        final String sql = """
                SELECT
                id, user_id, recipe_id, content, date_added
                FROM
                comment
                WHERE
                recipe_id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1,recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Comment> comments = new ArrayList<>();
            while (resultSet.next()){
                Comment comment = mapRow(resultSet);
                comments.add(comment);
            }
            return comments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Comment mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        int user_id = resultSet.getInt("user_id");
        int recipe_id = resultSet.getInt("recipe_id");
        String content = resultSet.getString("content");
        LocalDateTime date_added = resultSet.getTimestamp("date_added").toLocalDateTime();
        return new Comment(id, user_id, recipe_id, content, date_added);
    }
}
