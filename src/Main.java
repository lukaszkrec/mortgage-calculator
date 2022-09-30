import model.InputData;
import model.Overpayment;
import model.MortgageType;
import service.*;

import java.math.BigDecimal;
import java.util.Collections;

public class Main {

    public static void main(String[] args) {
        InputData inputData = new InputData()
                .withAmount(new BigDecimal("300000"))
                .withOverpaymentSchema(Collections.emptyMap())
                .withMonthsDuration(BigDecimal.valueOf(180))
                .withType(MortgageType.CONSTANT)
                .withOverpaymentReduceWay(Overpayment.REDUCE_RATE);

        PrintingService printingService = new PrintingServiceIImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountCalculationServiceImpl(
                        new ConstantAmountCalculationServiceImpl(),
                        new DecreasingAmountCalculationServiceImpl()
                ),
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculateServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );

        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );
        mortgageCalculationService.calculate(inputData);


    }
}
