package com.rahpouyan.rahayi.demo.model.repository;

import com.rahpouyan.rahayi.demo.model.entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public class UserDA {

    @PersistenceContext
    private EntityManager entityManager;

    public List<User> findAll() {
        Query query = entityManager.createQuery("select e from User e");
        return query.getResultList();
    }

    public void save(User user) {
        entityManager.persist(user);
    }


    public User findByUsename(String username) {
        Query query = entityManager.createQuery("select e from User e where e.username = :u");
        query.setParameter("u", username);
        User user = (User) query.getResultList().get(0);
        return user;
    }


}
