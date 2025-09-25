package com.payment.system.payment.system.controller;

import com.payment.system.payment.system.dto.AuthenticationResponse;
import com.payment.system.payment.system.dto.AutheticationRequest;
import com.payment.system.payment.system.dto.UserRequest;
import com.payment.system.payment.system.dto.UserResponse;
import com.payment.system.payment.system.model.User;
import com.payment.system.payment.system.service.TokenService;
import com.payment.system.payment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;




    @PostMapping("/register")
    public ResponseEntity<?> registroruser(@RequestBody UserRequest userRequest) {
        try {
            User user = userRequest.toModel();
            UserResponse savedUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedUser);
        } catch (ResponseStatusException ex) {
            return ResponseEntity
                    .status(ex.getStatusCode())
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(ex.getReason());
        }
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)){
            return "Usuario verificado com sucesso!";
        }else{
            return "Usuario Já verificado";
        }
    }


    @GetMapping("/teste")
    public String teste() {
        return "Logado";
    }



}
