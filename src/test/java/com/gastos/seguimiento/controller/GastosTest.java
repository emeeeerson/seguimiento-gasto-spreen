package com.gastos.seguimiento.controller;

import com.gastos.seguimiento.controller.gastos.GastosController;
import com.gastos.seguimiento.model.gatos.Gastos;
import com.gastos.seguimiento.service.gatos.GastosService;
import com.gastos.seguimiento.security.JWTUtil;
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

@ExtendWith(MockitoExtension.class)
public class GastosTest {

    @Mock
    private GastosService gastosService;

    @Mock
    private JWTUtil jwtUtil; // Agrega este mock para JWTUtil

    @InjectMocks
    private GastosController gastosController;

    @Test
    public void testGetGastoById() {
        String gastoId = "123";
        Gastos mockGasto = new Gastos();
        mockGasto.setId(gastoId);
        mockGasto.setDescripcion("Test Gasto");

        when(gastosService.findById(gastoId)).thenReturn(mockGasto);

        ResponseEntity<Gastos> response = gastosController.getGastoById(gastoId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Gasto", response.getBody().getDescripcion());
    }

    @Test
    public void testGetAllGastos() {
        Gastos gasto1 = new Gastos();
        gasto1.setId("1");
        gasto1.setDescripcion("Gasto 1");

        Gastos gasto2 = new Gastos();
        gasto2.setId("2");
        gasto2.setDescripcion("Gasto 2");

        List<Gastos> mockGastosList = Arrays.asList(gasto1, gasto2);

        when(gastosService.findAll()).thenReturn(mockGastosList);

        List<Gastos> result = gastosController.getAllGastos();

        assertEquals(2, result.size());
        assertEquals("Gasto 1", result.get(0).getDescripcion());
    }

    @Test
    public void testSaveGasto() {
        // Arrange
        String token = "Bearer testToken";  // Token simulado para la prueba
        Gastos mockGasto = new Gastos();
        mockGasto.setDescripcion("Nuevo Gasto");
        mockGasto.setMonto(100.0);

        // Configura jwtUtil para que devuelva un email simulado
        when(jwtUtil.extractEmail("testToken")).thenReturn("test@example.com");

        when(gastosService.save(any(Gastos.class))).thenReturn(mockGasto);

        // Act
        ResponseEntity<Gastos> response = gastosController.createGasto(token, mockGasto);
        Gastos result = response.getBody();

        // Assert
        assertEquals("Nuevo Gasto", result.getDescripcion());
        assertEquals(100.0, result.getMonto());
    }
}
