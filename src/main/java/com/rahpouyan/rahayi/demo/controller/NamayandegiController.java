package com.rahpouyan.rahayi.demo.controller;

import com.rahpouyan.rahayi.demo.Information;
import com.rahpouyan.rahayi.demo.model.entity.Namayandegi;
import com.rahpouyan.rahayi.demo.model.service.NamayandegiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/namayandegi")
@CrossOrigin(origins = Information.frontAddress)
public class NamayandegiController {

    @Autowired
    private NamayandegiService namayandegiService;

    @GetMapping("/findAll")
    @ResponseBody
    public Object findAll(@RequestParam(required = false) String city) {
        return namayandegiService.findAll(city.trim());
    }

    @GetMapping("/getCities")
    @ResponseBody
    public Object getCities() {
        return namayandegiService.getAllCities();
    }

    @GetMapping("/findOne")
    @ResponseBody
    public Object getCities(@RequestParam Long id) {
        return namayandegiService.findOne(id);
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Namayandegi namayandegi) {
        namayandegi.setId(namayandegiService.generateSequenceID());
        namayandegiService.save(namayandegi);
        return "redirect:/namayandegi/findAll";
    }

    @PostMapping("/delete")
    public String delete(@ModelAttribute Namayandegi namayandegi) {
        namayandegiService.delete(namayandegi);
        return "redirect:/namayandegi/findAll";
    }


    @PostMapping("/update")
    public String update(@ModelAttribute Namayandegi namayandegi) {
        namayandegiService.update(namayandegi);
        return "redirect:/namayandegi/findAll";
    }


}
