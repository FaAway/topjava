package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.repository.UserMealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

/**
 * User: gkisline
 * Date: 26.08.2014
 */

@Repository
@Transactional(readOnly = true)
public class JpaUserMealRepositoryImpl implements UserMealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public UserMeal save(UserMeal userMeal, int userId) {
        if (userMeal.isNew()) {
            userMeal.setUser(em.getReference(User.class, userId));
            em.persist(userMeal);
            return userMeal;
        } else {
            return em.createNamedQuery(UserMeal.UPDATE)
                    .setParameter("calories", userMeal.getCalories())
                    .setParameter("dateTime", userMeal.getDateTime())
                    .setParameter("description", userMeal.getDescription())
                    .setParameter("id", userMeal.getId())
                    .setParameter("user", em.getReference(User.class, userId))
                    .executeUpdate() == 0 ? null : userMeal;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(UserMeal.DELETE)
                .setParameter("id", id)
                .setParameter("user", em.getReference(User.class, userId))
                .executeUpdate() != 0;
    }

    @Override
    public UserMeal get(int id, int userId) {
        return (UserMeal) DataAccessUtils.singleResult(em.createNamedQuery(UserMeal.GET).setParameter("id", id).setParameter("user", em.getReference(User.class, userId)).getResultList());
    }

    @Override
    public List<UserMeal> getAll(int userId) {
        return (List<UserMeal>) em.createNamedQuery(UserMeal.ALL).setParameter("user", em.getReference(User.class, userId)).getResultList();
    }

    @Override
    public List<UserMeal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return (List<UserMeal>) em.createNamedQuery(UserMeal.BETWEEN)
                .setParameter("user", em.getReference(User.class, userId))
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .getResultList();
    }
}