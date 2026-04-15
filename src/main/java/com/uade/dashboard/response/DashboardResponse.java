package com.uade.dashboard.response;

public class DashboardResponse {

    private Long totalUsuarios;
    private Long totalEventos;
    private Long eventosHoy;
    private Double PromedioEventosPorUsuario;
    public DashboardResponse() {
    }

    public Long getTotalUsuarios() {
        return totalUsuarios;
    }

    public void setTotalUsuarios(Long totalUsuarios) {
        this.totalUsuarios = totalUsuarios;
    }

    public Long getTotalEventos() {
        return totalEventos;
    }

    public void setTotalEventos(Long totalEventos) {
        this.totalEventos = totalEventos;
    }

    public Long getEventosHoy() {
        return eventosHoy;
    }

    public void setEventosHoy(Long eventosHoy) {
        this.eventosHoy = eventosHoy;
    }
    public Double getPromedioEventosPorUsuario() {
        return PromedioEventosPorUsuario;
    }
    public void setPromedioEventosPorUsuario(Double promedioEventosPorUsuario) {
        this.PromedioEventosPorUsuario = promedioEventosPorUsuario;
    }
}