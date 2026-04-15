package com.uade.dashboard.services;

import com.uade.dashboard.repository.EventoRepository;
import com.uade.dashboard.repository.UsuarioRepository;
import com.uade.dashboard.response.DashboardResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public DashboardService(UsuarioRepository usuarioRepository, EventoRepository eventoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.eventoRepository = eventoRepository;
    }

    public DashboardResponse obtenerResumen() {
        DashboardResponse response = new DashboardResponse();

        Long totalUsuarios = usuarioRepository.count();
        Long totalEventos = eventoRepository.count();

        LocalDateTime inicio = LocalDate.now().atStartOfDay();
        LocalDateTime fin = inicio.plusDays(1);

        Long eventosHoy = eventoRepository.contarEventosEntre(inicio, fin);

        Double promedio = 0.0;
        if (totalUsuarios != null && totalUsuarios > 0) {
            promedio = (double) totalEventos / totalUsuarios;
        }

        response.setTotalUsuarios(totalUsuarios);
        response.setTotalEventos(totalEventos);
        response.setEventosHoy(eventosHoy);
        response.setPromedioEventosPorUsuario(promedio);

        return response;
    }
}