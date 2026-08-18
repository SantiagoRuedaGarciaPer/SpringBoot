package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.VentaRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;
import com.explicacionD1.projectD1.DTO.Response.VentaResponse;
import com.explicacionD1.projectD1.Model.Venta;
import com.explicacionD1.projectD1.Service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@Tag(name = "Ventas", description = "Operaciones relacionadas a las ventas")
@RestController
@RequestMapping("/api/venta")
@RequiredArgsConstructor
public class VentaController {
    private final VentaService ventaService;

    @Operation(summary = "Nueva venta", description = "Crea una nueva venta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Venta creada exitosamente"),
                    @ApiResponse(responseCode = "500", description = "Body incorrecto")
            }
    )
    @PostMapping
    public ResponseEntity<VentaResponse> crear(@RequestBody VentaRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.guardar(dto));
    }

    @Operation(summary = "Retorna todas las ventas")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Ventas retornadas exitosamente")
            }
    )
    @GetMapping
    public ResponseEntity<List<VentaResponse>> buscar() {
        return ResponseEntity.ok(ventaService.obtenerTodos());
    }

    @Operation(summary = "Busca una venta", description = "Busca una venta utilizando el id")
    @Parameter(name = "id", description = "ID de la venta a buscar")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Venta encontrada y retornada"),
                    @ApiResponse(responseCode = "404", description = "Datos erroneos"),
                    @ApiResponse(responseCode = "404", description = "No se proveyo el id")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<VentaResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.buscar(id));
    }

    @Operation(summary = "busca ventas con un total mayor", description = "Retorna cualquier Venta que contenga un total mayor a un monto provisto")
    @Parameter(name = "monto", description = "Monto a comparar")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "Ventas retornadas"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se entrego el monto")
            }
    )
    @GetMapping("/total/mayor")
    public ResponseEntity<List<VentaResponse>> obtenerVentaMayor(@RequestParam Double monto){
        return ResponseEntity.ok(ventaService.buscarTotalMayorQue(monto));
    }

    @Operation(summary = "busca ventas con un total menor", description = "Retorna cualquier Venta que contenga un total menor a un monto provisto")
    @Parameter(name = "monto", description = "Monto a comparar")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "Ventas retornadas"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se entrego el monto")
            }
    )
    @GetMapping("/total/menor")
    public ResponseEntity<List<VentaResponse>> obtenerVentaMenor(@RequestParam Double monto){
        return ResponseEntity.ok(ventaService.buscarTotalMenorQue(monto));
    }

    @Operation(summary = "busca ventas con un total entre", description = "Retorna cualquier Venta que contenga un total entre dos montos provistos")
    @Parameter(name = "monto1", description = "Monto minimo")
    @Parameter(name = "monto2", description = "Monto maximo")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "Ventas retornadas"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se entrego alguno de los montos")
            }
    )
    @GetMapping("/fecha/entre")
    public ResponseEntity<List<VentaResponse>> obtenerFechaEntre(@RequestParam Date fecha1, @RequestParam Date fecha2){
        return ResponseEntity.ok(ventaService.buscarFechaEntre(fecha1, fecha2));
    }

    @Operation(summary = "busca ventas de un mes", description = "Retorna cualquier Venta realizada en un mes")
    @Parameter(name = "mes", description = "Mes de la venta")
    @Parameter(name = "anio", description = "Anio de la venta")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "Ventas retornadas"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se entrego alguno de los datos requeridos")
            }
    )
    @GetMapping("/fecha/anio/{anio}/mes/{mes}")
    public ResponseEntity<List<VentaResponse>> obtenerFechaMesAnio(@PathVariable Integer mes, @PathVariable Integer anio){
        return ResponseEntity.ok(ventaService.buscarFechaMesAnio(mes, anio));
    }

    @Operation(summary = "busca ventas de una fecha", description = "Retorna cualquier Venta realizada en una fecha especifica")
    @Parameter(name = "mes", description = "Mes de la venta")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "Ventas retornadas"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se entrego la fecha")
            }
    )
    @GetMapping("/fecha")
    public ResponseEntity<List<VentaResponse>> obtenerFecha(@RequestParam Date fecha){
        return ResponseEntity.ok(ventaService.buscarFecha(fecha));
    }

    @Operation(summary = "Actualiza una venta")
    @Parameter(name = "id", description = "Id a actualizar")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "200", description = "Venta actualizada"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "500", description = "Body mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se entrego alguno de los datos requeridos")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<VentaResponse> actualizar(@PathVariable Long id, @RequestBody VentaRequest dto) {
        return ResponseEntity.ok(ventaService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina una venta")
    @Parameter(name = "id", description = "Id a eliminar")
    @ApiResponses(
            value ={
                    @ApiResponse(responseCode = "204", description = "Venta eliminada"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se entrego alguno de los datos requeridos")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        ventaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
