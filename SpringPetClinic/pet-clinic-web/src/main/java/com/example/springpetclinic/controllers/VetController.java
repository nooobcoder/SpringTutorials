package com.example.springpetclinic.controllers;

import com.example.springpetclinic.model.Vet;
import com.example.springpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Set;

@Controller
public class VetController {

    private final VetService vetService;

    VetController(VetService vetService) {
        this.vetService = vetService;
    }

    @GetMapping({"/vets/index", "/vets/index.html", "/vets.html", "/vets"})
    public String listVets(Model model) {
        model.addAttribute("vets", vetService.findAll());

        return "vets/index";
    }

    @GetMapping("/api/vets")
    public @ResponseBody Set<Vet> getVetsJson() {
        return vetService.findAll();
    }

}
