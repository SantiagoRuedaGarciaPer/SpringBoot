package com.explicacionD1.projectD1.Repository;

import com.explicacionD1.projectD1.Model.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {

    List<DetalleVenta> findByProductoId(Long id);

    List<DetalleVenta> findByVentaId(Long id);

    List<DetalleVenta> findBySubtotalGreaterThanEqual(Double monto);

    List<DetalleVenta> findBySubtotalLessThanEqual(Double monto);

    List<DetalleVenta> existByCantidadLessThanEqual(Double cantidad);
}
