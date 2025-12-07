package com.usermanagementapi.controllers;

import com.usermanagementapi.dto.UserDto;
import com.usermanagementapi.service.UserService;
import com.usermanagementapi.utils.JwtTokenUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping(value = "v1/userLogin")
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody UserDto user) {

        String response = userService.userLogin(user);

        Map<String, String> responseBody = new HashMap<>();

        if (response.equals("Login successful !")) {

            String token = jwtTokenUtil.generateToken(user.getEmail());

            responseBody.put("message", response);
            responseBody.put("token", token);
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } else {
            responseBody.put("message", response);
            return new ResponseEntity<>(responseBody, HttpStatus.UNAUTHORIZED);
        }

}

    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint() {
        return ResponseEntity.ok("This is a protected endpoint!");
    }

}
