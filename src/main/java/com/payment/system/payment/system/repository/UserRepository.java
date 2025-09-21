package com.payment.system.payment.system.repository;

import com.payment.system.payment.system.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<User, Long> {


    UserDetails findByEmail(String email);
}
