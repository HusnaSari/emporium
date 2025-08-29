package com.husnasari.emporium_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.husnasari.emporium_backend.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {
}