package com.project.demo.services;

import com.project.demo.entities.User;
import com.project.demo.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UsersRepo usersRepo;

    public List<User> listAll(){
        return usersRepo.findAll();
    }

    public User findUserByEmail(String email){
        return usersRepo.findByEmail(email);
    }

    public User findUserById(Long id){
        return usersRepo.getById(id);
    }

    public void addUser(User user){
        usersRepo.save(user);
    }

    public void updateUser(User user){
        usersRepo.save(user);
    }

    public void deleteUser(Long id){
        usersRepo.deleteById(id);
    }
}
