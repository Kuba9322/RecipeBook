package pl.example.recipeBook.client.vote;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HttpMethodConstraint;
import jakarta.servlet.annotation.ServletSecurity;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import pl.example.recipeBook.domain.api.RecipeVote;
import pl.example.recipeBook.domain.api.RecipeVoteService;

import java.io.IOException;

@WebServlet("/recipe/vote")
@ServletSecurity(
        httpMethodConstraints = {
                @HttpMethodConstraint(value = "GET", rolesAllowed = "USER")
        }
)
public class RecipeVoteController extends HttpServlet {
    private RecipeVoteService voteService = new RecipeVoteService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RecipeVote recipeVote = createRecipeVote(request);
        voteService.addVote(recipeVote);
        response.sendRedirect(request.getContextPath());
    }

    private RecipeVote createRecipeVote(HttpServletRequest request) {
        Integer id = Integer.valueOf(request.getParameter("id"));
        String type = request.getParameter("type");
        String name = request.getUserPrincipal().getName();
        return new RecipeVote(name, id, type);
    }
}
