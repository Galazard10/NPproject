package com.project.demo.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.project.demo.entities.Donations;
import com.project.demo.entities.User;
import com.project.demo.services.DonationsService;
import com.project.demo.services.FondService;
import com.project.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.List;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin
public class DonationController {

    @Autowired
    private DonationsService donationsService;

    @Autowired
    private UserService userService;

    @Autowired
    private FondService fondService;

    @GetMapping(value = "/donations")
    public ResponseEntity<List<Donations>> getAllDonations(){
        List<Donations> donations = donationsService.listAll();
        return new ResponseEntity(donations, HttpStatus.OK);
    }

    @GetMapping(value = "/donations/{donationId}")
    public ResponseEntity<Donations> getDonationById(@PathVariable(name = "donationId") Long donationId){
        Donations donation = donationsService.findDonationById(donationId);
        return new ResponseEntity(donation, HttpStatus.OK);
    }

    @PostMapping(value = "/donations/add-donation/{fondId}")
    public ResponseEntity<String> toAddDonation(@RequestBody Donations donation,
                                                @PathVariable(name = "fondId") Long fondId){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(!(auth instanceof AnonymousAuthenticationToken)){
            String email = (String)auth.getPrincipal();
            donation.setUser(userService.getUserByEmail(email));
        }
        donation.setFond(fondService.findFondById(fondId));
        donationsService.addDonation(donation);
        return new ResponseEntity("DONATION ADDED", HttpStatus.OK);
    }
}