package com.ayeminwai.pc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ayeminwai.pc.model.EVoucher;
import com.ayeminwai.pc.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	List<Payment> findByEvoucher(EVoucher evoucher);

	Payment findByPromoCode(String promoCode);

}
