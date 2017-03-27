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
    private Set<Dish> menus;

    public RestaurantTo() {
    }

    public RestaurantTo(Integer id, String name, Set<Dish> menus) {
        super(id);
        this.name = name;
        this.menus = menus;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Dish> getMenus() {
        return menus;
    }

    public void setMenus(Set<Dish> menus) {
        this.menus = menus;
    }

    @Override
    public String toString() {
        return "RestaurantTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", menus=" + menus +
                '}';
    }
}
