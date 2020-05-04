package com.rahpouyan.rahayi.demo.model.service;

import com.rahpouyan.rahayi.demo.commom.CalendarTool;
import com.rahpouyan.rahayi.demo.model.entity.Image;
import com.rahpouyan.rahayi.demo.model.entity.ImagePost;
import com.rahpouyan.rahayi.demo.model.repository.ImagePostDA;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImagePostService {

    @Autowired
    private ImagePostDA imagePostDA;


    public void save(ImagePost imagePost, String jsonImage) {

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
        imagePost.setReleaseDate(calendarTool.getAmirFormat());
        imagePost.setReleaseTime(calendarTool.getTime());
        imagePost.setImagesList(images);
        imagePostDA.save(imagePost);
    }

    public void update(ImagePost imagePost, String jsonImage) {
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

        imagePost.setImagesList(images);
        imagePostDA.update(imagePost);
    }

    public void delete(ImagePost imagePost) {
        imagePostDA.delete(imagePost);
    }

    public ImagePost findOne(Long id) {
        return imagePostDA.findOne(ImagePost.class, id);
    }

    public List<ImagePost> findAll(int index, int lenght, String type, Integer day, Integer month, Integer year, String city) {
        return imagePostDA.findAll(index, lenght, type, day, month, year, city);
    }

    public List<ImagePost> findAll4() {
        return imagePostDA.findAll4();
    }

    public Long getCount(String type, Integer day, Integer month, Integer year, String city) {
        return imagePostDA.count(type, day, month, year, city);
    }

    public Long generateSequenceID() {
        return imagePostDA.getSequence();
    }

    public void addViewNumber(long id) {
        imagePostDA.addViewNumber(id);
    }

    public List<String> getAllCities() {
        return imagePostDA.getAllCities();
    }


}
