package com.epam.springcore.repository;

import com.epam.springcore.model.User;
import com.epam.springcore.repository.storage.Storage;
import java.util.List;

public interface UserRepository {

  void setUserStorage(Storage<User> storage);

  User findUserById(long userId);

  User findUserByEmail(String email);

  List<User> findUsersByName(String name, int pageSize, int pageNum);

  User createUser(User user);

  User updateUser(User user);

  boolean deleteUser(long userId);
}
