package com.examly.springapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.entities.Coupon;

public interface CouponRepository extends JpaRepository<Coupon,Long> {
    
}
