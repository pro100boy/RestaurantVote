package ua.restaurant.vote.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;
import ua.restaurant.vote.util.DateTimeUtil;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Galushkin Pavel
 * 04.03.2017
 */
@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date"}, name = "user_date_unique_idx")})
public class Vote extends BaseEntity{
    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    //@JsonBackReference(value="restaurant-votes")
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(LocalDate date) {
        this(null, date);
    }

    public Vote(Integer id, LocalDate date) {
        super(id);
        this.date = date;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate dateTime) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Vote{" +
                "id=" + getId() +
                ", date=" + date +
                //", user=" + user +
                //", restaurant=" + getRestaurant() +
                '}';
    }
}
