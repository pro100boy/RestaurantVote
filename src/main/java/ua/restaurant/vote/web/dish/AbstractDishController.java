package ua.restaurant.vote.web.dish;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.restaurant.vote.model.Dish;
import ua.restaurant.vote.service.DishService;

import java.util.List;

import static ua.restaurant.vote.util.ValidationUtil.checkIdConsistent;
import static ua.restaurant.vote.util.ValidationUtil.checkNew;

/**
 * Created by Galushkin Pavel on 09.03.2017.
 */
public abstract class AbstractDishController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private DishService service;

    public Dish create(Dish menu, int restaurantId) {
        checkNew(menu);
        log.info("create {} for Restaurant {}", menu, restaurantId);
        return service.save(menu, restaurantId);
    }

    public void update(Dish menu, int id, int restaurantId) {
        checkIdConsistent(menu, id);
        log.info("update {} for Restaurant {}", menu, restaurantId);
        service.update(menu, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete menu {} for Restaurant {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public Dish get(int id, int restaurantId) {
        log.info("get menu {} for Restaurant {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public List<Dish> getAll(int restaurantId) {
        log.info("getAll for Restaurant {}", restaurantId);
        return service.getAllByRestaurant(restaurantId);
    }
}
