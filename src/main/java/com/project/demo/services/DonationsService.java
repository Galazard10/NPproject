package com.project.demo.services;

import com.project.demo.entities.Donations;
import com.project.demo.repos.DonationsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DonationsService {

    @Autowired
    private DonationsRepo donationsRepo;

    public List<Donations> listAll(){
        return donationsRepo.findAll();
    }

    public Donations findDonationById(Long id){
        return donationsRepo.findById(id).orElse(null);
    }

    public void addDonation(Donations donation){
        donationsRepo.save(donation);
    }
}
