package ua.restaurant.vote;

import ua.restaurant.vote.matcher.ModelMatcher;
import ua.restaurant.vote.model.Dish;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static ua.restaurant.vote.model.BaseEntity.START_SEQ;

/**
 * Created by Galushkin Pavel on 11.03.2017.
 */
public class DishTestData {
    public static final ModelMatcher<Dish> MATCHER = ModelMatcher.of(Dish.class);

    public static final int DISH1_ID = START_SEQ + 7;
    public static final int DISH2_ID = START_SEQ + 8;
    public static final int DISH3_ID = START_SEQ + 9;
    public static final int DISH4_ID = START_SEQ + 10;
    public static final int DISH5_ID = START_SEQ + 11;
    public static final int DISH6_ID = START_SEQ + 12;
    public static final int DISH7_ID = START_SEQ + 13;
    public static final int DISH8_ID = START_SEQ + 14;

    public static final Dish DISH1 = new Dish(DISH1_ID, of(2017, Month.JANUARY, 30), "Dish 1", 1250);
    public static final Dish DISH2 = new Dish(DISH2_ID, of(2017, Month.JANUARY, 30), "Dish 2", 1350);
    public static final Dish DISH3 = new Dish(DISH3_ID, of(2017, Month.JANUARY, 30), "Dish 3", 1450);
    public static final Dish DISH4 = new Dish(DISH4_ID, of(2017, Month.FEBRUARY, 20), "Dish 4", 12050);
    public static final Dish DISH5 = new Dish(DISH5_ID, of(2017, Month.FEBRUARY, 20), "Dish 5", 13050);
    public static final Dish DISH6 = new Dish(DISH6_ID, of(2017, Month.FEBRUARY, 20), "Dish 6", 14050);
    public static final Dish DISH7 = new Dish(DISH7_ID, LocalDate.now(), "Dish 7", 13999);
    public static final Dish DISH8 = new Dish(DISH8_ID, LocalDate.now(), "Dish 8", 14099);

    public static final List<Dish> DISHES = Arrays.asList(DISH1, DISH2, DISH3);

    public static Dish getCreated() {
        return new Dish(null, LocalDate.now(), "Created menu", 599);
    }

    public static Dish getUpdated() {
        return new Dish(DISH1_ID, LocalDate.now(), "Updated menu", 1599);
    }
}
