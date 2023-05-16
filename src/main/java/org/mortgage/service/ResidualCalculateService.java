package org.mortgage.service;

import org.mortgage.model.*;

public interface ResidualCalculateService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);

    MortgageResidual calculate(RateAmounts rateAmounts, final InputData inputData, Rate previousRate);
}
