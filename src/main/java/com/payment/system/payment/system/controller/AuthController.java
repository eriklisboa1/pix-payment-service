package com.payment.system.payment.system.controller;

import com.payment.system.payment.system.dto.AuthenticationResponse;
import com.payment.system.payment.system.dto.AutheticationRequest;
import com.payment.system.payment.system.model.User;
import com.payment.system.payment.system.repository.UserRepository;
import com.payment.system.payment.system.service.TokenService;
import com.payment.system.payment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Autowired
    private UserRepository userRepository;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AutheticationRequest authRequest) {

        User user = userRepository.findByEmailCheck(authRequest.email());

        if (!"Verificado".equalsIgnoreCase(user.getVerificationCode())) {
            return ResponseEntity.status(403).body("Conta não verificada, por favor verifique seu email");
        }

        var UsernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                authRequest.email(), authRequest.password()
        );

        var auth = authenticationManager.authenticate(UsernamePasswordAuthenticationToken);

        var token = tokenService.generateToken((User) auth.getPrincipal());
        return ResponseEntity.ok(new AuthenticationResponse(token));
    }




}
