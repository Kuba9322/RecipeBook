package pl.example.recipeBook.domain.api;

import pl.example.recipeBook.domain.recipe.Recipe;
import pl.example.recipeBook.domain.recipe.RecipeDao;
import pl.example.recipeBook.domain.user.UserDao;
import pl.example.recipeBook.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class RecipeService {
    private final RecipeDao recipeDao = new RecipeDao();
    private final RecipeMapper recipeMapper = new RecipeMapper();

    public List<RecipeBasicInfo> findAll(){
        return recipeDao.findAll()
                .stream()
                .map(recipeMapper::map)
                .collect(Collectors.toList());
    }
    public RecipeBasicInfo findById(int recipeId){
        Recipe recipe = recipeDao.findById(recipeId).orElseThrow();
        return recipeMapper.map(recipe);
    }

    public void add(RecipeSaveRequest recipeSaveRequest){
        Recipe recipeToSave = recipeMapper.map(recipeSaveRequest);
        recipeDao.save(recipeToSave);
    }

    public List<RecipeBasicInfo> findByCategory(int categoryId){
        return recipeDao.findByCategory(categoryId)
                .stream()
                .map(recipeMapper::map)
                .collect(Collectors.toList());
    }

    private static class RecipeMapper{
        private final UserDao userDao = new UserDao();
        private final VoteDao voteDao = new VoteDao();

        RecipeBasicInfo map(Recipe recipe){
            return new RecipeBasicInfo(
                    recipe.getId(),
                    recipe.getTitle(),
                    recipe.getPhoto_url(),
                    recipe.getDescription(),
                    recipe.getIngredients(),
                    recipe.getPrepTimeInMinutes(),
                    recipe.getDateAdded(),
                    voteDao.countByRecipeId(recipe.getId()),
                    userDao.findById(recipe.getUserId()).orElseThrow().getUsername()
            );
        }

        private Recipe map(RecipeSaveRequest recipeSaveRequest) {
            return new Recipe(
                    recipeSaveRequest.getTitle(),
                    recipeSaveRequest.getPhoto_url(),
                    recipeSaveRequest.getDescription(),
                    recipeSaveRequest.getIngredients(),
                    recipeSaveRequest.getPrepTimeMin(),
                    LocalDateTime.now(),
                    recipeSaveRequest.getCategoryId(),
                    userDao.findByUsername(recipeSaveRequest.getAuthor())
                            .orElseThrow()
                            .getId()
            );
        }
    }
}
