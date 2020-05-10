package com.rahpouyan.rahayi.demo.model.repository;

import com.rahpouyan.rahayi.demo.model.entity.ImagePost;
import com.rahpouyan.rahayi.demo.model.entity.Tavalod;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class TavalodDA {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Tavalod tavalod) {
        entityManager.persist(tavalod);
    }

    public void update(Tavalod tavalod) {
        entityManager.merge(tavalod);
    }


    public void delete(Tavalod tavalod) {
        Tavalod tavalod1 = entityManager.merge(tavalod);
        entityManager.remove(tavalod1);
    }

    public Tavalod findOne(Class<Tavalod> aClass, Long id) {
        return entityManager.find(aClass, id);
    }


    public Long getSequence() {
        Query query = entityManager.createNativeQuery("SELECT SEQ.NEXTVAL AS SEQ FROM DUAL");
        return ((BigDecimal) query.getSingleResult()).longValue();
    }


    public void addViewNumber(Long id) {
        Tavalod tavalod = entityManager.find(Tavalod.class, id);
        tavalod.setViewNumber(tavalod.getViewNumber() + 1);
        entityManager.merge(tavalod);
    }

    public List<Tavalod> findAll(int index, int lenght, String type, String name) {
        String jpql = "select entity from tavalod entity where entity.type = :t";
        if (name != null) {
            jpql += " and entity.name = :n";
        }
        jpql += " order by entity.id desc";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("t", type);
        if (name != null) {
            query.setParameter("n", name);
        }

        query.setFirstResult(((index - 1) * lenght));
        query.setMaxResults(lenght);
        return query.getResultList();
    }


    public long count(String type, String name) {
        String jpql = "SELECT count(entity) FROM tavalod entity where entity.type = :t";
        if (name != null) {
            jpql += " and entity.name = :n";
        }
        Query query = entityManager.createQuery(jpql);
        query.setParameter("t", type);
        if (name != null) {
            query.setParameter("n", name);
        }
        return (long) query.getSingleResult();
    }

    public List<String> getNames() {
        String jpql = "select distinct(e.name) from tavalod e";
        Query query = entityManager.createQuery(jpql);
        return query.getResultList();
    }


}
