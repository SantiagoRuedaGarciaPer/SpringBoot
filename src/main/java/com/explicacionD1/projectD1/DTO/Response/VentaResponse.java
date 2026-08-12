package com.explicacionD1.projectD1.DTO.Response;

import java.util.Date;

public record VentaResponse(
        Long id,
        Date fecha,
        Double total
) {
}
