package Services;

import Response.DashboardResponse;
import Repositories.EventoRepository;
import Repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final EventoRepository eventoRepository;

    public DashboardResponse obtenerEstadisticas() {

        Long totalUsuarios = usuarioRepository.count();
        Long totalEventos = eventoRepository.count();

        // 🔥 Eventos de hoy
        LocalDate hoy = LocalDate.now();
        LocalDateTime inicioDia = hoy.atStartOfDay();
        LocalDateTime finDia = hoy.atTime(23, 59, 59);

        Long eventosHoy = eventoRepository
                .findByFechaBetween(inicioDia, finDia)
                .stream()
                .count();

        // 🔥 Promedio eventos por usuario
        Double promedio = 0.0;
        if (totalUsuarios > 0) {
            promedio = (double) totalEventos / totalUsuarios;
        }

        // Armar respuesta
        DashboardResponse response = new DashboardResponse();
        response.setTotalUsuarios(totalUsuarios);
        response.setTotalEventos(totalEventos);
        response.setEventosHoy(eventosHoy);
        response.setPromedioEventosPorUsuario(promedio);

        return response;
    }
}