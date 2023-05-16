package org.mortgage.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mortgage.model.*;


import java.math.BigDecimal;

class ConstantAmountsCalculationServiceImplTest {

    private ConstantAmountCalculationService constantAmountsCalculationService;

    @BeforeEach
    public void setup() {
        this.constantAmountsCalculationService = new ConstantAmountCalculationServiceImpl();
    }

    @Test
    @DisplayName("Calculate rate amounts for first rate")
    void shouldCalculateFirstRateAmountsCorrectly() {
        // given
        InputData inputData = TestData.someInputData();
        RateAmounts expected = TestData.someRateAmounts();

        // when
        RateAmounts result = constantAmountsCalculationService.calculate(inputData, null);

        // then
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("Calculate rate amounts for other rates")
    void shouldCalculateOtherRateAmountsCorrectly() {
        // given
        InputData inputData = TestData.someInputData();
        Rate rate = TestData.someRate();
        RateAmounts expected = TestData.someRateAmounts()
            .withRateAmount(new BigDecimal("3303.45"))
            .withInterestAmount(new BigDecimal("2483.87"))
            .withCapitalAmount(new BigDecimal("819.58"));

        // when
        RateAmounts result = constantAmountsCalculationService.calculate(inputData, null, rate);

        // then
        Assertions.assertEquals(expected, result);
    }
}