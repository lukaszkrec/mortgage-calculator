package mortgage;

import model.InputData;
import model.MortgageType;
import model.Overpayment;
import service.*;

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
                .withType(MortgageType.CONSTANT)
                .withOverpaymentSchema(overpaymentSchema);

        PrintingService printingService = new PrintingServiceIImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new OverpaymentCalculationServiceImpl(),
                new AmountCalculationServiceImpl(
                        new ConstantAmountCalculationServiceImpl(),
                        new DecreasingAmountCalculationServiceImpl()

                ),
                new ResidualCalculateServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                rateCalculationService,
                printingService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(defaultInputData);
    }
}



