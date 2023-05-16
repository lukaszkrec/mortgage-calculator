package org.mortgage.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.mortgage.model.InputData;
import org.mortgage.model.Overpayment;

class OverpaymentCalculationServiceImplTest {
    /**
     * Method under test: {@link OverpaymentCalculationServiceImpl#calculate(BigDecimal, InputData)}
     */
    @Test
    void testCalculate() {
        OverpaymentCalculationServiceImpl overpaymentCalculationServiceImpl = new OverpaymentCalculationServiceImpl();
        BigDecimal rateNumber = BigDecimal.valueOf(42L);
        Overpayment actualCalculateResult = overpaymentCalculationServiceImpl.calculate(rateNumber, new InputData());
        BigDecimal expectedAmount = rateNumber.ZERO;
        BigDecimal amount = actualCalculateResult.getAmount();
        assertSame(expectedAmount, amount);
        assertSame(amount, actualCalculateResult.getProvisionAmount());
        assertEquals("0", amount.toString());
    }
}

