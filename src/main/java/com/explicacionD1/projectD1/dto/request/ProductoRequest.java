package com.explicacionD1.projectD1.dto.request;

import jakarta.validation.constraints.*;

public record ProductoRequest(
        @NotBlank(message = "No se permite el nombre vacio")
        @Size(min = 3, max = 50, message = "el nombre debe tener entre 2 y 50 caracteres")
        String nombre,
        @NotBlank(message = "No se permite la descripcion vacia")
        @Size(min = 3, max = 50, message = "La descripcion no puede ser vacia")
        String descripcion,
        @NotNull(message = "el precio de compra no puede ser nulo")
        @Positive(message = "El precio de compra debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "el precio de compra debe tener maximo 10 digitos y dos decimales")
        Double precioCompra,
        @NotNull(message = "el precio de venta no puede ser nulo")
        @Positive(message = "El precio de venta debe ser positivo")
        @Digits(integer = 10, fraction = 2, message = "el precio de venta debe tener maximo 10 digitos y dos decimales")
        Double precioVenta) {
}
