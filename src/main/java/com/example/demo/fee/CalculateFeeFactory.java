package com.example.demo.fee;

import com.example.demo.fee.enums.PurchaseType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateFeeFactory {

    @Autowired
    List<CalculateFee> calculateFeeList;

    public CalculateFee create(PurchaseType purchaseType) {
        return calculateFeeList
                .stream()
                .filter(calculateFee -> calculateFee.purchaseType().equals(purchaseType))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("PurchaseType not found"));
    }

}
