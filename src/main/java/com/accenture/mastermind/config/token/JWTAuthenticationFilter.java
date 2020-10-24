package com.accenture.mastermind.config.token;


import com.accenture.mastermind.config.PropertyReader;
import com.accenture.mastermind.user.User;
import com.accenture.mastermind.user.UserDTO;
import com.accenture.mastermind.user.UserDetailsImpl;
import com.accenture.mastermind.user.UserMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;


import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  private PropertyReader propertyReader;
  private Logger logger;
  private UserMapper userMapper;

  public JWTAuthenticationFilter(
      RequestMatcher requiresAuthenticationRequestMatcher,
      AuthenticationManager authenticationManager,
      PropertyReader propertyReader,
      Logger logger,
      UserMapper userMapper
  ) {
    super(requiresAuthenticationRequestMatcher);
    setAuthenticationManager(authenticationManager);
    this.propertyReader = propertyReader;
    this.logger = logger;
    this.userMapper = userMapper;
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
      throws AuthenticationException, IOException, ServletException {
    try {
      User creds = new ObjectMapper().readValue(req.getInputStream(), User.class);

      return getAuthenticationManager()
          .authenticate(new UsernamePasswordAuthenticationToken(creds.getUsername(), creds.getPassword()));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
                                          Authentication auth) throws IOException, ServletException {
    // Adds the UserDetailsImpl logic to the authenticated user
    UserDetailsImpl userDetailsImpl = (UserDetailsImpl) auth.getPrincipal();
    User user = userDetailsImpl.getUser();
    String subject = user.getId();

    // Builds the JWT
    String token = Jwts.builder().setSubject(subject)
        .setExpiration(
            new Date(System.currentTimeMillis() + propertyReader.getIntProperty("jwt.expiration-time")))
        .signWith(SignatureAlgorithm.HS512, propertyReader.getStringProperty("jwt.secret").getBytes())
        .setIssuer(propertyReader.getStringProperty("jwt.issuer"))
        .compact();
    res.addHeader(propertyReader.getStringProperty("jwt.header-string"),
        propertyReader.getStringProperty("jwt.token-prefix") + " " + token);
    // Expose the Headers
    res.addHeader("Access-Control-Expose-Headers", propertyReader.getStringProperty("jwt.header-string") );

    // Put the user's ID and roles into the response body
    UserDTO userDTO = userMapper.toDTO(user);

    String userDTOString = new ObjectMapper().writeValueAsString(userDTO);

    res.getWriter().write(userDTOString);

    res.setContentType("application/json");
  }

  @Override
  protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
    response.setStatus(HttpStatus.UNAUTHORIZED.value());
  }
}