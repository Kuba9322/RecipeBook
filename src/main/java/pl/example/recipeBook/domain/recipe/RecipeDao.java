package pl.example.recipeBook.domain.recipe;

import pl.example.recipeBook.domain.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RecipeDao extends BaseDao {

    public void save(Recipe recipe){
        final String sql = """
                INSERT INTO
                recipe (title, photo_url, description, ingredients, prep_time_min, date_added, category_id, user_id)
                VALUES
                (?, ?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,recipe.getTitle());
            preparedStatement.setString(2, recipe.getPhoto_url());
            preparedStatement.setString(3,recipe.getDescription());
            preparedStatement.setString(4,recipe.getIngredients());
            preparedStatement.setInt(5, recipe.getPrepTimeInMinutes());
            preparedStatement.setObject(6,recipe.getDateAdded());
            preparedStatement.setInt(7, recipe.getCategoryId());
            preparedStatement.setInt(8,recipe.getUserId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){
                recipe.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Optional<Recipe> findById(int recipeId){
        final String sql = """
                SELECT
                id, title, photo_url, description, ingredients, prep_time_min, date_added, category_id, user_id
                FROM
                recipe_book.recipe
                WHERE
                id = ?
                """;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                Recipe recipe = mapRow(resultSet);
                return Optional.of(recipe);
            }else {
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Recipe> findByCategory(int categoryId){
        final String sql ="""
                SELECT
                id, title, photo_url, description, ingredients, prep_time_min, date_added, category_id, user_id
                FROM
                recipe_book.recipe
                WHERE
                category_id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Recipe> recipeList = new ArrayList<>();
            while (resultSet.next()){
                Recipe recipe = mapRow(resultSet);
                recipeList.add(recipe);
            }
            return recipeList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Recipe> findAll(){
        final String sql = """
                SELECT 
                id, title, photo_url, description, ingredients, prep_time_min, date_added, category_id, user_id
                FROM 
                recipe d
                """;
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Recipe> recipes = new ArrayList<>();
            while (resultSet.next()){
                Recipe recipe = mapRow(resultSet);
                recipes.add(recipe);
            }
            return recipes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Recipe mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String photo_url = resultSet.getString("photo_url");
        String description = resultSet.getString("description");
        String ingredients = resultSet.getString("ingredients");
        int prep_time_min = resultSet.getInt("prep_time_min");
        LocalDateTime date_added = resultSet.getTimestamp("date_added").toLocalDateTime();
        int categoryId = resultSet.getInt("category_id");
        int user_id = resultSet.getInt("user_id");
        return new Recipe(id, title, photo_url, description, ingredients, prep_time_min, date_added, categoryId, user_id);
    }
}