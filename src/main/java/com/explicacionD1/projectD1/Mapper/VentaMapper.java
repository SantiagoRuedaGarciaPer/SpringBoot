package com.explicacionD1.projectD1.Mapper;

import com.explicacionD1.projectD1.DTO.Request.VentaRequest;
import com.explicacionD1.projectD1.DTO.Response.VentaResponse;
import com.explicacionD1.projectD1.Model.Venta;
import org.springframework.stereotype.Component;

@Component
public class VentaMapper {
    public VentaResponse entityToDto(Venta v){
        if (v == null) return null;

        return new VentaResponse(
                v.getId(),
                v.getFecha(),
                v.getTotal()
        );
    }

    public Venta dtoToEntity(VentaRequest vr){
        if (vr == null) return null;

        Venta v = new Venta();
        v.setFecha(vr.fecha());
        v.setTotal(vr.total());

        return v;
    }

    public void updateDtoToEntity(Venta v, VentaRequest vr){
        if (v == null || vr == null) return;

        v.setTotal(vr.total());
        v.setFecha(vr.fecha());
    }
}
