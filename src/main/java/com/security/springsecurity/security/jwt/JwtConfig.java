package com.security.springsecurity.security.jwt;

import com.google.common.net.HttpHeaders;
import io.jsonwebtoken.security.Keys;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;

@Component
@ConfigurationProperties(prefix = "application.jwt")
@Data
@NoArgsConstructor
public class JwtConfig {
  private String secretKey;
  private String tokenPrefix;
  private Integer tokenExpirationAfterDays;

  public String getAuthorizationHeader() {
    return HttpHeaders.AUTHORIZATION;
  }
}
