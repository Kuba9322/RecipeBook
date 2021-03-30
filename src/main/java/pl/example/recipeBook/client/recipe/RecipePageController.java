package pl.example.recipeBook.client.recipe;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.example.recipeBook.domain.api.RecipeBasicInfo;
import pl.example.recipeBook.domain.api.RecipeComment;
import pl.example.recipeBook.domain.api.RecipeCommentService;
import pl.example.recipeBook.domain.api.RecipeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/recipe/view")
public class RecipePageController extends HttpServlet {
    RecipeService recipeService = new RecipeService();
    RecipeCommentService recipeCommentService = new RecipeCommentService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RecipeBasicInfo recipe = recipeService.findById(id);
        request.setAttribute("recipe", recipe);
        List<RecipeComment> comments = recipeCommentService.findCommentsByRecipeId(id);
        request.setAttribute("comments", comments);
        request.getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(request, response);
    }
}
