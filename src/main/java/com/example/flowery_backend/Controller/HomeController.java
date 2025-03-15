package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.FlowerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {
    private final FlowerService flowerService;

    public HomeController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("flowers", flowerService.getFlowers()); // Add the flowers list to the model
        return "index";
    }




}
