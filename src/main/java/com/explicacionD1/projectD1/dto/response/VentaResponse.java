package com.explicacionD1.projectD1.dto.response;

import java.util.Date;

public record VentaResponse(
        Long id,
        Date fecha,
        Double total
) {
}
