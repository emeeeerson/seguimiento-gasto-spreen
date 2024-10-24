package com.gastos.seguimiento.repository;

import com.gastos.seguimiento.model.gatos.Gastos;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface    GastosRepository extends MongoRepository<Gastos, String> {

    // Método para encontrar gastos entre dos fechas
    List<Gastos> findByFechaBetween(LocalDate startDate, LocalDate endDate);
}
