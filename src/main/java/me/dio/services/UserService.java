package me.dio.services;

import me.dio.domain.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User findById(Long id);
    User create(User userToCreate);
}
