package com.example.demo.fee.impl;


import com.example.demo.fee.CalculateFee;
import com.example.demo.fee.enums.PurchaseType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.demo.fee.constants.FeeConstants.DEFAULT_FEE;

@Service
public class CalculateFeeBrazil implements CalculateFee {

    @Override
    public PurchaseType purchaseType() {
        return PurchaseType.BRAZIL;
    }

    @Override
    public BigDecimal calculate() {
        return DEFAULT_FEE;
    }
}
