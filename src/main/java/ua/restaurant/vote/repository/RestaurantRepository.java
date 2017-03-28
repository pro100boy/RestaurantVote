package ua.restaurant.vote.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.vote.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 06.03.2017.
 */
@Transactional(readOnly = true)
@SuppressWarnings("JpaQlInspection")
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    @Override
    Restaurant save(Restaurant restaurant);

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r WHERE r.id=:id")
    int delete(@Param("id") int id);

    //List<Restaurant> findAllByOrderByNameAsc();
    List<Restaurant> findAll();
//TODO проверить что возвращает
    // Each restaurant provides new menu each day
    @Query("SELECT DISTINCT r FROM Restaurant r LEFT JOIN FETCH r.dishes m WHERE m.date = ?1 ORDER BY r.name")
    List<Restaurant> findAllForDate(LocalDate date);

    @Override
    Restaurant findOne(Integer integer);

    List<Restaurant> findByNameIgnoreCaseStartingWith(String name);
}
