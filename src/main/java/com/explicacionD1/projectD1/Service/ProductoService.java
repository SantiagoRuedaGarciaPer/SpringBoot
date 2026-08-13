package com.explicacionD1.projectD1.Service;

import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;

import java.util.List;

public interface ProductoService {
    ProductoResponse guardar(ProductoRequest dto);

    List<ProductoResponse> obtenerTodos();

    ProductoResponse buscar(Long id);

    ProductoResponse actualizar(Long id, ProductoRequest dto);

    void eliminar(Long id);

    List<ProductoResponse> buscarPorNombre(String nombre);

    List<ProductoResponse> buscarPrecioVentaMayor(Double monto);

    List<ProductoResponse> buscarPrecioVentaMenor(Double monto);

    List<ProductoResponse> buscarPrecioCompraMayor(Double monto);

    List<ProductoResponse> buscarPrecioVentaEntre(Double monto1, Double monto2);

    List<ProductoResponse> buscarPrecioCompraMenor(Double monto);

    List<ProductoResponse> buscarPrecioCompraEntre(Double monto1, Double monto2);

    List<ProductoResponse> buscarNombrePrecioVentaMayor(String nombre, Double monto);


}
