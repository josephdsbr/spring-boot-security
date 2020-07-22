package com.security.springsecurity.repository.Dao;

import com.security.springsecurity.models.auth.ApplicationUser;

import java.util.Optional;

public interface ApplicationUserDao {
  Optional<ApplicationUser> selectApplicationUserByUserName(String username);
}
