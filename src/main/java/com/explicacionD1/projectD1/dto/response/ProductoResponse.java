package com.explicacionD1.projectD1.dto.response;

public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        Double precioCompra,
        Double precioVenta

) {
}
