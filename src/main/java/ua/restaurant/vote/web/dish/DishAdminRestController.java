package ua.restaurant.vote.web.dish;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ua.restaurant.vote.model.Dish;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

/**
 * Created by Galushkin Pavel on 14.03.2017.
 */
@RestController
@RequestMapping(value = DishAdminRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishAdminRestController extends AbstractDishController {
    static final String REST_URL = "/rest/admin/restaurants/{restaurantId}/dishes";

    // returns concrete menu of the restaurant
    @Override
    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id, @PathVariable("restaurantId") int restaurantId) {
        super.delete(id, restaurantId);
    }

    @Override
    @GetMapping
    public List<Dish> getAll(@PathVariable("restaurantId") int restaurantId) {
        return super.getAll(restaurantId);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@Valid @RequestBody Dish menu, @PathVariable("id") int id,
                       @PathVariable("restaurantId") int restaurantId) {
        super.update(menu, id, restaurantId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> createWithLocation(@Valid @RequestBody Dish menu, @PathVariable("restaurantId") int restaurantId) {
        Dish created = super.create(menu, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
