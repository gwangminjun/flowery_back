package com.example.flowery_backend.Controller;

import com.example.flowery_backend.Service.FlowerService;
import com.example.flowery_backend.model.Flower;
import com.example.flowery_backend.model.request.FlowerRequest;
import com.example.flowery_backend.model.response.FlowerListResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.xml.bind.JAXBException;
import java.io.UnsupportedEncodingException;
import java.util.List;


@Controller
public class HomeController {
    private final FlowerService flowerService;

    public HomeController(FlowerService flowerService) {
        this.flowerService = flowerService;
    }

    @GetMapping("/")
    public String home(Model model) throws JAXBException {
        flowerService.sync();
        model.addAttribute("flowers", flowerService.getFlowers()); // Add the flowers list to the model
        return "index";
    }




}
