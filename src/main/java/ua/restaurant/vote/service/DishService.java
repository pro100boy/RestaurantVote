package ua.restaurant.vote.service;

import ua.restaurant.vote.model.Dish;
import ua.restaurant.vote.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 14.03.2017.
 */
public interface DishService {
    Dish save(Dish menu, int restaurantId);
    Dish update(Dish menu, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;;

    Dish get(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAllByRestaurant(int restaurantId);
    List<Dish> getAllByRestaurant(int restaurantId, LocalDate startDate, LocalDate endDate);

    void evictCache();
}
