package com.example.demo.fee;

import com.example.demo.fee.enums.PurchaseType;
import com.example.demo.fee.impl.CalculateFeeBrazil;
import com.example.demo.fee.impl.CalculateFeeOtherCountries;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {CalculateFeeFactory.class, CalculateFeeBrazil.class, CalculateFeeOtherCountries.class})
class CalculateFeeFactoryTest {

    @Autowired
    CalculateFeeFactory calculateFeeFactory;

    @Test
    void createWhenPurchaseTypeIsBrazilShouldCreateBrazilFeeCalculator() {
        CalculateFee calculateFee = calculateFeeFactory.create(PurchaseType.BRAZIL);
        assertEquals(PurchaseType.BRAZIL, calculateFee.purchaseType());
        assertEquals(BigDecimal.TEN, calculateFee.calculate());
    }

    @Test
    void createWhenPurchaseTypeIsOtherCountriesShouldCreateOtherCountriesFeeCalculator() {
        CalculateFee calculateFee = calculateFeeFactory.create(PurchaseType.OTHER_COUNTRIES);
        assertEquals(PurchaseType.OTHER_COUNTRIES, calculateFee.purchaseType());
        assertEquals(BigDecimal.valueOf(35), calculateFee.calculate());
    }
}