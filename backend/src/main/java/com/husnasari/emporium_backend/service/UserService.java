package com.husnasari.emporium_backend.service;

import com.husnasari.emporium_backend.model.User;

public interface UserService {

    User findUserByJwtToken(String jwt) throws Exception;

    User findUserByEmail(String email) throws Exception;
}
