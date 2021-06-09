package com.exadel.discount.platform.web;

import com.exadel.discount.platform.config.JWTUtil;
import com.exadel.discount.platform.domain.JWTResponse;
import com.exadel.discount.platform.service.UserService;
import com.exadel.discount.platform.service.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO userLogin) throws Exception{
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLogin.getEmail(), userLogin.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password");
        }
        UserDetails userDetails = userService.loadUserByUsername(userLogin.getEmail());
        return ResponseEntity.ok(new JWTResponse(jwtUtil.generateTOKEN(userDetails)));
    }

}
