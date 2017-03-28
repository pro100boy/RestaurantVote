package ua.restaurant.vote.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import ua.restaurant.vote.AuthorizedUser;
import ua.restaurant.vote.model.Vote;
import ua.restaurant.vote.service.VoteService;
import ua.restaurant.vote.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 12.03.2017.
 */
public class AbstractVoteController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    VoteService service;

    public List<Vote> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll for User {}", userId);
        return service.getAll(userId);
    }

    public List<Vote> getAll(int userId) {
        log.info("getAllWithUser for User {}", userId);
        return service.getAll(userId);
    }

    public Vote get(int id, int userId) {
        log.info("get vote {} for User {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id, int userId) {
        log.info("delete vote {} for User {}", id, userId);
        service.delete(id, userId);
    }

    public void createOrUpdate(int restaurantId) {
        int userId = AuthorizedUser.id();
        Vote vote = service.getVote(userId, LocalDate.now());
        // create new vote
        if (vote == null)
        {
            log.info("create vote for User {} and Restaurant {}", AuthorizedUser.get(), restaurantId);
            service.save(userId, restaurantId);
        }
        // update vote
        else {
            log.info("update vote for User {} and Restaurant {}", AuthorizedUser.get(), restaurantId);
            if (!vote.isNew()) {service.update(vote, restaurantId);}
        }
    }
}
