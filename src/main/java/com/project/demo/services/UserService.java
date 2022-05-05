package com.project.demo.services;

import com.project.demo.entities.User;
import com.project.demo.repos.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    public List<User> listAll(){
        return usersRepo.findAll();
    }

    public User findUserByEmail(String email){
        return usersRepo.findByEmail(email);
    }

    public User findUserById(Long id){
        return usersRepo.findById(id).orElse(null);
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

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepo.findByEmail(email);
        if(user != null)
            return user;
        else
            throw new UsernameNotFoundException("USER NOT FOUND");
    }
}
