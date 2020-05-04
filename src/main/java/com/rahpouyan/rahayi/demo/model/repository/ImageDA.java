package com.rahpouyan.rahayi.demo.model.repository;


import com.rahpouyan.rahayi.demo.model.entity.Image;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public class ImageDA {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Image image) {
        entityManager.persist(image);
    }

    public void update(Image image) {
        /*Image image1 = entityManager.find(Image.class,image.getId());*/
        entityManager.merge(image);
    }

    public void delete(Image image ) {
        Image image1 = entityManager.merge(image);
        entityManager.remove(image1);
    }


    public Image findOne(Class<Image> aClass, Long id) {
        return entityManager.find(aClass, id);
    }

    public List<Image> findAll(int index) {
        int lenght = 10;
        Query query = entityManager.createQuery("select entity from image entity order by entity.id desc");
        query.setFirstResult(((index - 1) * lenght));
        query.setMaxResults(lenght);
        return query.getResultList();
    }


    public Long getSequence() {
        Query query = entityManager.createNativeQuery("SELECT SEQIMG.NEXTVAL AS SEQIMG FROM DUAL");
        return ((BigDecimal) query.getSingleResult()).longValue();
    }



}
