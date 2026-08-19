package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.DetalleVentaRequest;
import com.explicacionD1.projectD1.DTO.Response.DetalleVentaResponse;
import com.explicacionD1.projectD1.Service.DetalleVentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Detalle de Venta", description = "Operaciones relacionadas a los detalles de una venta")
@Validated
@RestController
@RequestMapping("/api/detalleVenta")
@RequiredArgsConstructor
public class DetalleVentaCrontroller {
    private final DetalleVentaService detalleVentaService;

    @Operation(summary = "Crea un nuevo detalle de venta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Detalle de venta creado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Body configurado incorrectamente")
            }
    )
    @PostMapping
    public ResponseEntity<DetalleVentaResponse> crear(@Valid @RequestBody DetalleVentaRequest dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(detalleVentaService.crear(dto));
    }

    @Operation(summary = "Lista todos los Detalles de venta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Detalles de ventas listados correctamente")
            }
    )
    @GetMapping
    public ResponseEntity<List<DetalleVentaResponse>> buscar(){
        return ResponseEntity.ok(detalleVentaService.listarTodos());
    }

    @Operation(summary = "Obtiene un Detalle de venta", description = "Busca el detalle de venta por el id")
    @Parameter(name = "id", description = "Id a buscar")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Detalle venta es existente y fue retornado"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto"),
                    @ApiResponse(responseCode = "404", description = "No se entrego el id del detalle de venta"),
                    @ApiResponse(responseCode = "500", description = "No se encontro el detalle de venta")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> obtenerPorId(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorId(id));
    }

    @Operation(summary = "Lista de detalles de venta", description = "Obtiene todos los detalle de venta que contienen un producto")
    @Parameter(name = "producto_id", description = "Id del producto a buscar")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Detalles de ventas retornados"),
                    @ApiResponse(responseCode = "400", description = "tipo de dato incorrecto"),
                    @ApiResponse(responseCode = "404", description = "No se dio el ID del producto")
            }
    )
    @GetMapping("/producto/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> buscarPorIdProducto(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdProducto(id));
    }

    @Operation(summary = "Lista de detalles de venta", description = "Obtiene todos los detalle de venta de una venta")
    @Parameter(name = "venta_id", description = "Id de la venta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Detalles de ventas retornados"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "500", description = "El id no existe"),
                    @ApiResponse(responseCode = "404", description = "No se proveyo el id")
            }
    )
    @GetMapping("/venta/{id}")
    public ResponseEntity<List<DetalleVentaResponse>> buscarPorIdVenta(@PathVariable Long id){
        return ResponseEntity.ok(detalleVentaService.buscarPorIdVenta(id));
    }

    @Operation(summary = "Lista de detalles venta con cierta cantidad", description = "Retorna todos los detalles de ventas que son menores a la cantidad provista")
    @Parameter(name = "cantidad", description = "Cantidad a comparar")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Detalles de ventas retornados"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato erroneo"),
                    @ApiResponse(responseCode = "404", description = "No se proveyo la cantidad"),
            }
    )
    @GetMapping("/cantidad")
    public ResponseEntity<List<DetalleVentaResponse>> listarPorCantidadMenorQue(@RequestParam Long cantidad){
        return ResponseEntity.ok((detalleVentaService.buscarCatantidadMenorQue(cantidad)));
    }

    @Operation(summary = "Actualiza un detalle de venta")
    @Parameter(name = "id", description = "id del Detalle venta")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Detalle de venta actualizado correctamente"),
                    @ApiResponse(responseCode = "500", description = "Datos correctos, Valor de texto muy largo (50 maximo) / Id inexistente"),
                    @ApiResponse(responseCode = "400", description = "Datos erroneos"),
                    @ApiResponse(responseCode = "404", description = "No se ingreso el id")

            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<DetalleVentaResponse> actualizar( @PathVariable Long id, @Valid @RequestBody DetalleVentaRequest dto){
        return ResponseEntity.ok(detalleVentaService.actualizar(id, dto));
    }

    @Operation(summary = "Elimina un producto")
    @Parameter(description = "id")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Producto eliminado correctamente"),
                    @ApiResponse(responseCode = "500", description = "El producto no existe"),
                    @ApiResponse(responseCode = "404", description = "No se ingreso el id")

            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        detalleVentaService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
