package pl.example.recipeBook.client.comment;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.example.recipeBook.domain.api.RecipeBasicInfo;
import pl.example.recipeBook.domain.api.RecipeComment;
import pl.example.recipeBook.domain.api.RecipeCommentService;
import pl.example.recipeBook.domain.api.RecipeService;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = "/recipe/comment")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "POST", rolesAllowed = "USER")
        }
)
public class AddCommentController extends HttpServlet {
    RecipeCommentService recipeCommentService = new RecipeCommentService();
    RecipeService recipeService = new RecipeService();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeComment recipeComment = createRecipeComment(request);
        recipeCommentService.addComment(recipeComment);
        RecipeBasicInfo recipe = recipeService.findById(Integer.parseInt(request.getParameter("recipeId")));
        request.setAttribute("recipe", recipe);
        request.getRequestDispatcher("/WEB-INF/views/recipe.jsp").forward(request, response);
    }

        private RecipeComment createRecipeComment(HttpServletRequest request) {
        String comment = request.getParameter("comment");
        int recipeId = Integer.parseInt(request.getParameter("recipeId"));
        String name = request.getUserPrincipal().getName();
        return new RecipeComment(recipeId, name, comment, LocalDateTime.now());
    }
}
