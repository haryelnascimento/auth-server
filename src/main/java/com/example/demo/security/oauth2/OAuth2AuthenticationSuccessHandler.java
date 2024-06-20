package com.example.demo.security.oauth2;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.config.AppProperties;
import com.example.demo.exception.BadRequestException;
import com.example.demo.factory.UserFactoryManager;
import com.example.demo.security.AuthenticatedUser;
import com.example.demo.security.TokenProvider;
import com.example.demo.utils.CookieUtils;
import com.example.demo.vo.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

  public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
  public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";
  private static final int cookieExpireSeconds = 180;

  private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;
  // private AppProperties appProperties;
  private ApplicationContext applicationContext;

  @Autowired
  private TokenProvider tokenProvider;

  @Autowired
  OAuth2AuthenticationSuccessHandler(HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository, ApplicationContext applicationContext) {
      // this.appProperties = appProperties;
      this.httpCookieOAuth2AuthorizationRequestRepository = httpCookieOAuth2AuthorizationRequestRepository;
      this.applicationContext = applicationContext;
  }

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {

    UserFactoryManager userFactoryManager = new UserFactoryManager(applicationContext);
    UserDTO userDTO = userFactoryManager.createUser(authentication);

    String token = geraEArmazenaToken(authentication, userDTO);
    String targetUrl = determineTargetUrl(request, response, token);

    if (response.isCommitted()) {
      logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
      return;
    }

    clearAuthenticationAttributes(request, response);
    getRedirectStrategy().sendRedirect(request, response, targetUrl);

  }

  private String geraEArmazenaToken(Authentication authentication, UserDTO userDTO) {
    Collection<GrantedAuthority> authorities = new ArrayList<>();

    authorities.addAll(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")));
    if (userDTO.getFlSuperUsuario().equals("1")) {
      authorities.addAll(Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
    }

    AuthenticatedUser authUser = toAuthenticatedUser(userDTO, (OAuth2AuthenticationToken) authentication, authorities);

    return tokenProvider.createToken(authUser, authorities);
  }

  public AuthenticatedUser toAuthenticatedUser(UserDTO user, OAuth2AuthenticationToken authentication,
      Collection<GrantedAuthority> authorities) {
    AuthenticatedUser authUser = new AuthenticatedUser(user.getCdUsuario(),
        "",
        user.getDeEmail(),
        user.getNmUsuario(),
        user.getNuCpfcnpj(),
        authentication.getAuthorizedClientRegistrationId(),
        user.getNuTelefone(),
        UUID.randomUUID().toString(),
        authorities);

    return authUser;
  }

  protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, String token) {
    Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URI_PARAM_COOKIE_NAME)
        .map(Cookie::getValue);

    if (redirectUri.isPresent() && !isAuthorizedRedirectUri(redirectUri.get())) {
      throw new BadRequestException(
          "Sorry! We've got an Unauthorized Redirect URI and can't proceed with the authentication");
    }

    String targetUrl = redirectUri.orElse(getDefaultTargetUrl());

    return UriComponentsBuilder.fromUriString(targetUrl)
        .queryParam("token", token)
        .build().toUriString();
  }

  protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
    super.clearAuthenticationAttributes(request);
    httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequestCookies(request, response);
  }

  private boolean isAuthorizedRedirectUri(String uri) {
    URI clientRedirectUri = URI.create(uri);

    return Arrays.asList("http://localhost:3000", "http://localhost:4200")
        .stream()
        .anyMatch(authorizedRedirectUri -> {
          // Only validate host and port. Let the clients use different paths if they want
          // to
          URI authorizedURI = URI.create(authorizedRedirectUri);
          if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost())
              && authorizedURI.getPort() == clientRedirectUri.getPort()) {
            return true;
          }
          return false;
        });
  }

}