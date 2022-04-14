package com.project.demo.controllers;

import com.project.demo.entities.FondEntity;
import com.project.demo.repos.FondsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class HomeController {

    @Autowired
    private FondsRepo fondsRepo;

    @GetMapping(value = "/")
    public String homepage(){
        return "Hello World!";
    }

    @GetMapping(value = "/fonds")
    public ResponseEntity<List<FondEntity>> getAllFonds(){
        List<FondEntity> fonds = fondsRepo.findAll();
        return new ResponseEntity<>(fonds, HttpStatus.OK);
    }
}
