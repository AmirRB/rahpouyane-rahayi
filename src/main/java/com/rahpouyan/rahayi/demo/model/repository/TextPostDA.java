package com.rahpouyan.rahayi.demo.model.repository;

import com.rahpouyan.rahayi.demo.model.entity.TextPost;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class TextPostDA {
    @PersistenceContext
    private EntityManager entityManager;


    public void save(TextPost t) {
        entityManager.persist(t);
    }

    public void update(TextPost t) {
        entityManager.merge(t);
    }

    public void delete(TextPost t) {
        TextPost t1 = entityManager.merge(t);
        entityManager.remove(t1);
    }

    public TextPost findOne(Class<TextPost> aClass, Long id) {
        return entityManager.find(aClass, id);
    }

    public List<TextPost> findAll(int index, int lenght, String type) {
        Query query = entityManager.createQuery("select entity from textPost entity where entity.type = :t order by entity.id desc");
        query.setParameter("t", type);
        query.setFirstResult(((index - 1) * lenght));
        query.setMaxResults(lenght);


        return query.getResultList();
    }

    public List<TextPost> findAll7(String type) {
        int lenght = 7;
        Query query = entityManager.createQuery("select entity from textPost entity where entity.type = :t order by entity.id desc");
        query.setParameter("t", type);
        query.setFirstResult(0);
        query.setMaxResults(lenght);


        return query.getResultList();
    }

    public Long getSequence() {
        Query query = entityManager.createNativeQuery("SELECT SEQ.NEXTVAL AS SEQ FROM DUAL");
        return ((BigDecimal) query.getSingleResult()).longValue();
    }

    public long count(String type) {
        Query query = entityManager.createQuery("SELECT count(entity) FROM textPost entity where entity.type = :t");
        query.setParameter("t", type);
        return (long) query.getSingleResult();
    }


    public void addViewNumber(Long id) {
        TextPost textPost = entityManager.find(TextPost.class, id);
        textPost.setViewNumber(textPost.getViewNumber() + 1);
        entityManager.merge(textPost);
    }

}
