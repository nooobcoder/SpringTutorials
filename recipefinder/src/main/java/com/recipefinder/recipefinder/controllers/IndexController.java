package com.recipefinder.recipefinder.controllers;

import com.recipefinder.recipefinder.model.Category;
import com.recipefinder.recipefinder.model.UnitOfMeasure;
import com.recipefinder.recipefinder.repositories.CategoryRepository;
import com.recipefinder.recipefinder.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    // Constructor based dependency injection
    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"", "/", "index"})
    public String getIndexPage() {
        System.out.println("Some logging for you, enjoy!");

        Optional<Category> categoryOptional = categoryRepository.findByDescription("American");
        Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Teaspoon");

        assert categoryOptional.orElse(null) != null;
        assert unitOfMeasureOptional.orElse(null) != null;

        System.out.println("Category Id: " + categoryOptional.orElse(null).getId());
        System.out.println("UOM Id: " + unitOfMeasureOptional.orElse(null).getId());

        return "index";
    }
}
