package com.explicacionD1.projectD1.Repository;

import com.explicacionD1.projectD1.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByProductoId(Long id);

    List<DetalleVenta> findByVentaId(Long id);

    List<DetalleVenta> findBySubtotalGreaterThanEqual(Double monto);

    List<DetalleVenta> findBySubtotalLessThanEqual(Double monto);

    List<DetalleVenta> existByCantidadLessThanEqual(Double cantidad);
}
