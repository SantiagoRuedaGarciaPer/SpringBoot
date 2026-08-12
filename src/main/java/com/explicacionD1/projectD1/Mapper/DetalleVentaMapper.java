package com.explicacionD1.projectD1.Mapper;

import com.explicacionD1.projectD1.DTO.Request.DetalleVentaRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.DTO.Response.VentaResponse;
import com.explicacionD1.projectD1.Model.DetalleVenta;
import com.explicacionD1.projectD1.Model.Producto;
import com.explicacionD1.projectD1.Model.Venta;
import org.springframework.stereotype.Component;

@Component
public class DetalleVentaMapper {
    public DetalleVentaResponse entityToDto(DetalleVenta dv, VentaResponse vr, ProductoResponse pr){
        if(dv == null) return null;

        return new DetalleVentaResponse(
                dv.getId(),
                vr,
                pr,
                dv.getCantidad(),
                dv.getSubtotal()
        );
    }

    public DetalleVenta dtoToentity(DetalleVentaRequest dvr, Producto p, Venta v){
        if (dvr == null || p == null || v == null) return null;
        DetalleVenta dv = new DetalleVenta();
        dv.setVenta(v);
        dv.setProducto(p);
        dv.setCantidad(dvr.cantidad());
        dv.setSubtotal(dvr.subtotal());

        return dv;
    }
    public void UpdateDtoToentity(DetalleVenta dv, DetalleVentaRequest dvr, Producto p, Venta v){
        if (dvr == null || p == null || v == null || dv == null) return;
        dv.setVenta(v);
        dv.setProducto(p);
        dv.setCantidad(dvr.cantidad());
        dv.setSubtotal(dvr.subtotal());
    }
}
