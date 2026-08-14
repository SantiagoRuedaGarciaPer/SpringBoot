package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productos")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> buscarProductos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscar(id));
    }

    @GetMapping("/Nombre")
    public ResponseEntity<List<ProductoResponse>> obtenerPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }

    @GetMapping("/PrecioVenta/mayor")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioVentaMayor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioVentaMayor(monto));
    }

    @GetMapping("/PrecioVenta/menor")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioVentaMenor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioVentaMenor(monto));
    }

    @GetMapping("/PrecioVenta/entre")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioVentaEntre(@RequestParam Double monto1, @RequestParam Double monto2){
        return ResponseEntity.ok(productoService.buscarPrecioVentaEntre(monto1, monto2));
    }

    @GetMapping("/PrecioCompra/mayor")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioCompraMayor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioCompraMayor(monto));
    }

    @GetMapping("/PrecioCompra/menor")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioCompraMenor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioCompraMenor(monto));
    }

    @GetMapping("/PrecioCompra/entre")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioCompraEntre(@RequestParam Double monto1, @RequestParam Double monto2){
        return ResponseEntity.ok(productoService.buscarPrecioCompraEntre(monto1, monto2));
    }



    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponse> actualizarProducto( @PathVariable Long id, @RequestBody ProductoRequest dto){
        return ResponseEntity.ok(productoService.actualizar(id, dto));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
