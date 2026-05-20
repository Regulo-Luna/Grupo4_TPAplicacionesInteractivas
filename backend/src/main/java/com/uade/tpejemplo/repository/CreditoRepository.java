package com.uade.tpejemplo.repository;

import com.uade.tpejemplo.model.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    List<Credito> findByClienteDni(String dni);

    // 1. Suma el total financiado
    @Query("SELECT SUM(c.importeCuota) FROM Credito c")
    Double sumMontoTotal();

    // 2. Cuenta la cantidad total de créditos (útil para las estadísticas)
    @Query("SELECT COUNT(c) FROM Credito c")
    Long countAll();
}
