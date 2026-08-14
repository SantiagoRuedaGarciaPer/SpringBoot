package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.DetalleVentaRequest;
import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.Service.DetalleVentaService;
import com.explicacionD1.projectD1.Service.ProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalleVenta")
@RequiredArgsConstructor
public class DetalleVentaCrontroller {
    private final DetalleVentaService detalleVentaService;

    @PostMapping
    public ResponseEntity<DetalleVentaResponse> crear(@RequestBody DetalleVentaRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaService.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<DetalleVentaResponse>> buscar(){
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorId(id));
    }

    @GetMapping("/producto/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> buscarPorIdProducto(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdProducto(id));
    }

    @GetMapping("/venta/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> buscarPorIdVenta(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdVenta(id));
    }

    @GetMapping("/venta")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorCantidadMenorQue(@RequestParam Long cantidad){
        return ResponseEntity.ok((detalleVentaService.buscarCatantidadMenorQue(cantidad)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> actualizar( @PathVariable Long id, @RequestBody DetalleVentaRequest dto){
        return ResponseEntity.ok(detalleVentaService.actualizar(id, dto));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        detalleVentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
