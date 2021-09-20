package com.ayeminwai.pc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayeminwai.pc.model.EVoucherDetails;

@Repository
public interface EVoucherDetailsRepository extends JpaRepository<EVoucherDetails, Long> {

}
