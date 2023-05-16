package org.mortgage.service;

import lombok.AllArgsConstructor;
import org.mortgage.model.InputData;
import org.mortgage.model.Overpayment;
import org.mortgage.model.Rate;
import org.mortgage.model.RateAmounts;

@AllArgsConstructor
public class AmountCalculationServiceImpl implements AmountCalculationService {

    private final ConstantAmountCalculationService constantAmountsCalculationService;

    private final DecreasingAmountCalculationService decreasingAmountsCalculationService;

    @Override
    public RateAmounts calculate(final InputData inputData, final Overpayment overpayment) {
        return switch (inputData.getRateType()) {
            case CONSTANT -> constantAmountsCalculationService.calculate(inputData, overpayment);
            case DECREASING -> decreasingAmountsCalculationService.calculate(inputData, overpayment);
            default -> throw new MortgageException("Case not handled");
        };
    }

    @Override
    public RateAmounts calculate(final InputData inputData, final Overpayment overpayment, final Rate previousRate) {
        return switch (inputData.getRateType()) {
            case CONSTANT -> constantAmountsCalculationService.calculate(inputData, overpayment, previousRate);
            case DECREASING -> decreasingAmountsCalculationService.calculate(inputData, overpayment, previousRate);
            default -> throw new MortgageException("Case not handled");
        };
    }


}
