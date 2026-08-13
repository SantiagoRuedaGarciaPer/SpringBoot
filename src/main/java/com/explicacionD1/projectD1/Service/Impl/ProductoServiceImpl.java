package com.explicacionD1.projectD1.Service.Impl;

import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.Mapper.ProductoMapper;
import com.explicacionD1.projectD1.Model.Producto;
import com.explicacionD1.projectD1.Repository.ProductoRepository;
import com.explicacionD1.projectD1.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {
    private final ProductoRepository productoRepository;
    private final ProductoMapper productoMapper;

    @Override
    public ProductoResponse guardar(ProductoRequest dto) {
        Producto producto = productoMapper.DtoToEntity(dto);
        return productoMapper.entityToDto(productoRepository.save(producto));
    }

    @Override
    public List<ProductoResponse> obtenerTodos() {
        return productoRepository.findAll().stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public ProductoResponse buscar(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(()->  new RuntimeException("No se encontro el producto"));
        return productoMapper.entityToDto(producto);
    }

    @Override
    //                                 Entity old, Entity new
    public ProductoResponse actualizar(Long id, ProductoRequest dto) {
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el producto a actualizar"));
        productoMapper.updateDtoToEntity(producto, dto);
        return productoMapper.entityToDto(productoRepository.save(producto));
    }

    @Override
    public void eliminar(Long id) {
        Producto producto = productoRepository.findById(id).orElseThrow(()-> new RuntimeException("No se encontro el producto a eliminar"));
        productoRepository.delete(producto);
    }

    @Override
    public List<ProductoResponse> buscarPorNombre(String nombre) {
        List<Producto> producto = productoRepository.findByNombre(nombre);
        return producto.stream().map(productoMapper::entityToDto).toList();

    }

    @Override
    public List<ProductoResponse> buscarPrecioVentaMayor(Double monto) {
        List<Producto> productos = productoRepository.findByPrecioVentaGreaterThanEqual(monto);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> buscarPrecioVentaMenor(Double monto) {
        List<Producto> productos = productoRepository.findByPrecioVentaLessThanEqual(monto);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> buscarPrecioCompraMayor(Double monto) {
        List<Producto> productos = productoRepository.findByPrecioCompraGreaterThanEqual(monto);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> buscarPrecioVentaEntre(Double monto1, Double monto2) {
        List<Producto> productos = productoRepository.findByPrecioVentaBetween(monto1, monto2);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> buscarPrecioCompraMenor(Double monto) {
        List<Producto> productos = productoRepository.findByPrecioCompraLessThanEqual(monto);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> buscarPrecioCompraEntre(Double monto1, Double monto2) {
        List<Producto> productos = productoRepository.findByPrecioCompraBetween(monto1, monto2);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }

    @Override
    public List<ProductoResponse> buscarNombrePrecioVentaMayor(String nombre, Double monto) {
        List<Producto> productos = productoRepository.findByNombreAndPrecioVentaGreaterThanEqual(nombre, monto);
        return productos.stream().map(productoMapper::entityToDto).toList();
    }
}
