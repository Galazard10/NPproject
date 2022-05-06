package com.project.demo.controllers;

import com.project.demo.entities.Fond;
import com.project.demo.services.FondServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
@CrossOrigin
public class HomeController {

    @Autowired
    private FondServiceImpl fondService;

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

    @GetMapping(value = "/fonds/{fondId}")
    public ResponseEntity<Fond> getFondById(@PathVariable(name = "fondId") Long id){
        Fond fond = fondService.findFondById(id);
        return new ResponseEntity(fond, HttpStatus.OK);
    }

    @PostMapping(value = "/add-fond")
    public ResponseEntity<String> toAddFond(@RequestBody Fond fond){
        fondService.addFond(fond);
        return new ResponseEntity<>("FOND ADDED", HttpStatus.OK);
    }

//    @GetMapping(value = "/users")
//    public ResponseEntity<List<User>> getAllUsers(){
//        List<User> users = userService.listAll();
//        return new ResponseEntity<>(users, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/users/{userId}")
//    public ResponseEntity<User> getUserById(@PathVariable(name = "userId") Long id){
//        User user = userService.findUserById(id);
//        return new ResponseEntity(user, HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/add-user")
//    public ResponseEntity<String> toAddUser(@RequestBody User user){
//        User exists = userService.findUserByEmail(user.getEmail());
//        if(exists != null) {
//            userService.addUser(user);
//            return new ResponseEntity<>("USER ADDED", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("ERROR", HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/update-user")
//    public ResponseEntity<String> toUpdateUser(@RequestBody User user){
//        User exists = userService.findUserById(user.getId());
//        if(exists != null){
//            userService.updateUser(user);
//            return new ResponseEntity<>("USER UPDATED", HttpStatus.OK);
//        }
//        return new ResponseEntity<>("ERROR", HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/delete-user")
//    public ResponseEntity<String> toDeleteUser(@RequestParam(name = "userId") Long id){
//        userService.deleteUser(id);
//        return new ResponseEntity<>("DELETED", HttpStatus.OK);
//    }
}