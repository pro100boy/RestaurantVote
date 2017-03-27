package ua.restaurant.vote.web.dish;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.restaurant.vote.model.Dish;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 14.03.2017.
 */

@RestController
@RequestMapping(value = DishProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishProfileRestController extends AbstractDishController {
    static final String REST_URL = "/rest/profile/restaurants/{restaurantId}/menus";

    @GetMapping
    public List<Dish> getToday(@PathVariable("restaurantId") int restaurantId) {
        return super.getBetween(restaurantId, LocalDate.now(), LocalDate.now());
    }
}
