package com.explicacionD1.projectD1.Controller;

import com.explicacionD1.projectD1.DTO.Request.ProductoRequest;
import com.explicacionD1.projectD1.DTO.Response.ProductoResponse;
import com.explicacionD1.projectD1.Service.ProductoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name="Producto", description = "Endpoints para distintas operaciones de la tabla productos")
@RestController
@RequestMapping("/api/producto")
@RequiredArgsConstructor
public class ProductoController {
    private final ProductoService productoService;

    @Operation(summary = "Ingrese datos de productos", description = "Requiere un RequestBody/JSON con los parametros\nnombre\ndescripcion\nprecioVenta\nprecioCompra")
    @ApiResponses(
            value={
                    @ApiResponse(responseCode = "201", description = "Producto creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos no validos / body mal estructurado"),
            }
    )
    @PostMapping
    public ResponseEntity<ProductoResponse> crearProducto(@RequestBody ProductoRequest dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.guardar(dto));
    }

    @Operation(summary = "Devuelve lista completa de los productos existentes")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
            }
    )
    @GetMapping
    public ResponseEntity<List<ProductoResponse>> buscarProductos() {
        return ResponseEntity.ok(productoService.obtenerTodos());
    }

    @Operation(summary = "Devuelve un producto")
    @Parameter(description = "Id del producto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto listado correctamente"),
                    @ApiResponse(responseCode = "500", description = "El id del producto no se encuentra"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")

            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponse> obtenerProductoPorId(@PathVariable Long id) {
        return ResponseEntity.ok(productoService.buscar(id));
    }

    @Operation(summary = "Devuelve una lista de productos", description = "Basado en el nombre, devuelve una lista de todos los productos con ese nombre")
    @Parameter(description = "Nombre del producto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listado correctamente"),
                    @ApiResponse(responseCode = "400", description = "Request Param configurado erroneamente"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    @GetMapping("/Nombre")
    public ResponseEntity<List<ProductoResponse>> obtenerPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(productoService.buscarPorNombre(nombre));
    }


    @Operation(summary = "Devuelve una lista de productos", description = "Compara el precio de venta con el monto y devuelve los productos que cumplen con la condicion")
    @Parameter(description = "Monto")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto / body mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    @GetMapping("/PrecioVenta/mayor")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioVentaMayor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioVentaMayor(monto));
    }

    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto / body mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    @Operation(summary = "Devuelve una lista de productos", description = "Compara el precio de venta con el monto y devuelve los productos que cumplen con la condicion")
    @Parameter(description = "Monto")
    @GetMapping("/PrecioVenta/menor")
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioVentaMenor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioVentaMenor(monto));
    }

    @Operation(summary = "Devuelve una lista de productos", description = "devuelve los productos que se encuentran en el rango")
    @Parameter(description = "Monto1, Monto2")
    @GetMapping("/PrecioVenta/entre")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto / body mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioVentaEntre(@RequestParam Double monto1, @RequestParam Double monto2){
        return ResponseEntity.ok(productoService.buscarPrecioVentaEntre(monto1, monto2));
    }

    @Operation(summary = "Devuelve una lista de productos", description = "Compara el precio de compra con el monto y devuelve los productos que cumplen con la condicion")
    @Parameter(description = "Monto")
    @GetMapping("/PrecioCompra/mayor")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto / Request Param mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioCompraMayor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioCompraMayor(monto));
    }

    @Operation(summary = "Devuelve una lista de productos", description = "Compara el precio de compra con el monto y devuelve los productos que cumplen con la condicion")
    @Parameter(description = "Monto")
    @GetMapping("/PrecioCompra/menor")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto / Request Param mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioCompraMenor(@RequestParam Double monto) {
        return ResponseEntity.ok(productoService.buscarPrecioCompraMenor(monto));
    }

    @Operation(summary = "Devuelve una lista de productos", description = "devuelve los productos que se encuentran en el rango")
    @Parameter(description = "Monto")
    @GetMapping("/PrecioCompra/entre")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Productos listados correctamente"),
                    @ApiResponse(responseCode = "400", description = "Tipo de dato incorrecto / Request Param mal configurado"),
                    @ApiResponse(responseCode = "404", description = "No se ingresaron los parametros requeridos")
            }
    )
    public ResponseEntity<List<ProductoResponse>> obtenerPrecioCompraEntre(@RequestParam Double monto1, @RequestParam Double monto2){
        return ResponseEntity.ok(productoService.buscarPrecioCompraEntre(monto1, monto2));
    }


    @Operation(summary = "Actualiza un producto")
    @Parameter(name ="id", description = "id del producto")
    @PutMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Producto actualizado correctamente"),
                    @ApiResponse(responseCode = "500", description = "Datos correctos, Valor de texto muy largo (50 maximo) / Id inexistente"),
                    @ApiResponse(responseCode = "400", description = "Datos erroneos"),
                    @ApiResponse(responseCode = "404", description = "No se ingreso el id")

            }
    )
    public ResponseEntity<ProductoResponse> actualizarProducto( @PathVariable Long id, @RequestBody ProductoRequest dto){
        return ResponseEntity.ok(productoService.actualizar(id, dto));
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
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        productoService.eliminar(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
