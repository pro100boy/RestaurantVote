package ua.restaurant.vote.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "dishes", uniqueConstraints = {@UniqueConstraint(columnNames = {"rest_id", "date", "name", "price"}, name = "dishes_unique_idx")})
public class Dish extends NamedEntity {
    @Column(name = "date", nullable = false)
    @NotNull
    @DateTimeFormat(pattern = DateTimeUtil.DATE_PATTERN)
    private LocalDate date;

    @Column(name = "price", nullable = false, columnDefinition = "int default 0")
    @NotNull
    private Integer price;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rest_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference(value="restaurant-dishes")
    private Restaurant restaurant;

    public Dish() {
    }

    public Dish(LocalDate date, String name, Integer price) {
        this(null, date, name, price);
    }

    public Dish(Integer id, LocalDate date, String name, Integer price) {
        super(id, name);
        this.date = date;
        this.price = price;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + getId() +
                ", date=" + date +
                ", dish=" + name +
                ", price=" + Integer.toString(price) +
                //", restaurant=" + restaurant +
                '}';
    }
}
