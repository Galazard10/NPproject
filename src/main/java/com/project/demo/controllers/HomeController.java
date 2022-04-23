package com.project.demo.controllers;

import com.project.demo.entities.Fond;
import com.project.demo.entities.User;
import com.project.demo.repos.FondsRepo;
import com.project.demo.repos.UsersRepo;
import com.project.demo.services.FondService;
import com.project.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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
    private FondService fondService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/")
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
                                            @RequestParam("fondAmount") Integer amount,
                                            @RequestParam("fondDescription") String description,
                                            @RequestParam("location") String location){
        Fond fond = new Fond();
        fond.setTitle(title);
        fond.setAmount(amount);
        fond.setDescription(description);
        fond.setLocation(location);
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
        User user = new User();
        user.setFirstname(firstName);
        user.setLastname(lastName);
        user.setEmail(email);
        user.setPassword(password);
        userService.addUser(user);
        return new ResponseEntity<>("USER ADDED", HttpStatus.OK);
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