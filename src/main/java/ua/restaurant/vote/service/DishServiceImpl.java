package ua.restaurant.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ua.restaurant.vote.model.Dish;
import ua.restaurant.vote.repository.DishRepository;
import ua.restaurant.vote.repository.RestaurantRepository;

import java.time.LocalDate;
import java.util.List;

import static ua.restaurant.vote.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Galushkin Pavel on 14.03.2017.
 */
@Service("menuService")
public class DishServiceImpl implements DishService {
    @Autowired
    DishRepository dishRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Override
    @Transactional
    public Dish save(Dish menu, int restaurantId) {
        if (!menu.isNew() && get(menu.getId(), restaurantId) == null) {
            menu = null;
        }
        else {
            menu.setRestaurant(restaurantRepository.getOne(restaurantId));
        }
        Assert.notNull(menu, "menu must not be null");
        return dishRepository.save(menu);
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
        Dish menu = dishRepository.findOne(id);
        return checkNotFoundWithId(menu != null && menu.getRestaurant().getId() == restaurantId ? menu : null, id);
    }

    @Override
    public List<Dish> getAllByRestaurant(int restaurantId) {
        return dishRepository.getAllByRestaurant(restaurantId);
    }
}
