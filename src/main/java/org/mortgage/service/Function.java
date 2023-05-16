package org.mortgage.service;


import java.math.BigDecimal;
import org.mortgage.model.*;

public interface Function {

    BigDecimal calculate(Rate rate);
}
