package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.UserMeal;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by FarAway on 09.04.2016.
 */

@Transactional(readOnly = true)
public interface ProxyUserMealRepository extends JpaRepository<UserMeal, Integer> {

    @Transactional
    @Override
    UserMeal save(UserMeal meal);

    @Transactional
    @Modifying
    @Query("DELETE FROM UserMeal m WHERE m.id =:id AND m.user.id =:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    UserMeal findOneByIdAndUserId(Integer id, int userId);

    List<UserMeal> findByUserIdOrderByDateTimeDesc(int userId);

    List<UserMeal> findByUserIdAndDateTimeBetweenOrderByDateTimeDesc(int userId, LocalDateTime startDate, LocalDateTime endDate);

    @Query("SELECT m FROM UserMeal m LEFT JOIN FETCH m.user WHERE m.id=:id")
    UserMeal findWithUser(@Param("id") int id);
}
