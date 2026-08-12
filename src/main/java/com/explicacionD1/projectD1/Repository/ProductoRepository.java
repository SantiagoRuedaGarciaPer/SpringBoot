package com.explicacionD1.projectD1.Repository;

import com.explicacionD1.projectD1.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByNombre(String nombre);

    List<Producto> findByPrecioCompraGreaterThanEqual(Double precio);

    List<Producto> findByPrecioCompraLessThanEqual(Double precio);

    List<Producto> findByPrecioCompraBetween(Double precio1, Double precio2);

    List<Producto> findByPrecioVentaGreaterThanEqual(Double precio);

    List<Producto> findByPrecioVentaLessThanEqual(Double precio);

    List<Producto> findByPrecioVentaBetween(Double precio1, Double precio2);

    List<Producto> findByNombreAndPrecioVentaGreaterThanEqual(String nombre, Double precio);


}
