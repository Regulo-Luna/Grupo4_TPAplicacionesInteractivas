package Controller;

import Response.DashboardResponse;
import Services.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse obtenerDashboard() {
        return dashboardService.obtenerEstadisticas();
    }
}