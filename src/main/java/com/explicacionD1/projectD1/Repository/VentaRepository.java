package com.explicacionD1.projectD1.Repository;

import com.explicacionD1.projectD1.Model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> findByFecha(Date fecha);

    @Query("""
            Select v from Venta v
            where MONTH(v.fecha) = :mes AND YEAR(v.fecha) = :anio
            """)
    List<Venta> findByMesYAnio(
            @Param("mes") int mes,
            @Param("anio") int anio
    );

    List<Venta> findByTotalGreaterThanEqual(Double monto);

    List<Venta> findByTotalLessThanEqual(Double monto);

    List<Venta> findByFechaBetween(Date fecha1, Date fecha2);


}
