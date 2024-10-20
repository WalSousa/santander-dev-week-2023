package me.dio.services.impl;

import me.dio.domain.model.User;
import me.dio.domain.repository.UserRepository;
import me.dio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id){
        try {
            return userRepository.findById(id).orElseThrow();
        }
        catch (Exception throwable){
            throw new NoSuchElementException(throwable.getMessage());
        }
    }

    @Override
    public User create(User userToCreate) {
        if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())){
            throw new IllegalArgumentException("this user Account number already exists");
        }
        return userRepository.save(userToCreate);
    }
}
