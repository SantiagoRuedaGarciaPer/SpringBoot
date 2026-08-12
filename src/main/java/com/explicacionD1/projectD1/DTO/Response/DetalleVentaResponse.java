package com.explicacionD1.projectD1.DTO.Response;

public record DetalleVentaResponse(
        Long id,
        VentaResponse venta,
        ProductoResponse producto,
        Long cantidad,
        Double subtotal

) {
}
