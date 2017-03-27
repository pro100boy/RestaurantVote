package ua.restaurant.vote.web.dish;

import org.junit.Test;
import org.springframework.http.MediaType;
import ua.restaurant.vote.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.restaurant.vote.DishTestData.*;
import static ua.restaurant.vote.RestaurantTestData.RESTAURANT2_ID;
import static ua.restaurant.vote.TestUtil.userHttpBasic;
import static ua.restaurant.vote.UserTestData.USER1;


public class DishProfileRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = DishProfileRestController.REST_URL + '/';

    @Test
    public void testGetToday() throws Exception {
        mockMvc.perform(get(REST_URL, RESTAURANT2_ID)
                .with(userHttpBasic(USER1)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentListMatcher(DISH7, DISH8));
    }
}
