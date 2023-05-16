package org.mortgage.service;


import java.util.List;
import org.mortgage.model.*;

public interface RateCalculationService {

    List<Rate> calculate(InputData inputData);
}
