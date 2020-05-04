package com.rahpouyan.rahayi.demo.model.service;

import com.rahpouyan.rahayi.demo.model.entity.Namayandegi;
import com.rahpouyan.rahayi.demo.model.repository.NamayandegiDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NamayandegiService {

    @Autowired
    private NamayandegiDA namayandegiDA;

    public List<Namayandegi> findAll(String city) {
        return namayandegiDA.findAll(city);
    }


    public void save(Namayandegi namayandegi) {
        namayandegiDA.save(namayandegi);
    }

    public Long generateSequenceID() {
        return namayandegiDA.getSequence();
    }

    public List<String> getAllCities() {
        return namayandegiDA.getAllCities();
    }

    public void update(Namayandegi namayandegi) {
        namayandegiDA.update(namayandegi);
    }

    public void delete(Namayandegi namayandegi) {
        namayandegiDA.delete(namayandegi);
    }

    public Namayandegi findOne(Long id) {
        return namayandegiDA.findOne(Namayandegi.class,id);
    }

}
