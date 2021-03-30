package pl.example.recipeBook.client.home;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.example.recipeBook.domain.api.CategoryName;
import pl.example.recipeBook.domain.api.CategoryService;
import pl.example.recipeBook.domain.api.RecipeBasicInfo;
import pl.example.recipeBook.domain.api.RecipeService;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "")
public class HomeController extends HttpServlet {
    private RecipeService recipeService = new RecipeService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RecipeBasicInfo> recipes = recipeService.findAll();
        request.setAttribute("recipes", recipes);
        List<CategoryName> categories = categoryService.findAllCategoryNames();
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("categories", categories);
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

}