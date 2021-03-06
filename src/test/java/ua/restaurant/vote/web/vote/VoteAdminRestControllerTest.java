package ua.restaurant.vote.web.vote;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.vote.RestaurantTestData;
import ua.restaurant.vote.TestUtil;
import ua.restaurant.vote.model.Vote;
import ua.restaurant.vote.web.AbstractControllerTest;
import ua.restaurant.vote.web.json.JsonUtil;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ua.restaurant.vote.RestaurantTestData.RESTAURANT1;
import static ua.restaurant.vote.RestaurantTestData.RESTAURANT2;
import static ua.restaurant.vote.TestUtil.userHttpBasic;
import static ua.restaurant.vote.UserTestData.ADMIN;
import static ua.restaurant.vote.UserTestData.USER1_ID;
import static ua.restaurant.vote.VoteTestData.*;

/**
 * Created by Galushkin Pavel on 13.03.2017.
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class VoteAdminRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteAdminRestController.REST_URL + '/';

    @Test
    @Transactional
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + VOTE2_ID + "/users/" + USER1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE6), voteService.getAll(USER1_ID));
    }

    @Test
    public void testGetAll() throws Exception {
        ResultActions action = mockMvc.perform(get(REST_URL + "users/" + USER1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MATCHER.contentListMatcher(VOTE6, VOTE2));

        Collection<Vote> votes = JsonUtil.readValues(TestUtil.getContent(action), Vote.class);
        RestaurantTestData.MATCHER.assertCollectionEquals(Arrays.asList(RESTAURANT1, RESTAURANT2), votes.stream().map(r->r.getRestaurant()).collect(Collectors.toList()));
    }

    @Test
    public void testGetNotFound() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE2_ID + "/users/1")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE2_ID + "/users/" + USER1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(VOTE2));
    }

    @Test
    public void testGetUnauth() throws Exception {
        mockMvc.perform(get(REST_URL + VOTE1_ID))
                .andExpect(status().isUnauthorized());
    }

}
