package com.example.shoppingapp.product.service;

import com.example.shoppingapp.product.domain.MoneyTypes;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductDeliveryService {

    public String getDeliveryInfo(String ProductId){
        return "Tomorrow";
    }
    public boolean freeDeliveryCheck(String ProductId, BigDecimal price, MoneyTypes moneyTypes){
        return true;
    }
}
