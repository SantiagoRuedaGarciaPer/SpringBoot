package com.explicacionD1.projectD1.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "detalle_venta")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DetalleVenta {

    private Long id;
    @ManyToOne
    @JoinColumn(name = "venta_fk", nullable = false)
    private Venta venta;
    @ManyToOne
    @JoinColumn(name = "producto_fk", nullable = false)
    private Producto producto;

    @Column(nullable = false)
    private Long cantidad;

    @Column(nullable = false)
    private Double subtotal;

}
