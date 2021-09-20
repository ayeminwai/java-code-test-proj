package com.ayeminwai.evoucher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayeminwai.evoucher.model.EVoucher;

@Repository
public interface EVoucherRepository extends JpaRepository<EVoucher, Long> {

	List<EVoucher> findAllByEvoucherStatus(String evoucherStatus);
	
}
