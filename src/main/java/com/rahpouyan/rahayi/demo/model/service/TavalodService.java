package com.rahpouyan.rahayi.demo.model.service;

import com.rahpouyan.rahayi.demo.commom.CalendarTool;
import com.rahpouyan.rahayi.demo.commom.CustomException;
import com.rahpouyan.rahayi.demo.commom.ExceptionWrapper;
import com.rahpouyan.rahayi.demo.model.entity.Image;
import com.rahpouyan.rahayi.demo.model.entity.Tavalod;
import com.rahpouyan.rahayi.demo.model.repository.TavalodDA;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TavalodService {

    @Autowired
    private TavalodDA tavalodDA;

    public void save(Tavalod tavalod, String jsonImage) throws CustomException {

        if (jsonImage == null) {
            throw new CustomException("jsonImage is null");
        }


        List<Image> images = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonImage);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                Image image = new Image();
                image.setId((Long) jsonObject.get("id"));
                image.setRecodVersion(((Long) jsonObject.get("recodVersion")).intValue());
                image.setWidth(((Long) jsonObject.get("width")).intValue());
                image.setHeight(((Long) jsonObject.get("height")).intValue());
                image.setText((String) jsonObject.get("text"));
                image.setFileName((String) jsonObject.get("fileName"));
                images.add(image);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        CalendarTool calendarTool = new CalendarTool();
        tavalod.setReleaseDate(calendarTool.getAmirFormat());
        tavalod.setReleaseTime(calendarTool.getTime());
        tavalod.setImagesList(images);
        tavalodDA.save(tavalod);
    }

    public void update(Tavalod tavalod, String jsonImage) throws Exception {


        if (jsonImage == null) {
            throw new CustomException("jsonImage is null");
        }


        if (tavalod.getId() == null) {
            throw new CustomException("this api need Id");
        }

        if (tavalod.getReleaseDate() == null) {
            throw new CustomException("releaseDate is null");
        }

        if (tavalod.getReleaseTime() == null) {
            throw new CustomException("releaseTime is null");
        }

        if (tavalod.getRecordVersion() == null) {
            throw new CustomException("recordVersion is null");
        }


        List<Image> images = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonImage);
            for (Object o : jsonArray) {
                JSONObject jsonObject = (JSONObject) o;
                Image image = new Image();
                image.setId((Long) jsonObject.get("id"));
                image.setRecodVersion(((Long) jsonObject.get("recodVersion")).intValue());
                image.setWidth(((Long) jsonObject.get("width")).intValue());
                image.setHeight(((Long) jsonObject.get("height")).intValue());
                image.setText((String) jsonObject.get("text"));
                image.setFileName((String) jsonObject.get("fileName"));
                images.add(image);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        tavalod.setImagesList(images);
        tavalodDA.update(tavalod);
    }

    public void delete(Tavalod tavalod) throws CustomException {

        if (tavalod.getId() == null) {
            throw new CustomException("this api need Id");
        }

        if (tavalod.getReleaseDate() == null) {
            throw new CustomException("releaseDate is null");
        }

        if (tavalod.getReleaseTime() == null) {
            throw new CustomException("releaseTime is null");
        }

        if (tavalod.getRecordVersion() == null) {
            throw new CustomException("recordVersion is null");
        }

        tavalodDA.delete(tavalod);
    }

    public Tavalod findOne(Long id) throws CustomException {
        if (id == null) {
            throw new CustomException("id is null");
        }
        return tavalodDA.findOne(Tavalod.class, id);
    }

    public List<Tavalod> findAll(int index, int lenght, String type, String name) {

        return tavalodDA.findAll(index, lenght, type, name);
    }

    public Long generateSequenceID() {
        return tavalodDA.getSequence();
    }

    public void addViewNumber(long id) throws Exception {
        if (id == 0) {
            throw new CustomException("id is null");
        }
        tavalodDA.addViewNumber(id);
    }

    public long count(String type, String name) {
        return tavalodDA.count(type, name);
    }

    public List<String> getNames() {
        return tavalodDA.getNames();
    }

}
