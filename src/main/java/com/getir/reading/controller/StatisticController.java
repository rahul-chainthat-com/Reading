package com.getir.reading.controller;

import com.getir.reading.model.response.MonthlyReportResponse;
import com.getir.reading.service.StatisticService;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
@Api(tags = {"Statistics"})
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{customerId}/monthly-report")
    public ResponseEntity<MonthlyReportResponse> getMonthlyStatistics(@PathVariable Long customerId) {
        return ResponseEntity.ok(statisticService.getMonthlyReport(customerId));
    }
}
