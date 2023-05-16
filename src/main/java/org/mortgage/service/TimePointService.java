package org.mortgage.service;


import java.math.BigDecimal;
import org.mortgage.model.*;

public interface TimePointService {

    TimePoint calculate(final BigDecimal rateNumber, final InputData inputData);

    TimePoint calculate(BigDecimal rateNumber , Rate previousRate);
}
