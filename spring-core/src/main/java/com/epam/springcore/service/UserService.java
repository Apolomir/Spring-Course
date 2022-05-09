package com.epam.springcore.service;

import com.epam.springcore.model.User;
import java.util.List;

public interface UserService {

  User findUserById(long userId);

  User findUserByEmail(String email);

  List<User> findUsersByName(String name, int pageSize, int pageNum);

  User createUser(User user);

  User updateUser(User user);

  boolean deleteUser(long userId);
}
