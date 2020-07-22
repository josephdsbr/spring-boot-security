package com.security.springsecurity.mock;

import com.security.springsecurity.models.auth.ApplicationUser;
import com.security.springsecurity.models.enums.ApplicationUserRole;
import com.security.springsecurity.repository.Dao.ApplicationUserDao;
import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.security.springsecurity.models.enums.ApplicationUserRole.*;

@Repository("fake")
public class FakerApplicationUserDaoService implements ApplicationUserDao {
  private final PasswordEncoder passwordEncoder;

  @Autowired
  public FakerApplicationUserDaoService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public Optional<ApplicationUser> selectApplicationUserByUserName(String username) {
    return getApplicationUsers().stream().filter(applicationUser -> username.equals(applicationUser.getUsername())).findFirst();
  }

  private List<ApplicationUser> getApplicationUsers() {
    return Lists.newArrayList(
      ApplicationUser
        .builder()
        .username("jose_vinicius")
        .password(passwordEncoder.encode("password"))
        .grantedAuthorities(STUDENT.getGrantedAuthorities())
        .isAccountNonExpired(true)
        .isAccountNonLocked(true)
        .isCredentialsNonExpired(true)
        .isEnabled(true)
        .build(),
      ApplicationUser
        .builder()
        .username("douglas_santos")
        .password(passwordEncoder.encode("password"))
        .grantedAuthorities(ADMIN.getGrantedAuthorities())
        .isAccountNonExpired(true)
        .isAccountNonLocked(true)
        .isCredentialsNonExpired(true)
        .isEnabled(true)
        .build(),
      ApplicationUser
        .builder()
        .username("dayane_santos")
        .password(passwordEncoder.encode("password"))
        .grantedAuthorities(ADMINTRAINNE.getGrantedAuthorities())
        .isAccountNonExpired(true)
        .isAccountNonLocked(true)
        .isCredentialsNonExpired(true)
        .isEnabled(true)
        .build()
    );
  }
}
