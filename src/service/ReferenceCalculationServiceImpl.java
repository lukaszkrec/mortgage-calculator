package service;

import model.*;

import java.math.BigDecimal;

public class ReferenceCalculationServiceImpl implements ReferenceCalculationService {

    @Override
    public MortgageReference calculate(RateAmounts rateAmounts, InputData inputData) {
        if (BigDecimal.ZERO.equals(inputData.getAmount())) {
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }
        return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
    }


    @Override
    public MortgageReference calculate(RateAmounts rateAmounts, InputData inputData, Rate previousRate) {
        if (BigDecimal.ZERO.equals(previousRate.getMortgageResidual().getResidualAmount())) {
            return new MortgageReference(BigDecimal.ZERO, BigDecimal.ZERO);
        }

        switch (inputData.getOverpaymentReduceWay()) {
            case Overpayment.REDUCE_PERIOD:
                return reduceRateMortgageReference(rateAmounts, previousRate);
            case Overpayment.REDUCE_RATE:
                return new MortgageReference(inputData.getAmount(), inputData.getMonthsDuration());
            default:
                throw new MortgageException();

        }

    }

    private MortgageReference reduceRateMortgageReference(final RateAmounts rateAmounts, final Rate previousResidual) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal residualAmount = calculateResidualAmount(
                    previousResidual.getMortgageResidual().getResidualAmount(), rateAmounts
            );
            return new MortgageReference(residualAmount, previousResidual
                    .getMortgageResidual()
                    .getResiudualDuration()
                    .subtract(BigDecimal.ONE));
        }
        return new MortgageReference(
                previousResidual.getMortgageResidual().getResidualAmount(),
                previousResidual.getMortgageResidual().getResiudualDuration()
        );


    }

    private BigDecimal calculateResidualAmount(final BigDecimal amount, final RateAmounts rateAmounts) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
