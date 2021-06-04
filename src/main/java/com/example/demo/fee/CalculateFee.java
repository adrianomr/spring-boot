package com.example.demo.fee;


import com.example.demo.fee.enums.PurchaseType;

import java.math.BigDecimal;

public interface CalculateFee {

    PurchaseType purchaseType();

    BigDecimal calculate();

}
