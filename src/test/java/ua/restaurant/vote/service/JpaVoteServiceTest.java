package ua.restaurant.vote.service;

import org.junit.Test;
import ua.restaurant.vote.ResultTestData;
import ua.restaurant.vote.to.ResultTo;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Created by Galushkin Pavel on 12.03.2017.
 */
public class JpaVoteServiceTest extends AbstractVoteServiceTest {

    @Test
    public void testGetVoteResult() throws Exception {
        List<ResultTo> resultSet = voteService.getResultSet(LocalDate.of(2017, Month.JANUARY, 30));
        ResultTestData.MATCHER.assertCollectionEquals(ResultTestData.RESULT_TO_LIST, resultSet);
    }
}
