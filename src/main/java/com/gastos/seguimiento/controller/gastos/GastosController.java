package com.gastos.seguimiento.controller.gastos;

import com.gastos.seguimiento.exception.GastosException;
import com.gastos.seguimiento.model.gatos.DateRange;
import com.gastos.seguimiento.model.gatos.Gastos;
import com.gastos.seguimiento.service.gatos.GastosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/gastos")
public class GastosController {

    @Autowired
    private GastosService gastosService;

    // Obtener todos los gastos
    @GetMapping("/getAll")
    public List<Gastos> getAllGastos() {
        return gastosService.findAll();
    }

    // Obtener un gasto por ID
    @GetMapping("/getById/{id}")
    public ResponseEntity<Gastos> getGastoById(@PathVariable String id) {
        Gastos gasto = gastosService.findById(id);
        if (gasto == null) {
            throw new GastosException.GastoNotFoundException("Gasto with ID " + id + " not found");
        }
        return new ResponseEntity<>(gasto, HttpStatus.OK);
    }

    // Crear un nuevo gasto
    @PostMapping("/save")
    public ResponseEntity<Gastos> createGasto(@RequestBody Gastos gasto) {
        // Validar datos
        if (gasto.getDescripcion() == null || gasto.getDescripcion().isEmpty()) {
            throw new GastosException.InvalidGastoDataException("Description is required");
        }
        if (gasto.getMonto() == null || gasto.getMonto() <= 0) {
            throw new GastosException.InvalidGastoDataException("Amount must be greater than zero");
        }

        Gastos newGasto = gastosService.save(gasto);
        return new ResponseEntity<>(newGasto, HttpStatus.CREATED);
    }

    // Actualizar un gasto existente
    @PutMapping("/update/{id}")
    public ResponseEntity<Gastos> updateGasto(@PathVariable String id, @RequestBody Gastos gastoDetails) {
        Gastos gasto = gastosService.findById(id);
        if (gasto == null) {
            throw new GastosException.GastoNotFoundException("Gasto with ID " + id + " not found");
        }

        // Validar los nuevos datos
        if (gastoDetails.getDescripcion() == null || gastoDetails.getDescripcion().isEmpty()) {
            throw new GastosException.InvalidGastoDataException("Description is required");
        }
        if (gastoDetails.getMonto() == null || gastoDetails.getMonto() <= 0) {
            throw new GastosException.InvalidGastoDataException("Amount must be greater than zero");
        }

        // Actualizar campos
        gasto.setDescripcion(gastoDetails.getDescripcion());
        gasto.setMonto(gastoDetails.getMonto());
        gasto.setCategoria(gastoDetails.getCategoria());
        gasto.setFecha(gastoDetails.getFecha());

        Gastos updatedGasto = gastosService.save(gasto);
        return new ResponseEntity<>(updatedGasto, HttpStatus.OK);
    }

    // Eliminar un gasto por ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteGasto(@PathVariable String id) {
        Gastos gasto = gastosService.findById(id);
        if (gasto == null) {
            throw new GastosException.GastoNotFoundException("Gasto with ID " + id + " not found");
        }

        gastosService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Filtrar gastos por rango de fechas
    @PostMapping("/findByDates")
    public ResponseEntity<List<Gastos>> filterGastosByDateRange(@RequestBody DateRange dateRange) {
        LocalDate startDate = dateRange.getStartDate();
        LocalDate endDate = dateRange.getEndDate();

        // Verificar que las fechas sean válidas
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new GastosException.InvalidDateRangeException("Invalid date range");
        }

        List<Gastos> gastos = gastosService.findByFechaBetween(startDate, endDate);
        return new ResponseEntity<>(gastos, HttpStatus.OK);
    }
}
