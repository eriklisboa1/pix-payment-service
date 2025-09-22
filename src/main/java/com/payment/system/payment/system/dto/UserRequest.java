package com.payment.system.payment.system.dto;

import com.payment.system.payment.system.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.antlr.v4.runtime.misc.NotNull;
import org.aspectj.bridge.IMessage;

public record UserRequest(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        @Size(min = 8, message = "A senha deve conter no minimo 8 caractere")
        String password) {




    public User toModel(){
        return new User(name, email, password);
    }

}
