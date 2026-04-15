package com.uade.dashboard.repository;

import com.uade.dashboard.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface EventoRepository extends JpaRepository<Evento, Long> {

    List<Evento> findByTipo(String tipo);

    Long countByTipo(String tipo);

    @Query("SELECT COUNT(e) FROM Evento e WHERE e.fecha >= :inicio AND e.fecha < :fin")
    Long contarEventosEntre(LocalDateTime inicio, LocalDateTime fin);
}