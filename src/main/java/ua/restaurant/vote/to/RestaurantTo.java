package ua.restaurant.vote.to;

import ua.restaurant.vote.model.Dish;

import java.io.Serializable;
import java.util.Set;

/**
 * Created by Galushkin Pavel on 10.03.2017.
 */
public class RestaurantTo extends BaseTo implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Set<Dish> dishes;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, Set<Dish> dishes) {
        super(id);
        this.name = name;
        this.dishes = dishes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", dishes=" + dishes +
                '}';
    }
}
