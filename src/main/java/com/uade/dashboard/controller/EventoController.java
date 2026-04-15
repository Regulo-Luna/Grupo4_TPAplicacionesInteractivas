package com.uade.dashboard.controller;

import com.uade.dashboard.model.Evento;
import com.uade.dashboard.repository.EventoRepository;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    private final EventoRepository eventoRepository;

    public EventoController(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @PostMapping
    public Evento crearEvento(@RequestBody Evento evento) {

        if (evento.getFecha() == null) {
            evento.setFecha(LocalDateTime.now());
        }

        return eventoRepository.save(evento);
    }

    @GetMapping
    public Iterable<Evento> listarEventos() {
        return eventoRepository.findAll();
    }
}