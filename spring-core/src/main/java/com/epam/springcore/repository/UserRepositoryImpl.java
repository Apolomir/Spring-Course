package com.epam.springcore.repository;

import com.epam.springcore.model.User;
import com.epam.springcore.repository.storage.Storage;
import com.epam.springcore.utils.PaginationUtils;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserRepositoryImpl implements UserRepository {

  private Storage<User> userStorage;

  @Override
  public void setUserStorage(Storage<User> userStorage) {
    this.userStorage = userStorage;
  }

  @Override
  public User findUserById(long userId) {
    return userStorage.getStorage().get(userId);
  }

  @Override
  public User findUserByEmail(String email) {
    final Optional<User> optionalUser =
        userStorage.getStorage().values().stream()
            .filter(user -> email.equals(user.getEmail()))
            .findFirst();
    User result = null;
    if (optionalUser.isPresent()) {
      result = optionalUser.get();
    }
    return result;
  }

  @Override
  public List<User> findUsersByName(String name, int pageSize, int pageNum) {
    final List<User> users =
        userStorage.getStorage().values().stream()
            .filter(user -> user.getName().contains(name))
            .collect(Collectors.toList());
    return PaginationUtils.paginateList(users, pageSize, pageNum);
  }

  @Override
  public User createUser(User user) {
    long userId = userStorage.getStorage().keySet().stream().max(Long::compareTo).orElse(0L);
    user.setId(++userId);
    userStorage.getStorage().put(userId, user);
    return user;
  }

  @Override
  public User updateUser(User user) {
    userStorage.getStorage().put(user.getId(), user);
    return userStorage.getStorage().get(user.getId());
  }

  @Override
  public boolean deleteUser(long userId) {
    return null != userStorage.getStorage().remove(userId);
  }
}
