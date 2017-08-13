package ua.restaurant.vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.restaurant.vote.model.Vote;
import ua.restaurant.vote.repository.RestaurantRepository;
import ua.restaurant.vote.repository.UserRepository;
import ua.restaurant.vote.repository.VoteRepository;
import ua.restaurant.vote.util.DateTimeUtil;
import ua.restaurant.vote.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ua.restaurant.vote.util.ValidationUtil.checkNotFoundWithId;

/**
 * Created by Galushkin Pavel on 07.03.2017.
 */
@Service("voteService")
public class VoteServiceImpl implements VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    public void checkModificationAllowed() {
        if (LocalTime.now().isAfter(DateTimeUtil.getDeadlineVoteTime())) {
            throw new DataIntegrityViolationException("VOTE_MODIFICATION_RESTRICTION");
        }
    }

    // because select and insert operations must be in one transaction
    @Transactional
    @Override
    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote(LocalDate.now());
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        vote.setUser(userRepository.getOne(userId));
        return checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    @Override
    @Transactional
    public Vote update(Vote vote, int restaurantId) throws NotFoundException {
        checkModificationAllowed();
        vote.setRestaurant(restaurantRepository.getOne(restaurantId));
        return checkNotFoundWithId(voteRepository.save(vote), vote.getId());
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        checkNotFoundWithId(voteRepository.delete(id, userId) != 0, id);
    }

    // used in tests only
    @Override
    public Vote getVote(int userId, LocalDate date) {
        return voteRepository.getVote(userId, date);
    }

    @Override
    public List<Vote> getAll(int userId) {
        return voteRepository.getAll(userId);
    }

    @Override
    public Vote get(int id, int userId) throws NotFoundException {
        Vote vote = voteRepository.findOne(id);
        return checkNotFoundWithId((vote != null && vote.getUser().getId() == userId ? vote : null), id);
    }
}
