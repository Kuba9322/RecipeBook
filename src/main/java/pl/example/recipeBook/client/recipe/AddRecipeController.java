package pl.example.recipeBook.client.recipe;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.example.recipeBook.domain.api.CategoryName;
import pl.example.recipeBook.domain.api.CategoryService;
import pl.example.recipeBook.domain.api.RecipeSaveRequest;
import pl.example.recipeBook.domain.api.RecipeService;

import java.io.IOException;
import java.util.List;

@WebServlet("/recipe/add")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER"),
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
        }
)
public class AddRecipeController extends HttpServlet {
    RecipeService recipeService = new RecipeService();
    CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<CategoryName> categories = categoryService.findAllCategoryNames();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/add-recipe.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeSaveRequest saveRequest = createSaveRequest(request);
        recipeService.add(saveRequest);
        response.sendRedirect(request.getContextPath());
    }

    private RecipeSaveRequest createSaveRequest(HttpServletRequest request) {
        String loggedInUsername = request.getUserPrincipal().getName();

        String title = request.getParameter("title");
        String photoUrl = request.getParameter("photo-url");
        Integer prepTime = Integer.parseInt(request.getParameter("prep-time"));
        Integer categoryId = Integer.parseInt(request.getParameter("categoryId"));
        String ingredients = request.getParameter("ingredients");
        String description = request.getParameter("description");

        return new RecipeSaveRequest(title, photoUrl, description,
                ingredients, prepTime, categoryId, loggedInUsername);
    }
}
