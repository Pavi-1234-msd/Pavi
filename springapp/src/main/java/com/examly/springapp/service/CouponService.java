package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.entities.Coupon;
import com.examly.springapp.repositories.CouponRepository;

@Service
public class CouponService 
{

    @Autowired
    private CouponRepository couponRepository;
    public CouponService(CouponRepository couponRepository)
    {
        this.couponRepository = couponRepository;
    }

    public List<Coupon> getAllCoupons() 
    {
        return couponRepository.findAll();
    }

    public Coupon getCouponById(Long id) 
    {
        return couponRepository.findById(id).orElse(null);
    }

    public Coupon saveCoupon(Coupon coupon) 
    {
        return couponRepository.save(coupon);
    }

    public Coupon updateCoupon(Coupon coupon)
    {
        Optional<Coupon> existingCoupon = couponRepository.findById(coupon.getId());
        if(existingCoupon.isPresent())
        {
            return couponRepository.save(coupon);
        }
        else
        {
            return null;
        }
    }
    public void deleteCoupon(Long id) 
    {
        couponRepository.deleteById(id);
    }
}