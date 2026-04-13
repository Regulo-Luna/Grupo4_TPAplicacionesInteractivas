package Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
    @Entity
    public class Evento {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String tipo;
        // Ej: LOGIN, COMPRA, CLICK, ERROR

        private String descripcion;

        @Column(nullable = false)
        private LocalDateTime fecha;

        private Double valor;
        // opcional: útil para métricas (ej: monto, duración, etc.)

        @ManyToOne
        @JoinColumn(name = "usuario_id")
        private Usuario usuario;

}
