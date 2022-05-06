package com.project.demo.services;

import com.project.demo.entities.Role;
import com.project.demo.entities.User;
import com.project.demo.repos.RoleRepo;
import com.project.demo.repos.UsersRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    private final UsersRepo usersRepo;

    @Autowired
    private final RoleRepo roleRepo;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = usersRepo.findByEmail(email);
        if(user == null){
            log.error("User not found in the database");
            throw new UsernameNotFoundException("User not found in the database");
        }else{
            log.info("User found in the database: {}", email);
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role.getRole()));
        });
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

    @Override
    public User saveUser(User user) {
        log.info("Saving new user {} to the database", user.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return usersRepo.save(user);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the database", role.getRole());
        return roleRepo.save(role);
    }

    @Override
    public void addRoleToUser(String email, String roleName) {
        log.info("Adding role {} to user {}", roleName, email);
        User user = usersRepo.findByEmail(email);
        Role role = roleRepo.findByRole(roleName);
        user.getRoles().add(role);
    }

    @Override
    public User getUser(String email) {
        log.info("Fetching user by email {}", email);
        return usersRepo.findByEmail(email);
    }

    @Override
    public User getUser(Long id) {
        log.info("Fetching user by id {}", id);
        return usersRepo.findById(id).orElse(null);
    }

    @Override
    public List<User> getUsers() {
        log.info("Fetching all users");
        return usersRepo.findAll();
    }

    //    public List<User> listAll(){
//        return usersRepo.findAll();
//    }
//
//    public User findUserByEmail(String email){
//        return usersRepo.findByEmail(email);
//    }
//
//    public User findUserById(Long id){
//        return usersRepo.findById(id).orElse(null);
//    }
//
//    public void addUser(User user){
//        usersRepo.save(user);
//    }
//
//    public void updateUser(User user){
//        usersRepo.save(user);
//    }
//
//    public void deleteUser(Long id){
//        usersRepo.deleteById(id);
//    }
//
}