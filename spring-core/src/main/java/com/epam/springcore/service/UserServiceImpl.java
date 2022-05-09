package com.epam.springcore.service;

import com.epam.springcore.model.User;
import com.epam.springcore.repository.UserRepository;
import java.util.List;

public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;

  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public User findUserById(long userId) {
    return userRepository.findUserById(userId);
  }

  @Override
  public User findUserByEmail(String email) {
    return userRepository.findUserByEmail(email);
  }

  @Override
  public List<User> findUsersByName(String name, int pageSize, int pageNum) {
    return userRepository.findUsersByName(name, pageSize, pageNum);
  }

  @Override
  public User createUser(User user) {
    return userRepository.createUser(user);
  }

  @Override
  public User updateUser(User user) {
    return userRepository.updateUser(user);
  }

  @Override
  public boolean deleteUser(long userId) {
    return userRepository.deleteUser(userId);
  }
}
