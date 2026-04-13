package Response;

import lombok.Data;

public class DashboardResponse {

    private Long totalUsuarios;
    private Long totalEventos;
    private Long eventosHoy;
    private Double promedioEventosPorUsuario;
}