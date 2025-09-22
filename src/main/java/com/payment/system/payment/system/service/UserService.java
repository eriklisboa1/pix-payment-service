package com.payment.system.payment.system.service;

import com.payment.system.payment.system.dto.UserResponse;
import com.payment.system.payment.system.model.User;
import com.payment.system.payment.system.repository.UserRepository;
import com.payment.system.payment.system.util.RandomString;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.authentication.PasswordEncoderParser;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;



@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MailService mailService;

    private static ResponseStatusException conflict(String msg) {
        return new ResponseStatusException(HttpStatus.CONFLICT, msg);
    }

    @SneakyThrows
    public UserResponse registerUser(User user) {
        if (userRepository.findByEmail(user.getEmail()) != null) {
            System.out.println("Tentativa de cadastro com e-mail existente: " + user.getEmail());
            throw new ResponseStatusException(HttpStatus.CONFLICT, "E-mail já cadastrado.");
        }else {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);



            String randomCode = RandomString.generateRandomString(64);
            user.setVerificationCode(randomCode);
            user.setEnabled(false);


            User savedUser = userRepository.save(user);
            UserResponse userResponse = new UserResponse(savedUser.getId(),savedUser.getName(), savedUser.getEmail(), savedUser.getPassword());

            mailService.sendVerificationMail(user);
            return userResponse;
        }

    }

    public boolean verify(String verificationCode) {
        User user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        }else {
            user.setVerificationCode("Verificado");
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }

    }
}
