package ua.restaurant.vote.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ua.restaurant.vote.model.Restaurant;
import ua.restaurant.vote.service.RestaurantService;
import ua.restaurant.vote.to.RestaurantTo;
import ua.restaurant.vote.to.ResultTo;

import java.time.LocalDate;
import java.util.List;

import static ua.restaurant.vote.util.ValidationUtil.checkIdConsistent;
import static ua.restaurant.vote.util.ValidationUtil.checkNew;

/**
 * Created by Galushkin Pavel on 06.03.2017.
 */
public abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private RestaurantService service;

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<RestaurantTo> findAllForDate(LocalDate date) {
        log.info("findAllForDate {}", date);
        return service.findAllForDate(date != null ? date : LocalDate.now());
    }

    public Restaurant get(int id) {
        log.info("get " + id);
        return service.get(id);
    }

    public List<Restaurant> getByName(String name) {
        log.info("getByName " + name);
        return service.getByName(name);
    }

    public Restaurant create(Restaurant restaurant) {
        checkNew(restaurant);
        log.info("create " + restaurant);
        return service.save(restaurant);
    }

    public void delete(int id) {
        log.info("delete " + id);
        service.delete(id);
    }

    public void update(Restaurant restaurant, int id) {
        checkIdConsistent(restaurant, id);
        log.info("update " + restaurant);
        service.update(restaurant);
    }

    public List<ResultTo> get(LocalDate date) {
        if (date == null) date = LocalDate.now();
        log.info("get poll result for date {}", date);
        return service.getRes(date);
    }
}
