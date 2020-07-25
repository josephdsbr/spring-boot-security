package com.security.springsecurity.security.jwt;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UsernameAndPasswordAuthenticationRequest {
  @NonNull
  private String username;
  @NonNull
  private String password;
}
