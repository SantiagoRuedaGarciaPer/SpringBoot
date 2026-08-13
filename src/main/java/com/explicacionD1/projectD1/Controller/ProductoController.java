package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
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
    public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<ProductoResponse>> buscarProductos(){
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable Long id){
        return ResponseEntity.ok(productoService.buscar(id));
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
