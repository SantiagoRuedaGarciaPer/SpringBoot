package com.explicacionD1.projectD1.Mapper;

import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.Model.Producto;
import org.springframework.stereotype.Component;

@Component
public class ProductoMapper {
    public ProductoResponse entityToDto(Producto p) {
        if (p == null) return null;

        return new ProductoResponse(
                p.getId(),
                p.getNombre(),
                p.getDescripcion(),
                p.getPrecioCompra(),
                p.getPrecioVenta()
        );
    }

    public Producto DtoToEntity(ProductoRequest pr) {
        if (pr == null) return null;

        Producto p = new Producto();
        p.setNombre(pr.nombre());
        p.setDescripcion(pr.descripcion());
        p.setPrecioCompra(pr.precioCompra());
        p.setPrecioVenta(pr.precioVenta());

        return p;

    }

    public void updateDtoToEntity(Producto p, ProductoRequest pr) {
        if (pr == null || p == null) return;
        p.setNombre(pr.nombre());
        p.setDescripcion(pr.descripcion());
        p.setPrecioVenta(pr.precioVenta());
        p.setPrecioCompra(pr.precioCompra());

    }
}
