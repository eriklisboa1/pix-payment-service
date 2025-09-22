package com.payment.system.payment.system.service;


import com.auth0.jwt.algorithms.Algorithm;
import com.payment.system.payment.system.model.User;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Va
    private String secret;

    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(user.getPassword());
        }

    }
}
