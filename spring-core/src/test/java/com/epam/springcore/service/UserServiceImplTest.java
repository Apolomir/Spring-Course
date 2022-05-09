package com.epam.springcore.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.epam.springcore.model.User;
import com.epam.springcore.repository.UserRepository;
import java.util.List;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

  static final String TEST_USER_NAME = "Test User";
  static final String TEST_USER_EMAIL = "testUser@gmail.com";
  static final long TEST_USER_ID = 1;

  UserService userService;
  @Mock UserRepository userRepository;
  User testUser;

  @BeforeEach
  void init() {
    userService = new UserServiceImpl(userRepository);
    testUser = User.builder().id(TEST_USER_ID).name(TEST_USER_NAME).email(TEST_USER_EMAIL).build();
  }

  @Test
  void findUserById_UserIsFound() {
    long testUserId = 1;
    when(userRepository.findUserById(anyLong())).thenReturn(testUser);
    assertNotNull(userService.findUserById(testUserId));
  }

  @Test
  void findUserByEmail_UserIsFound() {
    when(userRepository.findUserByEmail(anyString())).thenReturn(testUser);
    assertNotNull(userService.findUserByEmail(TEST_USER_EMAIL));
  }

  @Test
  void findUsersByName_UsersAreFound() {
    List<User> testUserList = Lists.newArrayList(testUser);
    when(userRepository.findUsersByName(anyString(), anyInt(), anyInt())).thenReturn(testUserList);
    assertThat(userService.findUsersByName(testUser.getName(), 1, 1)).isNotEmpty();
  }

  @Test
  void createUser_UserIsCreated() {
    User newTestUser = User.builder().name("New Test User").email("New Test Email").build();
    when(userRepository.createUser(any(User.class))).thenReturn(testUser);
    assertEquals(testUser, userService.createUser(newTestUser));
  }

  @Test
  void updateUser_UserIsUpdated() {
    testUser.setName("Updated Name");
    when(userRepository.updateUser(any(User.class))).thenReturn(testUser);
    assertEquals(testUser, userService.updateUser(testUser));
  }

  @Test
  void deleteUser_UserIsDeleted() {
    when(userRepository.deleteUser(anyLong())).thenReturn(true);
    assertTrue(userService.deleteUser(TEST_USER_ID));
  }
}
