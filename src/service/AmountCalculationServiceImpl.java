package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

public class AmountCalculationServiceImpl implements AmountCalculationService {

    private final ConstantAmountCalculationService constantAmountCalculationService;
    private final DecreasingAmountCalculationService decreasingAmountCalculationService;


    public AmountCalculationServiceImpl(
            ConstantAmountCalculationService constantAmountCalculationService,
            DecreasingAmountCalculationService decreasingAmountCalculationService) {
        this.constantAmountCalculationService = constantAmountCalculationService;
        this.decreasingAmountCalculationService = decreasingAmountCalculationService;
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountCalculationService.calculate(inputData, overpayment);
            case DECREASING:
                return decreasingAmountCalculationService.calculate(inputData, overpayment);
            default:
                throw new MortgageException();
        }
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        switch (inputData.getRateType()) {
            case CONSTANT:
                return constantAmountCalculationService.calculate(inputData, overpayment, previousRate);
            case DECREASING:
                return decreasingAmountCalculationService.calculate(inputData, overpayment, previousRate);
            default:
                throw new MortgageException();
        }
    }

}
