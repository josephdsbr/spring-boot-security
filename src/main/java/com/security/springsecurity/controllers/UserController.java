package com.security.springsecurity.controllers;

import com.security.springsecurity.models.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
  private static final List<User> USERS = Arrays.asList(
      new User(1L, "José"),
      new User(2L, "Douglas"),
      new User(3L, "Dayanne")
  );

  @GetMapping(path = "{userId}")
  public User getUser(
      @PathVariable("userId") Long userId
  ) {
    return USERS.stream().filter(user -> user.getId().equals(userId))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("User: " + userId + " doesn't exist."));
  }
}
