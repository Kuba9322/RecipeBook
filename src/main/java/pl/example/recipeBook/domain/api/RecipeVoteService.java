package pl.example.recipeBook.domain.api;

import pl.example.recipeBook.domain.user.User;
import pl.example.recipeBook.domain.user.UserDao;
import pl.example.recipeBook.domain.vote.Vote;
import pl.example.recipeBook.domain.vote.VoteDao;

import java.time.LocalDateTime;
import java.util.Optional;

public class RecipeVoteService {
    private VoteDao voteDao = new VoteDao();
    private DiscoveryVoteMapper voteMapper = new DiscoveryVoteMapper();

    public void addVote(RecipeVote vote) {
        Vote voteToSave = voteMapper.map(vote);
        voteDao.save(voteToSave);
    }

    private static class DiscoveryVoteMapper {
        private final UserDao userDao = new UserDao();

        Vote map(RecipeVote vote) {
            Optional<User> user = userDao.findByUsername(vote.getUsername());
            return new Vote(
                    user.orElseThrow().getId(),
                    vote.getRecipeId(),
                    Vote.Type.valueOf(vote.getType()),
                    LocalDateTime.now()
            );
        }
    }
}