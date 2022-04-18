package com.project.demo.controllers;

import com.project.demo.entities.Fond;
import com.project.demo.entities.User;
import com.project.demo.repos.FondsRepo;
import com.project.demo.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class HomeController {

    @Autowired
    private FondsRepo fondsRepo;

    @Autowired
    private UsersRepo usersRepo;

    @GetMapping(value = "/")
    public String homepage() {
        return "Hello World!";
    }

    @GetMapping(value = "/fonds")
    public ResponseEntity<List<Fond>> getAllFonds() {
        List<Fond> fonds = fondsRepo.findAll();
        return new ResponseEntity<>(fonds, HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = usersRepo.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long id){
        Optional<User> user = usersRepo.findById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping(value = "/add-user")
    public ResponseEntity<String> toAddUser(@RequestParam("firstname") String firstName,
                                            @RequestParam("lastname") String lastName,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password){
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);
        usersRepo.save(user);
        return new ResponseEntity<>("USER ADDED", HttpStatus.OK);
    }

    @PostMapping(value = "/update-user")
    public ResponseEntity<String> toUpdateUser(@RequestParam("userId") Long id,
                                            @RequestParam("firstname") String firstName,
                                            @RequestParam("lastname") String lastName,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password){
        User user = usersRepo.getById(id);
        if(user != null){
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
            user.setPassword(password);
            usersRepo.save(user);
            return new ResponseEntity<>("USER UPDATED", HttpStatus.OK);
        }
        return new ResponseEntity<>("ERROR", HttpStatus.OK);
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> toDeleteUser(@RequestParam(name = "userId") Long id){
        usersRepo.deleteById(id);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}