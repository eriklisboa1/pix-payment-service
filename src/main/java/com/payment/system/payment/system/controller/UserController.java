package com.payment.system.payment.system.controller;

import com.payment.system.payment.system.dto.UserRequest;
import com.payment.system.payment.system.model.User;
import com.payment.system.payment.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<User> registroruser(@RequestBody UserRequest userRequest) {
        User user = userRequest.toModel();
        User savedUser = userService.registerUser(user);

        return ResponseEntity.ok().body(savedUser);
    }
}
