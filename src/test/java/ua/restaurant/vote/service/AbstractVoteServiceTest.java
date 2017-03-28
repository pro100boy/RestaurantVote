package ua.restaurant.vote.service;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ua.restaurant.vote.RestaurantTestData;
import ua.restaurant.vote.model.Vote;
import ua.restaurant.vote.util.DateTimeUtil;
import ua.restaurant.vote.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import static ua.restaurant.vote.RestaurantTestData.*;
import static ua.restaurant.vote.UserTestData.*;
import static ua.restaurant.vote.VoteTestData.MATCHER;
import static ua.restaurant.vote.VoteTestData.*;
import static ua.restaurant.vote.VoteTestData.getCreated;

/**
 * Created by Galushkin Pavel on 07.03.2017.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public abstract class AbstractVoteServiceTest extends AbstractServiceTest {

    @Autowired
    VoteService voteService;

    @Test
    public void testSave() {
        voteService.save(USER1_ID, RESTAURANT2_ID);
        Vote created = getCreated();
        created.setId(100023);
        MATCHER.assertEquals(created, voteService.getVote(USER1_ID, LocalDate.now()));
    }

    @Test(expected = NotFoundException.class)
    public void testNotFoundDelete() throws Exception {
        voteService.delete(1, 1);
    }

    @Test
    public void testGet() throws Exception {
        Vote vote = voteService.get(VOTE1_ID, ADMIN_ID);
        MATCHER.assertEquals(VOTE1, vote);
    }

    @Test(expected = NotFoundException.class)
    public void testGetNotFound() throws Exception {
        voteService.get(1, 1);
    }

    @Test
    public void testGetAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(VOTE5, VOTE1), voteService.getAll(ADMIN_ID));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testUpdateIllegal() throws Exception {
        Vote vote = voteService.getVote(ADMIN_ID, LocalDate.now());
        updateHelper(vote, 0, true);
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void testUpdateAfterDeadline() throws Exception {
        Vote vote = voteService.getVote(USER2_ID, LocalDate.now());
        updateHelper(vote, RESTAURANT1_ID, false);
    }

    private void updateHelper(Vote vote, int restId, boolean plusMin) {
        if (plusMin)
            DateTimeUtil.setDeadlineVoteTime(LocalTime.now().plusMinutes(1));
        else
            DateTimeUtil.setDeadlineVoteTime(LocalTime.now().minusMinutes(1));

        voteService.update(vote, restId);

        DateTimeUtil.setDeadlineVoteTime(DateTimeUtil.DEFAULT_VOTE_DEADLINE_TIME);
    }

    @Test
    public void testUpdate() throws Exception
    {
        Vote vote = voteService.getVote(USER2_ID, LocalDate.now());
        updateHelper(vote, RESTAURANT1_ID,true);
        Vote updated = voteService.getVote(USER2_ID, LocalDate.now());

        Vote expected = getCreated();
        expected.setId(100021);
        expected.setRestaurant(RESTAURANT1);

        MATCHER.assertEquals(expected, updated);
        RestaurantTestData.MATCHER.assertEquals(RESTAURANT1, updated.getRestaurant());
    }
}