package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timePointCalculationService;

    private final AmountCalculationService amountsCalculationService;

    private final ResidualCalculateService residualCalculationService;

    private final ReferenceCalculationService referenceCalculationService;

    private final OverpaymentCalculationService overpaymentCalculationService;

    public RateCalculationServiceImpl(
            final TimePointService timePointCalculationService,
            final OverpaymentCalculationService overpaymentCalculationService,
            final AmountCalculationService amountsCalculationService,
            final ResidualCalculateService residualCalculationService,
            final ReferenceCalculationService referenceCalculationService
    ) {
        this.timePointCalculationService = timePointCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.amountsCalculationService = amountsCalculationService;
        this.residualCalculationService = residualCalculationService;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> calculate(final InputData inputData) {
        List<Rate> rateList = new ArrayList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate zeroRate = calculateZeroRate(rateNumber, inputData);

        Rate previousRate = zeroRate;
        rateList.add(zeroRate);

        for (BigDecimal i = rateNumber.add(BigDecimal.ONE);
             i.compareTo(inputData.getMonthsDuration()) <= 0;
             i = i.add(BigDecimal.ONE)) {
            Rate nextRate = calculateNextRate(i, inputData, previousRate);
            previousRate = nextRate;
            rateList.add(nextRate);

            if (BigDecimal.ZERO.equals(nextRate
                    .getMortgageResidual()
                    .getResidualAmount()
                    .setScale(0, RoundingMode.HALF_UP))) {
                break;
            }
        }

        return rateList;
    }

    private Rate calculateZeroRate(final BigDecimal rateNumber, final InputData inputData) {
        TimePoint timePoint = timePointCalculationService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(rateAmounts, inputData);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate calculateNextRate(final BigDecimal rateNumber, final InputData inputData, final Rate previousRate) {
        TimePoint timepoint = timePointCalculationService.calculate(rateNumber, previousRate);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment, previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(
                rateAmounts,
                inputData,
                previousRate
        );

        return new Rate(rateNumber, timepoint, rateAmounts, mortgageResidual, mortgageReference);
    }
}
