package ua.restaurant.vote.web.vote;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.restaurant.vote.model.Vote;

import java.net.URI;

/**
 * Created by Galushkin Pavel on 12.03.2017.
 */
@RestController
@RequestMapping(value = VoteProfileRestController.REST_URL)
public class VoteProfileRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/profile/votes/restaurants";

    @PostMapping(value = "/{restaurantId}")
    public void createOrUpdate(@PathVariable("restaurantId") int restaurantId) {
        super.createOrUpdate(restaurantId);
    }
}
