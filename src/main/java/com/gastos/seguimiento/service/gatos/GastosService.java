package com.gastos.seguimiento.service.gatos;

import com.gastos.seguimiento.model.gatos.Gastos;
import com.gastos.seguimiento.repository.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class GastosService {
    @Autowired
    private GastosRepository gastosRepository;

    public List<Gastos> findAll() {
        return gastosRepository.findAll();
    }

    public Gastos findById(String id) {
        return gastosRepository.findById(id).orElse(null);
    }

    public Gastos save(Gastos gastos) {
        return gastosRepository.save(gastos);
    }

    public void delete(String id) {
        gastosRepository.deleteById(id);
    }

    //obtener los gastos entre dos fechas
    public List<Gastos> findByFechaBetween(LocalDate startDate, LocalDate endDate) {
        return gastosRepository.findByFechaBetween(startDate, endDate);
    }

    // para obtener los gastos de un usuario específico
    public List<Gastos> findByUserEmail(String email) {
        return gastosRepository.findByUserEmail(email);
    }
}
