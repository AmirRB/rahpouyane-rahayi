package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.Information;
import com.rahpouyan.rahayi.demo.commom.CalendarTool;
import com.rahpouyan.rahayi.demo.commom.UploadFile;
import com.rahpouyan.rahayi.demo.model.entity.Image;
import com.rahpouyan.rahayi.demo.model.entity.TextPost;
import com.rahpouyan.rahayi.demo.model.service.ImageService;
import com.rahpouyan.rahayi.demo.model.service.TextPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
@RequestMapping("/text_post")
@CrossOrigin(origins = Information.frontAddress)
public class TextPostController {

    @Autowired
    private UploadFile uploadFile;

    @Autowired
    private TextPostService textPostService;

    @Autowired
    private ImageService imageService;

    @Value("${upload.address.image}")
    private String uploadImage;

    @Value("${upload.address.voice}")
    private String uploadVoice;


    @PostMapping("/save")
    public String save(
            @ModelAttribute TextPost textPost,
            @RequestParam("imageFile") MultipartFile multipartFileImage,
            @RequestParam(value = "voiceFile", required = false) MultipartFile multipartFileVoice) {


        Long idImage = imageService.generateSequenceID();
        textPost.setId(textPostService.generateSequenceID());
        File file = uploadFile.store(multipartFileImage, Paths.get(uploadImage), idImage, ".jpg", ".png");
        Image image = new Image(file);
        image.setId(idImage);
        image.setFileName(file.getName());
        textPost.setImage(image);
        if (multipartFileVoice != null) {
            File f = uploadFile.store(multipartFileVoice, Paths.get(uploadVoice), textPost.getId(), ".mp3", ".m4a");
            textPost.setVoice(true);
            textPost.setFileName(f.getName());
        }
        textPostService.save(textPost);
        return "redirect:/text_post/findAll?index=1&type=" + textPost.getType()+"&lenght=10";
    }


    @PostMapping("/update")
    public String updateWithImageVoice(@ModelAttribute TextPost textPost,
                                       @RequestParam(value = "imageFile", required = false) MultipartFile multipartFileImage,
                                       @RequestParam(value = "voiceFile", required = false) MultipartFile multipartFileVoice) {
        Image img = textPostService.findOne(textPost.getId()).getImage();
        if (multipartFileImage != null) {
            File file = uploadFile.store(multipartFileImage, Paths.get(uploadImage), img.getId(), ".jpg", ".png");
            Image image = new Image(file);
            image.setFileName(file.getName());
            image.setId(img.getId());
            image.setRecodVersion(img.getRecodVersion());
            textPost.setImage(image);
        } else {
            textPost.setImage(img);
        }

        if (multipartFileVoice != null) {
            File f = uploadFile.store(multipartFileVoice, Paths.get(uploadVoice), textPost.getId(), ".mp3", ".m4a");
            textPost.setFileName(f.getName());
        }
        textPostService.update(textPost);
        return "redirect:/text_post/findAll?index=1&type=" + textPost.getType()+"&lenght=10";
    }


    @PostMapping("/delete")
    public String delete(@ModelAttribute TextPost textPost) {
        textPostService.delete(textPost);
        return "redirect:/text_post/findAll?index=1&type=" + textPost.getType()+"&lenght=10";
    }


    @GetMapping("/findAll")
    @ResponseBody
    public Object findAll(@RequestParam int index, @RequestParam int lenght, @RequestParam String type) {

        return textPostService.findAll(index, lenght, type);
    }

    @GetMapping("/findAll7")
    @ResponseBody
    public Object findAll7(@RequestParam String type) {

        return textPostService.findAll7(type);
    }


    @GetMapping("/findOne")
    @ResponseBody
    public Object findOne(@RequestParam Long idTextPost) {

        return textPostService.findOne(idTextPost);
    }

    @GetMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestParam String type) {
        return textPostService.getCount(type);
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
        textPostService.addViewNumber(id);
    }

}
