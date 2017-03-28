package ua.restaurant.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.vote.model.Vote;
import ua.restaurant.vote.to.ResultTo;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 07.03.2017.
 */
@Transactional(readOnly = true)
@SuppressWarnings("JpaQlInspection")
public interface VoteRepository extends JpaRepository<Vote, Integer> {
    @Override
    Vote save(Vote vote);

    @Transactional
    @Modifying
    @Query("DELETE FROM Vote v WHERE v.id=:id AND v.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    Vote findOne(Integer id);

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1 AND v.date = ?2")
    Vote getVote(int userId, LocalDate date);

    @Query("SELECT v FROM Vote v WHERE v.user.id=?1 ORDER BY v.date DESC")
    List<Vote> getAll(int userId);

    @Query(name = "getResultTo")
    List<ResultTo> getResultSet(@Param("date") LocalDate date);
}
