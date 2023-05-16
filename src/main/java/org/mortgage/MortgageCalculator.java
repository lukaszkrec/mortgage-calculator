package org.mortgage;

import org.mortgage.model.*;
import org.mortgage.service.*;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TreeMap;

public class MortgageCalculator {

    public static void main(String[] args) {
        Map<Integer, BigDecimal> overpaymentSchema = new TreeMap<>();
        overpaymentSchema.put(5, BigDecimal.valueOf(12000));
        overpaymentSchema.put(19, BigDecimal.valueOf(10000));
        overpaymentSchema.put(28, BigDecimal.valueOf(11000));
        overpaymentSchema.put(64, BigDecimal.valueOf(16000));
        overpaymentSchema.put(78, BigDecimal.valueOf(18000));

        InputData defaultInputData = new InputData()
                .withAmount(new BigDecimal("296192.11"))
                .withMonthsDuration(BigDecimal.valueOf(360))
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .withType(MortgageType.DECREASING)
                .withOverpaymentSchema(overpaymentSchema);

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountCalculationServiceImpl(
                        new ConstantAmountCalculationServiceImpl(),
                        new DecreasingAmountCalculationServiceImpl()
                ),
                new ResidualCalculateServiceImpl(),
                new ReferenceCalculationServiceImpl(),
                new OverpaymentCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                rateCalculationService,
                printingService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(defaultInputData);
    }
}



