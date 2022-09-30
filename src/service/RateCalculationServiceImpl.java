package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timePointService;
    private final AmountCalculationService amountCalculationService;
    private final OverpaymentCalculationService overpaymentCalculationService;
    private final ResidualCalculateService residualCalculateService;

    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImpl(
            TimePointService timePointService,
            AmountCalculationService amountCalculationService,
            OverpaymentCalculationService overpaymentCalculationService,
            ResidualCalculateService residualCalculateService,
            ReferenceCalculationService referenceCalculationService
    ) {
        this.timePointService = timePointService;
        this.amountCalculationService = amountCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.residualCalculateService = residualCalculateService;
        this.referenceCalculationService = referenceCalculationService;
    }

    private static boolean mortgageFinished(Rate nextRate) {
        BigDecimal toCompare = nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP);
        return BigDecimal.ZERO.equals(toCompare);
    }

    @Override
    public List<Rate> calculate(InputData inputData) {
        List<Rate> rates = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate firstRate = calculateFirstRate(rateNumber, inputData);

        rates.add(firstRate);

        Rate previousRate = firstRate;
        for (BigDecimal index = rateNumber.add(BigDecimal.ONE);
             index.compareTo(inputData.getMonthsDuration()) <= 0;
             index = index.add(BigDecimal.ONE)
        ) {
            Rate nextRate = calculateNextRate(index, inputData, previousRate);
            rates.add(nextRate);
            previousRate = firstRate;

            if (mortgageFinished(nextRate)) {
                break;
            }

        }
        return rates;
    }

    private Rate calculateFirstRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculateService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate calculateNextRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountCalculationService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculateService.calculate(rateAmounts, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(
                inputData, rateAmounts, previousRate);


        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }
}
