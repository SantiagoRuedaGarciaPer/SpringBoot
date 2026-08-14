package com.explicacionD1.projectD1.Service.Impl;

import com.explicacionD1.projectD1.DTO.Request.VentaRequest;
import com.explicacionD1.projectD1.DTO.Response.VentaResponse;
import com.explicacionD1.projectD1.Mapper.VentaMapper;
import com.explicacionD1.projectD1.Model.Venta;
import com.explicacionD1.projectD1.Repository.VentaRepository;
import com.explicacionD1.projectD1.Service.VentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;
    private final VentaMapper ventaMapper;

    @Override
    public VentaResponse guardar(VentaRequest dto) {
        Venta venta = ventaMapper.dtoToEntity(dto);
        return ventaMapper.entityToDto(ventaRepository.save(venta));
    }

    @Override
    public List<VentaResponse> obtenerTodos() {
        return ventaRepository.findAll().stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public VentaResponse buscar(Long id) {
        Venta venta = ventaRepository.findById(id).orElseThrow(()->new RuntimeException("no se encuentra la venta"));
        return ventaMapper.entityToDto(venta);

    }

    @Override
    public VentaResponse actualizar(Long id, VentaRequest dto) {
        Venta venta = ventaRepository.findById(id).orElseThrow(()->new RuntimeException("no se encuentra la venta a actualizar"));
        ventaMapper.updateDtoToEntity(venta, dto);
        return ventaMapper.entityToDto(ventaRepository.save(venta));
    }

    @Override
    public void eliminar(Long id) {

    }

    @Override
    public List<VentaResponse> buscarTotalMayorQue(Double monto) {
        List<Venta> ventas = ventaRepository.findByTotalGreaterThanEqual(monto);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }


    @Override
    public List<VentaResponse> buscarTotalMenorQue(Double monto) {

        List<Venta> ventas = ventaRepository.findByTotalLessThanEqual(monto);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public List<VentaResponse> buscarFechaEntre(Date fecha1, Date fecha2) {

        List<Venta> ventas = ventaRepository.findByFechaBetween(fecha1, fecha2);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }

    @Override
    public List<VentaResponse> buscarFecha(Date fecha) {
        List<Venta> ventas = ventaRepository.findByFecha(fecha);
        return ventas.stream().map(ventaMapper::entityToDto).toList();

    }

    @Override
    public List<VentaResponse> buscarFechaMesAnio(int mes, int anio) {
        List<Venta> ventas = ventaRepository.findByMesYAnio(mes, anio);
        return ventas.stream().map(ventaMapper::entityToDto).toList();
    }
}
