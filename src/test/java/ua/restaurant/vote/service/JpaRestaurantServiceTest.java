package ua.restaurant.vote.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ua.restaurant.vote.DishTestData;
import ua.restaurant.vote.ResultTestData;
import ua.restaurant.vote.model.Restaurant;
import ua.restaurant.vote.repository.JpaUtil;
import ua.restaurant.vote.to.RestaurantTo;
import ua.restaurant.vote.to.ResultTo;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Galushkin Pavel on 07.03.2017.
 */
public class JpaRestaurantServiceTest extends AbstractRestaurantServiceTest {
    @Autowired
    private JpaUtil jpaUtil;

    @Before
    @Override
    public void setUp() throws Exception {
        super.setUp();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void testValidation() throws Exception {
        // empty name
        validateRootCause(() -> service.save(new Restaurant(null, "  ", "Restaurant")), ConstraintViolationException.class);
        // empty description
        validateRootCause(() -> service.save(new Restaurant(null, "Name", "  ")), ConstraintViolationException.class);
        // description size < 5
        validateRootCause(() -> service.save(new Restaurant(null, "Name", "desc")), ConstraintViolationException.class);
        // empty name and description
        validateRootCause(() -> service.save(new Restaurant(null, "  ", " ")), ConstraintViolationException.class);
    }

    @Test
    public void testFindAllForDate() throws Exception {
        List<RestaurantTo> restaurantsTo = service.findAllForDate(LocalDate.of(2017, Month.JANUARY, 30));
        Assert.assertEquals(restaurantsTo.size(), 3);
        DishTestData.MATCHER.assertCollectionEquals(restaurantsTo.stream().flatMap(m->m.getDishes().stream()).collect(Collectors.toList()), DishTestData.DISHES);
    }

    @Test
    public void testGetRes() throws Exception {
        List<ResultTo> resultSet = service.getRes(LocalDate.of(2017, Month.JANUARY, 30));
        ResultTestData.MATCHER.assertCollectionEquals(ResultTestData.RESULT_TO_LIST, resultSet);
    }
}
