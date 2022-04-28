package com.project.demo.controllers;

import com.project.demo.entities.Fond;
import com.project.demo.entities.Role;
import com.project.demo.entities.User;
import com.project.demo.services.FondService;
import com.project.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class HomeController {

    @Autowired
    private FondService fondService;

    @Autowired
    private UserService userService;

//    @Autowired
//    private PasswordEncoder passwordEncoder;

    @GetMapping(value = "/login")
    public String homepage() {
        return "Hello World!";
    }

    @GetMapping(value = "/fonds")
    public ResponseEntity<List<Fond>> getAllFonds(@RequestParam(name = "search", defaultValue = "", required = false) String search,
                                                  @Param("location") String location,
                                                  @Param("category") String category) {
        List<Fond> fonds = fondService.listAll(search, location, category);
        return new ResponseEntity<>(fonds, HttpStatus.OK);
    }

    @PostMapping(value = "/add-fond")
    public ResponseEntity<String> toAddFond(@RequestParam("fondTitle") String title,
                                            @RequestParam("fondDescription") String description,
                                            @RequestParam("location") String location,
                                            @RequestParam("img") String img){
        Fond fond = new Fond();
        fond.setTitle(title);
        fond.setDescription(description);
        fond.setLocation(location);
        fond.setImg(img);
        fondService.addFond(fond);
        return new ResponseEntity<>("FOND ADDED", HttpStatus.OK);
    }

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userService.listAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long id){
        User user = userService.findUserById(id);
        return new ResponseEntity(user, HttpStatus.OK);
    }

    @PostMapping(value = "/add-user")
    public ResponseEntity<String> toAddUser(@RequestParam("firstname") String firstName,
                                            @RequestParam("lastname") String lastName,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password){
        User exists = userService.findUserByEmail(email);
        if(exists != null) {
            User user = new User();
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
//            user.setPassword(passwordEncoder.encode(password));
            user.setPassword(password);
            user.setRoles(Collections.singleton(Role.USER));
            userService.addUser(user);
            return new ResponseEntity<>("USER ADDED", HttpStatus.OK);
        }
        return new ResponseEntity<>("ERROR", HttpStatus.OK);
    }

    @PostMapping(value = "/update-user")
    public ResponseEntity<String> toUpdateUser(@RequestParam("userId") Long id,
                                            @RequestParam("firstname") String firstName,
                                            @RequestParam("lastname") String lastName,
                                            @RequestParam("email") String email,
                                            @RequestParam("password") String password){
        User user = userService.findUserById(id);
        if(user != null){
            user.setFirstname(firstName);
            user.setLastname(lastName);
            user.setEmail(email);
//            user.setPassword(passwordEncoder.encode(password));
            user.setPassword(password);
            userService.updateUser(user);
            return new ResponseEntity<>("USER UPDATED", HttpStatus.OK);
        }
        return new ResponseEntity<>("ERROR", HttpStatus.OK);
    }

    @PostMapping(value = "/delete-user")
    public ResponseEntity<String> toDeleteUser(@RequestParam(name = "userId") Long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}