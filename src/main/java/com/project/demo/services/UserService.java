package com.project.demo.services;

import com.project.demo.entities.Role;
import com.project.demo.entities.User;

import java.util.List;

public interface UserService {
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String email, String roleName);
    User getUserByEmail(String email);
    List<User> getUsers();
    Boolean checkByEmail(String email);
    User getUserById(Long id);
}
