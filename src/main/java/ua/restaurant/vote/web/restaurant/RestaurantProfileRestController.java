package ua.restaurant.vote.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.restaurant.vote.model.Restaurant;
import ua.restaurant.vote.to.RestaurantTo;
import ua.restaurant.vote.to.ResultTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 10.03.2017.
 */
@RestController
@RequestMapping(value = RestaurantProfileRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantProfileRestController extends AbstractRestaurantController{
    public static final String REST_URL = "/rest/profile/restaurants";

    // get list with restaurants and his menus
    @Override
    @GetMapping(value = "/polls")
    public List<RestaurantTo> findAllForDate(@RequestParam(value = "date", required = false) LocalDate date) {
        return super.findAllForDate(date);
    }

    /*
    lets user to find and get info about concrete restaurant
     */
    @Override
    @GetMapping
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    // poll result for the specified date. If date doesn't present, then date = today
    @Override
    @GetMapping(value = "/results")
    public List<ResultTo> get(@RequestParam(value = "date", required = false) LocalDate date) {
        return super.get(date);
    }

    @Override
    @GetMapping(value = "/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @Override
    @GetMapping(value = "/by")
    public List<Restaurant> getByName(@RequestParam("name") String name) {
        return super.getByName(name);
    }
}
