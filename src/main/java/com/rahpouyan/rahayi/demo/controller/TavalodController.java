package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.commom.CalendarTool;
import com.rahpouyan.rahayi.demo.commom.CustomException;
import com.rahpouyan.rahayi.demo.commom.UploadFile;
import com.rahpouyan.rahayi.demo.model.entity.Tavalod;
import com.rahpouyan.rahayi.demo.model.service.TavalodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;

@Controller
@RequestMapping("/tavalod")
@CrossOrigin(origins = "http://localhost:4200")
public class TavalodController {

    @Autowired
    private TavalodService tavalodService;

    @Autowired
    private UploadFile uploadFile;

    @Value("${upload.address.voice}")
    private String uploadVoice;

    @ResponseBody
    @GetMapping("/findAll")
    public Object findAll(@RequestParam int index, @RequestParam int lenght, @RequestParam String type, @RequestParam(required = false) String name) {
        return tavalodService.findAll(index, lenght, type, name);
    }

    @ResponseBody
    @GetMapping("/findOne")
    public Object findOne(@RequestParam Long id) throws CustomException {
        return tavalodService.findOne(id);
    }

    @ResponseBody
    @GetMapping("/error")
    public String error(@RequestParam String error) {
        return error;
    }


    @PostMapping("/save")
    public String save(
            RedirectAttributes attributes,
            @ModelAttribute Tavalod tavalod,
            @RequestParam(required = false) String jsonImage,
            @RequestParam(value = "voiceFile", required = false) MultipartFile multipartFileVoice
    ) throws Exception {


        tavalod.setId(tavalodService.generateSequenceID());
        if (multipartFileVoice != null) {
            File f = uploadFile.store(multipartFileVoice, Paths.get(uploadVoice), tavalod.getId(), ".mp3", ".m4a");
            tavalod.setVoice(true);
            tavalod.setVoiceFileName(f.getName());
        } else
            tavalod.setVoice(false);

        tavalodService.save(tavalod, jsonImage);
        attributes.addAttribute("index", 1);
        attributes.addAttribute("type", tavalod.getType());
        attributes.addAttribute("lenght", 20);
        return "redirect:/tavalod/findAll";


    }


    @PostMapping("/update")
    public String update(
            RedirectAttributes attributes,
            @ModelAttribute Tavalod tavalod,
            @RequestParam(required = false) String jsonImage,
            @RequestParam(value = "voiceFile", required = false) MultipartFile multipartFileVoice

    ) throws Exception {


        if (multipartFileVoice != null) {
            File f = uploadFile.store(multipartFileVoice, Paths.get(uploadVoice), tavalod.getId(), ".mp3", ".m4a");
            tavalod.setVoice(true);
            tavalod.setVoiceFileName(f.getName());
        }

        tavalodService.update(tavalod, jsonImage);
        attributes.addAttribute("index", 1);
        attributes.addAttribute("type", tavalod.getType());
        attributes.addAttribute("lenght", 20);
        return "redirect:/tavalod/findAll";


    }

    @PostMapping("/delete")
    public String delete(RedirectAttributes attributes, @ModelAttribute Tavalod tavalod) throws Exception {

        tavalodService.delete(tavalod);

        attributes.addAttribute("index", 1);
        attributes.addAttribute("type", tavalod.getType());
        attributes.addAttribute("lenght", 20);

        return "redirect:/tavalod/findAll";


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


    @GetMapping("/getCount")
    @ResponseBody
    public Object getCount(@RequestParam String type, @RequestParam(required = false) String name) {
        return tavalodService.count(type, name);
    }

    @GetMapping("/getNames")
    @ResponseBody
    public Object getNames() {
        return tavalodService.getNames();
    }


}
