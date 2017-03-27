package ua.restaurant.vote.web.dish;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.vote.model.Dish;
import ua.restaurant.vote.web.AbstractControllerTest;
import ua.restaurant.vote.web.json.JsonUtil;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.restaurant.vote.DishTestData.*;
import static ua.restaurant.vote.RestaurantTestData.RESTAURANT1_ID;
import static ua.restaurant.vote.TestUtil.userHttpBasic;
import static ua.restaurant.vote.UserTestData.ADMIN;
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DishAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishAdminRestController.REST_URL + '/';

    @Test
    public void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL, RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH4, DISH1));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(get(REST_URL, RESTAURANT1_ID)
                .param("startDate", "2017-01-01")
                .param("endDate", "2018-01-01")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH4, DISH1));
    }

    @Test
    @Transactional
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH1_ID, RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(DISH4), dishService.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testDeleteNotFound() throws Exception {
        mockMvc.perform(delete(REST_URL + 1, RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Dish updated = getUpdated();
        mockMvc.perform(put(REST_URL + DISH1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertCollectionEquals(Arrays.asList(updated, DISH4), dishService.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testUpdateInvalid() throws Exception {
        Dish updated = getUpdated();
        updated.setName("");
        mockMvc.perform(put(REST_URL + DISH1_ID, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        Dish expected = getCreated();
        ResultActions action = mockMvc.perform(post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Dish returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertCollectionEquals(Arrays.asList(expected, DISH4, DISH1), dishService.getAllByRestaurant(RESTAURANT1_ID));
    }

    @Test
    public void testCreateInvalid() throws Exception {
        Dish invalid = new Dish(null, null, null);
        mockMvc.perform(post(REST_URL, RESTAURANT1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(invalid)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }
}