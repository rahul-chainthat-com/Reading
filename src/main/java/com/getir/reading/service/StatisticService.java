package com.getir.reading.service;

import com.getir.reading.repository.OrderHeaderRepository;
import com.getir.reading.repository.StatisticForOneMonthConverter;
import com.getir.reading.model.response.MonthlyReportResponse;
import com.getir.reading.model.response.StatisticForOneMonth;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatisticService {

    private final OrderHeaderRepository orderHeaderRepository;

    public StatisticService(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    public MonthlyReportResponse getMonthlyReport(Long customerId) {
        log.debug("MonthlyReportResponse fo customer ===>{}",customerId);

        List<StatisticForOneMonthConverter> list = orderHeaderRepository.generateMonthlyReport(customerId);
        List<StatisticForOneMonth> statisticForOneMonth = list.stream()
                .map(StatisticForOneMonth::of).collect(Collectors.toList());
        return MonthlyReportResponse.builder()
                .statisticForOneMonthList(statisticForOneMonth)
                .build();
    }
}
