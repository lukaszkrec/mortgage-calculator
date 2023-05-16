package org.mortgage.service;

import java.util.List;
import org.mortgage.model.*;

public interface SummaryService {

    Summary calculateSummary(List<Rate> rates);
}
