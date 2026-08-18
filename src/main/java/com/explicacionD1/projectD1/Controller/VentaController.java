package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.VentaRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;
import com.explicacionD1.projectD1.DTO.Response.VentaResponse;
import com.explicacionD1.projectD1.Model.Venta;
import com.explicacionD1.projectD1.Service.VentaService;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;

    @PostMapping
    public ResponseEntity<VentaResponse> crear(@RequestBody VentaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardar(dto));
    }

    @GetMapping
    public ResponseEntity<List<VentaResponse>> buscar() {
        return ResponseEntity.ok(ventaService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscar(id));
    }

    @GetMapping("/total/mayor")
    public ResponseEntity<List<VentaResponse>> obtenerVentaMayor(@RequestParam Double monto){
        return ResponseEntity.ok(ventaService.buscarTotalMayorQue(monto));
    }

    @GetMapping("/total/menor")
    public ResponseEntity<List<VentaResponse>> obtenerVentaMenor(@RequestParam Double monto){
        return ResponseEntity.ok(ventaService.buscarTotalMenorQue(monto));
    }

    @GetMapping("/fecha/entre")
    public ResponseEntity<List<VentaResponse>> obtenerFechaEntre(@RequestParam Date fecha1, @RequestParam Date fecha2){
        return ResponseEntity.ok(ventaService.buscarFechaEntre(fecha1, fecha2));
    }

    @GetMapping("/fecha/anio/{anio}/mes/{mes}")
    public ResponseEntity<List<VentaResponse>> obtenerFechaMesAnio(@PathVariable Integer mes, @PathVariable Integer anio){
        return ResponseEntity.ok(ventaService.buscarFechaMesAnio(mes, anio));
    }

    @GetMapping("/fecha")
    public ResponseEntity<List<VentaResponse>> obtenerFecha(@RequestParam Date fecha){
        return ResponseEntity.ok(ventaService.buscarFecha(fecha));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> actualizar(@PathVariable Long id, @RequestBody VentaRequest dto) {
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }

    @DeleteMapping("")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
