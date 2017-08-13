package ua.restaurant.vote.web.vote;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Galushkin Pavel on 12.03.2017.
 */
@RestController
@RequestMapping(value = VoteProfileRestController.REST_URL)
public class VoteProfileRestController extends AbstractVoteController {
    public static final String REST_URL = "/rest/profile/votes/restaurants";

    @PostMapping(value = "/{restaurantId}")
    public void createOrUpdate(@PathVariable("restaurantId") int restaurantId) {
        super.createOrUpdate(restaurantId);
    }
}
