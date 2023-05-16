package org.mortgage.service;

import java.math.BigDecimal;
import org.mortgage.model.*;

public interface OverpaymentCalculationService {

    Overpayment calculate(final BigDecimal rateNumber, final InputData inputData);
}
