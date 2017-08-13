package ua.restaurant.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ua.restaurant.vote.model.Dish;
import ua.restaurant.vote.repository.DishRepository;
import ua.restaurant.vote.repository.RestaurantRepository;

import java.util.List;

import static ua.restaurant.vote.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Galushkin Pavel on 14.03.2017.
 */
@Service("menuService")
public class DishServiceImpl implements DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            dish = null;
        }
        else {
            dish.setRestaurant(restaurantRepository.getOne(restaurantId));
        }
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }

    @Override
    @Transactional
    public Dish update(Dish menu, int restaurantId) {
        return checkNotFoundWithId(save(menu, restaurantId), menu.getId());
    }

    @Override
    public void delete(int id, int restaurantId) {
        checkNotFoundWithId(dishRepository.delete(id, restaurantId) != 0, id);
    }

    @Override
    public Dish get(int id, int restaurantId) {
        Dish dish = dishRepository.findOne(id);
        return checkNotFoundWithId(dish != null && dish.getRestaurant().getId() == restaurantId ? dish : null, id);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return dishRepository.getAllByRestaurant(restaurantId);
    }
}
