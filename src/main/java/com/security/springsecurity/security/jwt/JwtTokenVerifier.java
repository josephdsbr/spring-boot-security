package com.security.springsecurity.security.jwt;

import com.google.common.base.Strings;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Data
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class JwtTokenVerifier extends OncePerRequestFilter {
  private final SecretKey secretKey;
  private final JwtConfig jwtConfig;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                  FilterChain filterChain) throws ServletException, IOException {
    String authorizationHeader = request.getHeader(jwtConfig.getAuthorizationHeader());

    if (Strings.isNullOrEmpty(authorizationHeader) || !authorizationHeader.startsWith(String.format("%s ", jwtConfig.getTokenPrefix()))) {
      filterChain.doFilter(request, response);
      return;
    }

    String token = authorizationHeader.replace(String.format("%s ", jwtConfig.getTokenPrefix()), "");

    try {
      Jws<Claims> claimsJws = Jwts
        .parserBuilder()
        .setSigningKey(secretKey)
        .build()
        .parseClaimsJws(token);

      Claims body = claimsJws.getBody();

      String username = body.getSubject();

      var authorities = (List<Map<String, String>>) body.get("authorities");

      Set<GrantedAuthority> grantedAuthorities =  authorities.stream()
        .map(authority -> new SimpleGrantedAuthority(authority.get("authority")))
        .collect(Collectors.toSet());

      Authentication authentication = new UsernamePasswordAuthenticationToken(
        username,
        null,
        grantedAuthorities
      );

      SecurityContextHolder.getContext().setAuthentication(authentication);
    } catch (JwtException e) {
      throw new IllegalArgumentException(String.format("Token cannot be trusted: %s", token));
    }

    filterChain.doFilter(request, response);
  }
}
