package com.explicacionD1.projectD1.Service.Impl;

import com.explicacionD1.projectD1.DTO.Request.DetalleVentaRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;
import com.explicacionD1.projectD1.Mapper.DetalleVentaMapper;
import com.explicacionD1.projectD1.Mapper.ProductoMapper;
import com.explicacionD1.projectD1.Mapper.VentaMapper;
import com.explicacionD1.projectD1.Model.DetalleVenta;
import com.explicacionD1.projectD1.Model.Producto;
import com.explicacionD1.projectD1.Model.Venta;
import com.explicacionD1.projectD1.Repository.DetalleVentaRepository;
import com.explicacionD1.projectD1.Repository.ProductoRepository;
import com.explicacionD1.projectD1.Repository.VentaRepository;
import com.explicacionD1.projectD1.Service.DetalleVentaService;
import com.explicacionD1.projectD1.Service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DetalleVentaImpl implements DetalleVentaService {
    private final DetalleVentaRepository detalleVentaRepository;
    private final DetalleVentaMapper detalleVentaMapper;
    private final VentaRepository ventaRepository;
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;
    private final VentaMapper ventaMapper;

    @Override
    public DetalleVentaResponse crear(DetalleVentaRequest dto) {
        Producto producto = productoRepository.findById(dto.producto_id()).orElseThrow(()-> new EntityNotFoundException("no existe dicho producto"));
        Venta venta =ventaRepository.findById(dto.venta_id()).orElseThrow(()-> new EntityNotFoundException("No existe la venta a relacionar"));
        DetalleVenta detalleVenta = detalleVentaMapper.dtoToentity(dto, producto, venta);
        return detalleVentaMapper.entityToDto(detalleVentaRepository.save(detalleVenta), ventaMapper.entityToDto(venta), productoMapper.entityToDto(producto));
    }

    @Override
    public DetalleVentaResponse actualizar(Long id, DetalleVentaRequest dto) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No existe el detalle de venta a actulizar"));
        Producto producto = detalleVenta.getProducto();
        Venta venta = detalleVenta.getVenta();
        detalleVentaMapper.UpdateDtoToentity(detalleVenta, dto, producto, venta);
        return detalleVentaMapper.entityToDto(detalleVentaRepository.save(detalleVenta), ventaMapper.entityToDto(venta), productoMapper.entityToDto(producto));
    }

    @Override
    public void eliminar(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No existe el detalle de venta a actulizar"));
        detalleVentaRepository.deleteById(id);

    }

    @Override
    public List<DetalleVentaResponse> listarTodos() {
        List<DetalleVenta> detalleVentas = detalleVentaRepository.findAll();
        return detalleVentas.stream().map(
                p->
                        detalleVentaMapper.entityToDto(p,
                                ventaMapper.entityToDto(p.getVenta()),
                                productoMapper.entityToDto(p.getProducto())

        )).toList();
    }

    @Override
    public DetalleVentaResponse buscarPorId(Long id) {
        DetalleVenta detalleVenta = detalleVentaRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("No se encuentra el detalle venta"));
        return detalleVentaMapper.entityToDto(detalleVenta, ventaMapper.entityToDto(detalleVenta.getVenta()), productoMapper.entityToDto(detalleVenta.getProducto()));
    }

    @Override
    public List<DetalleVentaResponse> buscarPorIdVenta(Long id) {
        return detalleVentaRepository.findByVentaId(id).stream().map(
                p-> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public List<DetalleVentaResponse> buscarPorIdProducto(Long id) {
        return detalleVentaRepository.findByProductoId(id).stream().map(
                p-> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }

    @Override
    public List<DetalleVentaResponse> buscarCatantidadMenorQue(Long cantidad) {
        return detalleVentaRepository.findByCantidadLessThanEqual(cantidad).stream().map(
                p-> detalleVentaMapper.entityToDto(p,
                        ventaMapper.entityToDto(p.getVenta()),
                        productoMapper.entityToDto(p.getProducto()))
        ).toList();
    }
}
