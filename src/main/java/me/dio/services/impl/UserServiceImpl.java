package me.dio.services.impl;

import me.dio.domain.model.ListUserDto;
import me.dio.domain.model.SimplePageable;
import me.dio.domain.model.User;
import me.dio.domain.model.UserListDTO;
import me.dio.domain.repository.UserRepository;
import me.dio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
  public UserListDTO getAll() {

    List<User> users = userRepository.findAll();

    UserListDTO userListDTO = new UserListDTO();
    List<ListUserDto> userDtos = new ArrayList<>(users.size());

    for (User item : users) {
      ListUserDto dto = new ListUserDto();

      dto.setId(item.getId());
      dto.setName(item.getName());

      userDtos.add(dto);
    }

    userListDTO.setPageable(new SimplePageable());
    userListDTO.setUsers(userDtos);

    return userListDTO;
  }

  @Override
  public User findById(Long id) {
    try {
      return userRepository.findById(id).orElseThrow();
    } catch (Exception throwable) {
      throw new NoSuchElementException(throwable.getMessage());
    }
  }

  @Override
  public User create(User userToCreate) {
    if (userRepository.existsByAccountNumber(userToCreate.getAccount().getNumber())) {
      throw new IllegalArgumentException("this user Account number already exists");
    }
    return userRepository.save(userToCreate);
  }
}
