package pl.example.recipeBook.client.category;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import pl.example.recipeBook.domain.api.CategoryFullInfo;
import pl.example.recipeBook.domain.api.CategoryService;
import pl.example.recipeBook.domain.api.RecipeBasicInfo;
import pl.example.recipeBook.domain.api.RecipeService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryController extends HttpServlet {
    private RecipeService recipeService = new RecipeService();
    private CategoryService categoryService = new CategoryService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CategoryFullInfo categoryById = categoryService.findById(id)
                .orElseThrow();
        request.setAttribute("category", categoryById);
        List<RecipeBasicInfo> recipes = recipeService.findByCategory(id);
        request.setAttribute("recipes" , recipes);
        request.getRequestDispatcher("/WEB-INF/views/selected-recipes.jsp").forward(request, response);
    }

}