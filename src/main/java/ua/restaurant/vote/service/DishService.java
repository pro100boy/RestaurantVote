package ua.restaurant.vote.service;

import ua.restaurant.vote.model.Dish;
import ua.restaurant.vote.util.exception.NotFoundException;

import java.util.List;

/**
 * Created by Galushkin Pavel on 14.03.2017.
 */
public interface DishService {
    Dish save(Dish dish, int restaurantId);

    Dish update(Dish dish, int restaurantId) throws NotFoundException;

    void delete(int id, int restaurantId) throws NotFoundException;;

    Dish get(int id, int restaurantId) throws NotFoundException;

    List<Dish> getAllByRestaurant(int restaurantId);
}
