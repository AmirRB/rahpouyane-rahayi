package com.rahpouyan.rahayi.demo.model.repository;

import com.rahpouyan.rahayi.demo.model.entity.Namayandegi;
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
public class NamayandegiDA {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Namayandegi namayandegi) {
        entityManager.persist(namayandegi);
    }

    public List<Namayandegi> findAll(String city) {
        Query query;
        if (city != null) {
            query = entityManager.createQuery("select entity from namayandegi entity where entity.city = :c order by entity.id desc ");
            query.setParameter("c", city);
        } else {
            query = entityManager.createQuery("select entity from namayandegi entity order by entity.id desc ");
        }
        return query.getResultList();
    }

    public Long getSequence() {
        Query query = entityManager.createNativeQuery("SELECT SEQ_NAMAYANDEGI.NEXTVAL AS SEQ FROM DUAL");
        return ((BigDecimal) query.getSingleResult()).longValue();
    }

    public List<String> getAllCities() {
        Query query = entityManager.createQuery("select distinct(e.city) from namayandegi e ");
        return query.getResultList();
    }

    public void update(Namayandegi namayandegi) {
        entityManager.merge(namayandegi);
    }

    public void delete(Namayandegi namayandegi) {
        Namayandegi namayandegi1 = entityManager.merge(namayandegi);
        entityManager.remove(namayandegi1);
    }


    public Namayandegi findOne(Class<Namayandegi> aClass, Long id) {
        return entityManager.find(aClass, id);
    }

}
