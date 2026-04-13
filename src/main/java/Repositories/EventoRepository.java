package Repositories;

import Models.Evento;
import Models.TipoEvento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    // Eventos por usuario
    List<Evento> findByUsuarioId(Long usuarioId);

    // Eventos por tipo
    List<Evento> findByTipo(TipoEvento tipo);

    // Eventos en un rango de fechas
    List<Evento> findByFechaBetween(LocalDateTime inicio, LocalDateTime fin);

    // 🔥 Cantidad de eventos hoy
    @Query("SELECT COUNT(e) FROM Evento e WHERE DATE(e.fecha) = CURRENT_DATE")
    Long contarEventosHoy();

    // Si no funciona DATE(e.fecha) usar la de abajo
    // @Query("SELECT COUNT(e) FROM Evento e WHERE e.fecha >= :inicio AND e.fecha <= :fin")

    //Long contarEventosEntre(LocalDateTime inicio, LocalDateTime fin);

    // 🔥 Cantidad por tipo
    Long countByTipo(TipoEvento tipo);

}