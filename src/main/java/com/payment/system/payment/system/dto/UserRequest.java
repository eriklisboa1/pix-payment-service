package com.payment.system.payment.system.dto;

import com.payment.system.payment.system.model.User;

public record UserRequest(String name, String email, String password) {
    public User toModel(){
        return new User(name, email, password);
    }

}
