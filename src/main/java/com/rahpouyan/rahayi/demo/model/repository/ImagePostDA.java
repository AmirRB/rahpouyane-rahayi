package com.rahpouyan.rahayi.demo.model.repository;

import com.rahpouyan.rahayi.demo.model.entity.ImagePost;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class ImagePostDA {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(ImagePost imagePost) {
        entityManager.persist(imagePost);
    }

    public void update(ImagePost imagePost) {
        entityManager.merge(imagePost);
    }

    public void delete(ImagePost imagePost) {
        ImagePost imagePost1 = entityManager.merge(imagePost);
        entityManager.remove(imagePost1);
    }


    public ImagePost findOne(Class<ImagePost> aClass, Long id) {
        return entityManager.find(aClass, id);
    }


    public List<ImagePost> findAll(int index, int lenght, String type, Integer day, Integer month, Integer year, String city) {

        String jpql = "select entity from imagePost entity where entity.type = :t";
        if (city != null) {
            jpql += " and entity.city = :c";
        }
        if (year != null) {
            jpql += " and entity.year = :y";
        }

        if (month != null) {
            jpql += " and entity.month = :m";
        }

        if (day != null) {
            jpql += " and entity.day = :d";
        }


        jpql += " order by entity.id desc";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("t", type);

        if (city != null)
            query.setParameter("c", city);

        if (year != null)
            query.setParameter("y", year);

        if (month != null)
            query.setParameter("m", month);

        if (day != null)
            query.setParameter("d", day);

        query.setFirstResult(((index - 1) * lenght));
        query.setMaxResults(lenght);
        return query.getResultList();
    }

    public List<ImagePost> findAll4() {
        Query query = entityManager.createQuery("select entity from imagePost entity order by entity.id desc");
        query.setFirstResult(0);
        query.setMaxResults(4);
        return query.getResultList();
    }


    public Long getSequence() {
        Query query = entityManager.createNativeQuery("SELECT SEQ.NEXTVAL AS SEQ FROM DUAL");
        return ((BigDecimal) query.getSingleResult()).longValue();
    }

    public long count(String type, Integer day, Integer month, Integer year, String city) {
        String jpql = "SELECT count(entity) FROM imagePost entity where entity.type = :t";


        if (city != null) {
            jpql += " and entity.city = :c";
        }
        if (year != null) {
            jpql += " and entity.year = :y";
        }

        if (month != null) {
            jpql += " and entity.month = :m";
        }

        if (day != null) {
            jpql += " and entity.day = :d";
        }


        Query query = entityManager.createQuery(jpql);

        query.setParameter("t", type);

        if (city != null)
            query.setParameter("c", city);

        if (year != null)
            query.setParameter("y", year);

        if (month != null)
            query.setParameter("m", month);

        if (day != null)
            query.setParameter("d", day);

        return (long) query.getSingleResult();
    }

    public void addViewNumber(Long id) {
        ImagePost imagePost = entityManager.find(ImagePost.class, id);
        imagePost.setViewNumber(imagePost.getViewNumber() + 1);
        entityManager.merge(imagePost);
    }

    public List<String> getAllCities() {
        Query query = entityManager.createQuery("select distinct(e.city) from imagePost e where e.city is not null ");
        return query.getResultList();
    }

}
