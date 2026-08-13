package com.explicacionD1.projectD1.Service;

import com.explicacionD1.projectD1.DTO.Request.VentaRequest;
import com.explicacionD1.projectD1.DTO.Response.VentaResponse;

import java.util.Date;
import java.util.List;

public interface VentaService {
    VentaResponse guardar(VentaRequest dto);

    List<VentaResponse> obtenerTodos();

    VentaResponse buscar(Long id);

    VentaResponse actualizar(Long id, VentaRequest dto);

    void eliminar(Long id);

    List<VentaResponse> buscarTotalMayorQue(Double monto);

    List<VentaResponse> buscarTotalMenorQue(Double monto);

    List<VentaResponse> buscarFechaEntre(Date fecha1, Date fecha2);

    List<VentaResponse> buscarFecha(Date fecha);

    List<VentaResponse> buscarFechaMesAnio(int mes, int anio);
}
