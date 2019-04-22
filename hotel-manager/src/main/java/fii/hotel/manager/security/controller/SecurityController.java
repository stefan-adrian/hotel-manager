package fii.hotel.manager.security.controller;

import fii.hotel.manager.config.Utils;
import fii.hotel.manager.security.dto.AuthenticationRequestDto;
import fii.hotel.manager.security.dto.AuthenticationResponseDto;
import fii.hotel.manager.security.exception.AuthenticationException;
import fii.hotel.manager.security.model.CustomerUserDetails;
import fii.hotel.manager.security.service.UserDetailsServiceImpl;
import fii.hotel.manager.security.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@CrossOrigin(origins = Utils.REQUEST_SOURCE)
public class SecurityController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private TokenService tokenService;


    @PostMapping(value = "/authentication")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDto authenticationRequestDto){
        authenticate(authenticationRequestDto.getUsername(),authenticationRequestDto.getPassword());

        final CustomerUserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequestDto.getUsername());
        final String token = tokenService.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponseDto(token));
    }

    private void authenticate(String username, String password) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new AuthenticationException("User is disabled!", e);
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("Bad credentials!", e);
        }
    }

}
