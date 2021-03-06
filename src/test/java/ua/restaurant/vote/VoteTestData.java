package ua.restaurant.vote;

import ua.restaurant.vote.matcher.ModelMatcher;
import ua.restaurant.vote.model.Vote;

import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.of;
import static ua.restaurant.vote.RestaurantTestData.RESTAURANT2;
import static ua.restaurant.vote.UserTestData.USER1;
import static ua.restaurant.vote.model.BaseEntity.START_SEQ;

/**
 * Created by Galushkin Pavel on 07.03.2017.
 */
public class VoteTestData {
    public static final ModelMatcher<Vote> MATCHER = ModelMatcher.of(Vote.class);

    public static final int VOTE1_ID = START_SEQ + 15;
    public static final int VOTE2_ID = START_SEQ + 16;
    public static final int VOTE3_ID = START_SEQ + 17;
    public static final int VOTE4_ID = START_SEQ + 18;
    public static final int VOTE5_ID = START_SEQ + 19;
    public static final int VOTE6_ID = START_SEQ + 20;
    public static final int VOTE7_ID = START_SEQ + 21;
    public static final int VOTE8_ID = START_SEQ + 22;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, of(2017, Month.JANUARY, 30));
    public static final Vote VOTE2 = new Vote(VOTE2_ID, of(2017, Month.JANUARY, 30));
    public static final Vote VOTE3 = new Vote(VOTE3_ID, of(2017, Month.JANUARY, 30));
    public static final Vote VOTE4 = new Vote(VOTE4_ID, of(2017, Month.JANUARY, 30));
    public static final Vote VOTE5 = new Vote(VOTE5_ID, of(2017, Month.FEBRUARY, 20));
    public static final Vote VOTE6 = new Vote(VOTE6_ID, of(2017, Month.FEBRUARY, 20));
    public static final Vote VOTE7 = new Vote(VOTE7_ID, LocalDate.now());
    public static final Vote VOTE8 = new Vote(VOTE8_ID, LocalDate.now());

    public static final List<Vote> VOTES = Arrays.asList(VOTE1, VOTE2, VOTE3, VOTE4, VOTE5, VOTE6, VOTE7, VOTE8);
    public static final List<Vote> VOTES_USER = Arrays.asList(VOTE6, VOTE2);
    public static final List<Vote> VOTES_REST = Arrays.asList(VOTE8, VOTE5, VOTE6, VOTE1);

    public static Vote getCreated() {
        Vote created = new Vote(LocalDate.now());
        created.setRestaurant(RESTAURANT2);
        created.setUser(USER1);
        return created;
    }
}
