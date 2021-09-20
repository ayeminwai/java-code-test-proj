package com.ayeminwai.evoucher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayeminwai.evoucher.model.EVoucherDetails;

@Repository
public interface EVoucherDetailsRepository extends JpaRepository<EVoucherDetails, Long> {

}
