package com.husnasari.emporium_backend.model;

import lombok.Data;

//dto
@Data
public class BusinessDetails {
    private String businessName;
    private String businessEmail;
    private String businessMobile;
    private String businessAdress;
    private String logo;
    private String banner;
}
