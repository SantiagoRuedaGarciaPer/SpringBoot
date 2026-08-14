package com.explicacionD1.projectD1.Service;

import com.explicacionD1.projectD1.DTO.Request.DetalleVentaRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;


import java.util.List;

public interface DetalleVentaService {
    DetalleVentaResponse crear(DetalleVentaRequest dto);
    DetalleVentaResponse actualizar(Long id, DetalleVentaRequest dto);
    List<DetalleVentaResponse> listarTodos();
    DetalleVentaResponse buscarPorId(Long id);
    void eliminar(Long id);

    List<DetalleVentaResponse> buscarPorIdVenta(Long id);
    List<DetalleVentaResponse> buscarPorIdProducto(Long id);

    List<DetalleVentaResponse> buscarCatantidadMenorQue(Long cantidad);
}
