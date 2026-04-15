package com.uade.dashboard.controller;

import com.uade.dashboard.response.DashboardResponse;
import com.uade.dashboard.services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/dashboard")
    public DashboardResponse obtenerDashboard() {
        return dashboardService.obtenerResumen();
    }
}