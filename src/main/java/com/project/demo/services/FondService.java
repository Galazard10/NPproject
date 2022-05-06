package com.project.demo.services;

import com.project.demo.entities.Fond;
import com.project.demo.repos.FondsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class FondService {

    @Autowired
    private FondsRepo fondsRepo;

    public List<Fond> listAll(String search, String location, String category) {
//        List<String> categories = categoryRepo.getAll();
        List<String> categories = Arrays.asList("Everyone", "Family", "Elder", "Children");
//        List<String> locations = locationRepo.getAll();
        List<String> locations = Arrays.asList("Almaty", "Karagandy", "Taraz", "Nur-Sultan");

        if(category != null && category.length() > 0){
            categories = new ArrayList<>(Arrays.asList(category.split(",")));
        }
        if(location != null && location.length() > 0){
            locations = Collections.singletonList(location);
        }
        return fondsRepo.searchNew(search, locations, categories);
    }

    public Fond findFondById(Long id){
        return fondsRepo.findById(id).orElse(null);
    }

    public void addFond(Fond fond){
        fondsRepo.save(fond);
    }
}
