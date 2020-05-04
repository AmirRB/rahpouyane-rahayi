package com.rahpouyan.rahayi.demo.model.service;


import com.rahpouyan.rahayi.demo.model.entity.Image;
import com.rahpouyan.rahayi.demo.model.repository.ImageDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageDA imageDA;

    public void save(Image image) {
        imageDA.save(image);
    }

    public void update(Image image) {
        imageDA.update(image);
    }

    public void delete(Image image) {
        imageDA.delete(image);
    }

    public Image findOne(Long id) {
        return imageDA.findOne(Image.class, id);
    }

    public List<Image> findAll(int index) {
        return imageDA.findAll(index);
    }

    public Long generateSequenceID() {
        return imageDA.getSequence();
    }

}
