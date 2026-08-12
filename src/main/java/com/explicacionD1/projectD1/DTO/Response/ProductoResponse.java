package com.explicacionD1.projectD1.DTO.Response;

public record ProductoResponse(
        Long id,
        String nombre,
        String descripcion,
        Double precioCompra,
        Double precioVenta

) {
}
