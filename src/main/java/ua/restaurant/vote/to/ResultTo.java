package ua.restaurant.vote.to;

/**
 * Created by Galushkin Pavel on 10.03.2017.
 */

public class ResultTo {
    //r.ID, r.NAME, COUNT(v.REST_ID)
    private Integer id;
    private String name;
    private Long cnt;

    public ResultTo() {
    }

    public ResultTo(Integer id, String name, Long cnt) {
        this.id = id;
        this.name = name;
        this.cnt = cnt;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getCnt() {
        return cnt;
    }

    @Override
    public String toString() {
        return "ResultTo{" +
                "restaurantId=" + id +
                ", name='" + name + '\'' +
                ", votes=" + cnt +
                '}';
    }
}
