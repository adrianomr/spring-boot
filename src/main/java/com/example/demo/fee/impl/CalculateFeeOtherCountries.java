package com.example.demo.fee.impl;


import com.example.demo.fee.CalculateFee;
import com.example.demo.fee.enums.PurchaseType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.example.demo.fee.constants.FeeConstants.DEFAULT_FEE;
import static com.example.demo.fee.constants.FeeConstants.IOF;

@Service
public class CalculateFeeOtherCountries implements CalculateFee {

    @Override
    public PurchaseType purchaseType() {
        return PurchaseType.OTHER_COUNTRIES;
    }

    @Override
    public BigDecimal calculate() {
        return DEFAULT_FEE.add(IOF);
    }
}
