package com.payment.system.payment.system.repository;

import com.payment.system.payment.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;


public interface UserRepository extends JpaRepository<User, Long> {


    UserDetails findByEmail(String email);

    User findByVerificationCode(String verificationCode);

    @Query("SELECT u FROM users u WHERE u.email = :email")
    User findByEmailCheck(@Param("email") String email);
}
