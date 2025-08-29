package com.husnasari.emporium_backend.model;

import com.husnasari.emporium_backend.domain.PaymentStatus;

import lombok.Data;

@Data
public class PaymentDetails {

    private String paymentId;
    private String razorpayPaymentLinkId;
    private String razorpayPaymentLinkReferencedId;
    private String razorpayPaymentLinkStatus;
    private String razorpayPaymentIdZWSPS;
    private PaymentStatus status;
}
