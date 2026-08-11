package com.explicacionD1.projectD1.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record DetalleVentaRequest(
        @NotNull(message = "la venta no puede ser nula")
        @Positive(message = "La codigo de venta debe ser positivo")
        Long venta_id,
        @NotNull(message = "el producot no puede ser nulo")
        @Positive(message = "El codigo del producto no puede ser nulo")
        Long producto_id,
        @NotNull(message = "la cantidad no puede ser nula")
        @Positive(message = "La cantidad debe ser positiva")
        Long cantidad,
        @NotNull(message = "el subtotal no puede ser nulo")
        @Positive(message = "El subtotal debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "el subtotal debe tener maximo 10 digitos y 2 decimales")
        Double subtotal
) {
}
