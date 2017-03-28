package ua.restaurant.vote.service;

import ua.restaurant.vote.model.Vote;
import ua.restaurant.vote.to.ResultTo;
import ua.restaurant.vote.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by Galushkin Pavel on 07.03.2017.
 */
public interface VoteService {
    Vote save(int userId, int restaurantId);

    void delete(int id, int userId) throws NotFoundException;

    Vote get(int id, int userId) throws NotFoundException;

    List<Vote> getAll(int userId);

    Vote getVote(int userId, LocalDate date) throws NotFoundException;

    Vote update(int userId, int restaurantId) throws NotFoundException;

    List<ResultTo> getResultSet(LocalDate date);
}
