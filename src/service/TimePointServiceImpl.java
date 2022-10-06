package service;

import model.InputData;
import model.Rate;
import model.TimePoint;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;


public class TimePointServiceImpl implements TimePointService {

    private static final BigDecimal YEAR = BigDecimal.valueOf(12);

    @Override
    public TimePoint calculate(BigDecimal rateNumber, InputData inputData) {
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);
        LocalDate date = inputData.getRepaymentStartDate();
        return new TimePoint(date, year, month);
    }

    @Override
    public TimePoint calculate(BigDecimal rateNumber, Rate previousRate) {
        BigDecimal year = calculateYear(rateNumber);
        BigDecimal month = calculateMonth(rateNumber);
        LocalDate date = previousRate.getTimePoint().getDate().plus(1, ChronoUnit.MONTHS);
        return new TimePoint(date, year, month);

    }


    private BigDecimal calculateYear(final BigDecimal rateNumber) {
        return rateNumber.divide(AmountCalculationService.YEAR, RoundingMode.UP).max(BigDecimal.ONE);

    }

    private BigDecimal calculateMonth(final BigDecimal rateNumber) {
        return BigDecimal.ZERO.equals(rateNumber.remainder(AmountCalculationService.YEAR))
                ? YEAR
                : rateNumber.remainder(AmountCalculationService.YEAR);
    }
}
