package com.epam.springcore.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.epam.springcore.model.User;
import com.epam.springcore.repository.storage.Storage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserRepositoryImplTest {

  UserRepository userRepository = new UserRepositoryImpl();

  @BeforeEach
  void init(@Mock Storage<User> userStorage) {
    Map<Long, User> map = new HashMap<>();
    User user = User.builder().id(1).email("test@mail.com").name("Eugene").build();
    map.put(user.getId(), user);
    Mockito.when(userStorage.getStorage()).thenReturn(map);
    this.userRepository.setUserStorage(userStorage);
  }

  @Test
  void findUserById_UserFound() {
    assertNotNull(userRepository.findUserById(1));
  }

  @Test
  void findUserByEmail_UserFound() {
    assertNotNull(userRepository.findUserByEmail("test@mail.com"));
  }

  @Test
  void findUsersByName_UsersFoundWithPartialName() {
    assertThat(userRepository.findUsersByName("Eug", 1, 1)).isNotEmpty();
  }

  @Test
  void createUser_UserCreated() {
    User testUser = User.builder().name("TestUser").email("newTest@mail.com").build();
    assertThat(userRepository.createUser(testUser).getId()).isPositive();
  }

  @Test
  void updateUser_UserIsUpdated() {
    User user = userRepository.findUserById(1);
    String expectedName = "New User Name";
    user.setName(expectedName);
    assertEquals(expectedName, userRepository.updateUser(user).getName());
  }

  @Test
  void deleteUser_UserIsDeleted() {
    assertTrue(userRepository.deleteUser(1));
  }
}
