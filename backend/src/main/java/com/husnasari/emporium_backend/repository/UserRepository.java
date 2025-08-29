package com.husnasari.emporium_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.husnasari.emporium_backend.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
