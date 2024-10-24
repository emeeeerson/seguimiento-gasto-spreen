    package com.gastos.seguimiento.model.gatos;

    import lombok.Data;
    import org.springframework.data.annotation.Id;
    import org.springframework.data.mongodb.core.mapping.Document;

    import java.time.LocalDate;

        @Document(collection = "gastos")
        @Data
    public class Gastos {
        @Id
        private String id;
        private String descripcion;
        private Double monto;
        private String categoria;
        private LocalDate fecha;
    }