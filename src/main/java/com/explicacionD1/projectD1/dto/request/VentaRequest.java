package com.explicacionD1.projectD1.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;

public record VentaRequest(
        @NotNull(message = "la fecha no puede ser nula")
        Date fecha,
        @NotNull(message = "el total no puede ser nulo")
        @Positive(message = "El total ser positivo")
        @Digits(integer = 10, fraction = 2, message = "el precio de venta debe tener maximo 10 digitos y dos decimales")
        Double total) {
}
