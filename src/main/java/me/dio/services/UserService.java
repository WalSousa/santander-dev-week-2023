package me.dio.services;

import me.dio.domain.model.User;
import me.dio.domain.model.UserListDTO;

public interface UserService {

    UserListDTO getAll();
    User findById(Long id);
    User create(User userToCreate);
}
