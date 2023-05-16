package org.mortgage.service;

import org.mortgage.model.*;

public interface ConstantAmountCalculationService {

    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}


