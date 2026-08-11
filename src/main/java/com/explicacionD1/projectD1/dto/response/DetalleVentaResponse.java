package com.explicacionD1.projectD1.dto.response;

public record DetalleVentaResponse(
        Long id,
        VentaResponse venta,
        ProductoResponse producto,
        Long cantidad,
        Double subtotal

) {
}
