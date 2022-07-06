package PracticaAPI.controller;

import PracticaAPI.domain.Jugador;
import PracticaAPI.exception.ProductNotFoundException;
import PracticaAPI.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

import static PracticaAPI.controller.Response.NOT_FOUND;

/**
 * Controlador para productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@RestController
@Tag(name = "Products", description = "Catálogo de productos")
public class ProductController {

    private final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Operation(summary = "Obtiene el listado de productos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de productos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jugador.class)))),
    })
    @GetMapping(value = "/products", produces = "application/json")
    public ResponseEntity<Set<Jugador>> getProducts(@RequestParam(value = "category", defaultValue = "") String category) {
        logger.info("inicio getProducts");
        Set<Jugador> products = null;
        if (category.equals(""))
            products = productService.findAll();
        else
            products = productService.findByCategory(category);

        logger.info("fin getProducts");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el producto", content = @Content(schema = @Schema(implementation = Jugador.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Jugador> getProduct(@PathVariable long id) {
        Jugador product = productService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el producto", content = @Content(schema = @Schema(implementation = Jugador.class)))
    })
    @PostMapping(value = "/products", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Jugador> addProduct(@RequestBody Jugador product) {
        Jugador addedProduct = productService.addProduct(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un producto en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el producto", content = @Content(schema = @Schema(implementation = Jugador.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/products/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Jugador> modifyProduct(@PathVariable long id, @RequestBody Jugador newProduct) {
        Jugador product = productService.modifyProduct(id, newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un producto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el producto", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El producto no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/products/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(ProductNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
