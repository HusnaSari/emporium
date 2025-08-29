package com.husnasari.emporium_backend.service;

import com.husnasari.emporium_backend.domain.USER_ROLE;
import com.husnasari.emporium_backend.request.LoginRequest;
import com.husnasari.emporium_backend.response.AuthResponse;
import com.husnasari.emporium_backend.response.SignUpRequest;

public interface AuthService {

    void sentLoginOtp(String email, USER_ROLE role) throws Exception;

    String createUser(SignUpRequest req) throws Exception;

    AuthResponse signing(LoginRequest req) throws Exception;
}
