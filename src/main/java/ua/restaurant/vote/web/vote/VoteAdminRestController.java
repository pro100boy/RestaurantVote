package ua.restaurant.vote.web.vote;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.vote.model.Vote;

import java.util.List;

/**
 * Created by Galushkin Pavel on 12.03.2017.
 */
@RestController
@RequestMapping(value = VoteAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteAdminRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/admin/votes";

    /*
     * admin can remove the vote at any time
     */
    @Override
    @DeleteMapping(value = "/{id}/users/{userId}")
    public void delete(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        super.delete(id, userId);
    }

    // get all votes from concrete user
    @Override
    @GetMapping(value = "/users/{userId}")
    public List<Vote> getAll(@PathVariable("userId") int userId) {
        return super.getAll(userId);
    }

    // get specific vote
    @Override
    @GetMapping(value = "/{id}/users/{userId}")
    public Vote get(@PathVariable("id") int id, @PathVariable("userId") int userId) {
        return super.get(id, userId);
    }
}
