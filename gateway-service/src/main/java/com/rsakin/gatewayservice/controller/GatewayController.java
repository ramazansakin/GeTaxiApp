package com.rsakin.gatewayservice.controller;

import com.rsakin.gatewayservice.dao.model.User;
import com.rsakin.gatewayservice.dao.model.UserDTO;
import com.rsakin.gatewayservice.model.JwtRequest;
import com.rsakin.gatewayservice.model.JwtResponse;
import com.rsakin.gatewayservice.service.JwtUserDetailsService;
import com.rsakin.gatewayservice.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Validated
@RestController
@Slf4j
@CrossOrigin("*")
public class GatewayController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody @Valid JwtRequest authenticationRequest) {

        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
            final UserDetails userDetails = userDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());
            final String token = jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } catch (Exception e) {
            log.error("createAuthenticationToken : " + e.getMessage());
            throw new AuthenticationServiceException("Not authenticated  user [" + authenticationRequest.getUsername() + "]");
        }
    }

    @PostMapping(value = "/register")
    public ResponseEntity<UserDTO> saveUser(@RequestBody @Valid User user) {
        return ResponseEntity.ok(userDetailsService.save(user));
    }

    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new DisabledException("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
