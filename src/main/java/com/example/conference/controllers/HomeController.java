package com.example.conference.controllers;

import com.example.conference.models.Session;
import com.example.conference.repositories.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {
    @RequestMapping("/")
    public String printHelloWorld() {
        return "Greetings from Spring Boot!";
    }

}
