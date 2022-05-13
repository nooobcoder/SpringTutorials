package com.example.springpetclinic.controllers;

import com.example.springpetclinic.services.VetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VetController {

	private final VetService vetService;

	VetController(VetService vetService) {
		this.vetService = vetService;
	}

	@GetMapping({ "/vets/index", "/vets/index.html", "/vets.html", "/vets" })
	public String listVets(Model model) {
		model.addAttribute("vets", vetService.findAll());

		return "vets/index";
	}

}
