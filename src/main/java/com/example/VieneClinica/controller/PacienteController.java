package com.example.VieneClinica.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/pacientes")
@RequestMapping

public class PacienteController {

    @GetMapping
    public String helloWorld(){
        return "Hello world";
    }
}
