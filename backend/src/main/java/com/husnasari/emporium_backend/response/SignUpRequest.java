package com.husnasari.emporium_backend.response;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;

    private String fullname;

    private String otp;
}
