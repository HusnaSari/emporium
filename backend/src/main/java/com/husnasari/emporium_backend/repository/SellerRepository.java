package com.husnasari.emporium_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.husnasari.emporium_backend.model.Seller;
import com.husnasari.emporium_backend.domain.AccountStatus;

public interface SellerRepository extends JpaRepository<Seller, Long> {
    Seller findByEmail(String email);

    List<Seller> findByAccountStatus(AccountStatus status);
}
