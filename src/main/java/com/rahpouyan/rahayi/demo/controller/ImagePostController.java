package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.Information;
import com.rahpouyan.rahayi.demo.commom.CalendarTool;
import com.rahpouyan.rahayi.demo.commom.UploadFile;
import com.rahpouyan.rahayi.demo.model.entity.ImagePost;
import com.rahpouyan.rahayi.demo.model.service.ImagePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
@RequestMapping("image-post")
@CrossOrigin(origins = Information.frontAddress)
public class ImagePostController {

    @Autowired
    private ImagePostService imagePostService;

    @Autowired
    private UploadFile uploadFile;


    @Value("${upload.address.voice}")
    private String uploadVoice;


    @PostMapping("/save")
    public String save(
            @RequestParam String jsonImage,
            @ModelAttribute ImagePost imagePost,
            @RequestParam(value = "voiceFile", required = false) MultipartFile multipartFileVoice
    ) {

        imagePost.setId(imagePostService.generateSequenceID());
        if (multipartFileVoice != null) {
            File f = uploadFile.store(multipartFileVoice, Paths.get(uploadVoice), imagePost.getId(), ".mp3", ".m4a");
            imagePost.setVoice(true);
            imagePost.setFileName(f.getName());
        } else
            imagePost.setVoice(false);
        imagePostService.save(imagePost, jsonImage);
        return "redirect:/image-post/findAll?index=1&type=" + imagePost.getType() + "&lenght=20";
    }


    @GetMapping("/findAll")
    @ResponseBody
    public Object findAll(
            @RequestParam int index,
            @RequestParam int lenght,
            @RequestParam String type,
            @RequestParam(required = false) Integer day,
            @RequestParam(required = false) Integer month,
            @RequestParam(required = false) Integer year,
            @RequestParam(required = false) String city
    ) {
        return imagePostService.findAll(index, lenght, type, day, month, year, city);
    }

    @GetMapping("/findAll4")
    @ResponseBody
    public Object findAll4() {
        return imagePostService.findAll4();
    }

    @GetMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestParam String type,
                           @RequestParam(required = false) Integer day,
                           @RequestParam(required = false) Integer month,
                           @RequestParam(required = false) Integer year,
                           @RequestParam(required = false) String city) {
        return imagePostService.getCount(type, day, month, year, city);
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Object findOne(@RequestParam Long idImagePost) {
        return imagePostService.findOne(idImagePost);
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute ImagePost imagePost) {
        imagePostService.delete(imagePost);
        return "redirect:/image-post/findAll?index=1&type=" + imagePost.getType() + "&lenght=20";
    }

    @PostMapping("/update")
    public String update(
            @RequestParam String jsonImage,
            @ModelAttribute ImagePost imagePost,
            @RequestParam(value = "voiceFile", required = false) MultipartFile multipartFileVoice
    ) {

        if (multipartFileVoice != null) {
            File f = uploadFile.store(multipartFileVoice, Paths.get(uploadVoice), imagePost.getId(), ".mp3", ".m4a");
            imagePost.setVoice(true);
            imagePost.setFileName(f.getName());
        }

        imagePostService.update(imagePost, jsonImage);

        return "redirect:/image-post/findAll?index=1&type=" + imagePost.getType() + "&lenght=20";
    }

    @ResponseBody
    @RequestMapping("getDate")
    public Object getDate() {

        CalendarTool calendarTool = new CalendarTool();
        HashMap<String, Integer> hashMap = new HashMap();
        hashMap.put("day", calendarTool.getIranianDay());
        hashMap.put("month", calendarTool.getIranianMonth());
        hashMap.put("year", calendarTool.getIranianYear());

        return hashMap;

    }


    @ResponseBody
    @RequestMapping("increment")
    public void inc(@RequestParam long id) {
        imagePostService.addViewNumber(id);
    }

    @ResponseBody
    @RequestMapping("getCities")
    public Object getCities() {
        return imagePostService.getAllCities();
    }

}
