package ru.javawebinar.topjava.model;

import ru.javawebinar.topjava.util.LocalDateTimeAttributeConverter;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = UserMeal.UPDATE, query = "update UserMeal m set m.dateTime=:dateTime, m.description=:description, m.calories=:calories where m.id=:id and m.user=:user"),
        @NamedQuery(name = UserMeal.DELETE, query = "delete from UserMeal m where m.id=:id and m.user=:user"),
        @NamedQuery(name = UserMeal.GET, query = "select m from UserMeal m where m.id=:id and m.user=:user"),
        @NamedQuery(name = UserMeal.ALL, query = "select m from UserMeal m where m.user=:user ORDER BY m.dateTime DESC"),
        @NamedQuery(name = UserMeal.BETWEEN, query = "select m from UserMeal m where m.user=:user and m.dateTime between :startDate AND :endDate ORDER BY m.dateTime DESC")
})
@Entity
@Table(name = "meals", uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "date_time"}, name = "meals_unique_user_datetime_idx")})
public class UserMeal extends BaseEntity {

    public static final String UPDATE = "UserMeal.update";
    public static final String DELETE = "UserMeal.delete";
    public static final String GET = "UserMeal.get";
    public static final String ALL = "UserMeal.getAll";
    public static final String BETWEEN = "UserMeal.getBetween";

    @Column(name = "date_time", nullable = false)
    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime dateTime;

    @Column(name = "description")
    private String description;

    @Column(name = "calories")
    protected int calories;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserMeal() {
    }

    public UserMeal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public UserMeal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UserMeal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
