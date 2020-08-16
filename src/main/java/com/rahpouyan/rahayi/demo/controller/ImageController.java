package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.Information;
import com.rahpouyan.rahayi.demo.commom.UploadFile;
import com.rahpouyan.rahayi.demo.model.entity.Image;
import com.rahpouyan.rahayi.demo.model.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/image")
@CrossOrigin(origins = Information.frontAddress)
public class ImageController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UploadFile uploadFile;

    @Value("${upload.address.image}")
    private String uploadImage;

    @Value("${upload.address.voice}")
    private String uploadVoice;

    @GetMapping("/findAll")
    @ResponseBody
    public Object findAll(@RequestParam int index) {
        return imageService.findAll(index);
    }


    @GetMapping("/findOne")
    @ResponseBody
    public Object findOne(@RequestParam Long id) {
        return imageService.findOne(id);
    }

    @PostMapping("/save")
    @ResponseBody
    public Object save(@RequestParam("images") MultipartFile[] multipartFiles) {

        List<Image> images = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            Long idImage = imageService.generateSequenceID();
            File file = uploadFile.store(multipartFile, Paths.get(uploadImage),idImage,".jpg", ".png");
            Image image = new Image(file);
            image.setFileName(file.getName());
            image.setId(idImage);
            images.add(image);
        }
        return images;
    }


}
