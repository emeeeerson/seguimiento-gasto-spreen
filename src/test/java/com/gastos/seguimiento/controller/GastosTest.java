package com.gastos.seguimiento.controller;

import com.gastos.seguimiento.controller.gastos.GastosController;
import com.gastos.seguimiento.model.gatos.Gastos;
import com.gastos.seguimiento.service.gatos.GastosService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@ExtendWith(MockitoExtension.class)  // Necesario para habilitar Mockito en las pruebas
public class GastosTest {

    @Mock
    private GastosService gastosService;  // Simulación del servicio

    @InjectMocks
    private GastosController gastosController;  // Inyección del controlador con el mock

    // Test para obtener un gasto por ID
    @Test
    public void testGetGastoById() {
        // Arrange
        String gastoId = "123";
        Gastos mockGasto = new Gastos();
        mockGasto.setId(gastoId);
        mockGasto.setDescripcion("Test Gasto");

        when(gastosService.findById(gastoId)).thenReturn(mockGasto);

        // Act
        ResponseEntity<Gastos> response = gastosController.getGastoById(gastoId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Gasto", response.getBody().getDescripcion());
    }

    // Test para obtener todos los gastos
    @Test
    public void testGetAllGastos() {
        // Arrange
        Gastos gasto1 = new Gastos();
        gasto1.setId("1");
        gasto1.setDescripcion("Gasto 1");

        Gastos gasto2 = new Gastos();
        gasto2.setId("2");
        gasto2.setDescripcion("Gasto 2");

        List<Gastos> mockGastosList = Arrays.asList(gasto1, gasto2);

        when(gastosService.findAll()).thenReturn(mockGastosList);

        // Act
        List<Gastos> result = gastosController.getAllGastos();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Gasto 1", result.get(0).getDescripcion());
    }

    // Test para guardar un gasto
    @Test
    public void testSaveGasto() {
        // Arrange
        Gastos mockGasto = new Gastos();
        mockGasto.setDescripcion("Nuevo Gasto");
        mockGasto.setMonto(100.0);  // Establece un monto válido

        when(gastosService.save(any(Gastos.class))).thenReturn(mockGasto);

        // Act
        ResponseEntity<Gastos> response = gastosController.createGasto(mockGasto);
        Gastos result = response.getBody();  // Obtener el gasto desde el ResponseEntity

        // Assert
        assertEquals("Nuevo Gasto", result.getDescripcion());
        assertEquals(100.0, result.getMonto());  // Verifica que el monto sea correcto
    }



}
