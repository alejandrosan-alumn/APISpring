/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import PracticaAPI.domain.Equipo;
import PracticaAPI.service.VueloService;

/**
 *
 * @author alexP
 */
@RestController
@Tag(name = "vuelos", description = "Lista de vuelos")
public class VieloController {
    
    private final Logger logger = LoggerFactory.getLogger(VieloController.class);

    @Autowired
    private VueloService vueloService;

    @Operation(summary = "Obtiene el listado de vuelos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de vuelos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Equipo.class)))),
    })
    @GetMapping(value = "/vuelos", produces = "application/json")
    public ResponseEntity<Set<Equipo>> getVuelos(@RequestParam(value = "origen", defaultValue = "") String origen,@RequestParam(value = "destino", defaultValue = "") String destino) {
        logger.info("inicio getVuelos");
        Set<Equipo> vuelos = null;
        if (origen.equals("") && destino.equals(""))
            vuelos = vueloService.findAll();
        else if(!origen.isBlank())
            vuelos = vueloService.findByOrigen(origen);
        else
            vuelos = vueloService.findByDestino(destino);

        logger.info("fin getVuelos");
        return new ResponseEntity<>(vuelos, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un producto determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el vuelo", content = @Content(schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "404", description = "El vuelo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/vuelos/{id}", produces = "application/json")
    public ResponseEntity<Equipo> getVuelos(@PathVariable long id) {
        Equipo vuelo = vueloService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return new ResponseEntity<>(vuelo, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el vuelo", content = @Content(schema = @Schema(implementation = Jugador.class)))
    })
    @PostMapping(value = "/vuelos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Equipo> addVuelo(@RequestBody Equipo vuelo) {
        Equipo addedVuelo = vueloService.addVuelo(vuelo);
        return new ResponseEntity<>(addedVuelo, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un vuelo en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el vuelo", content = @Content(schema = @Schema(implementation = Jugador.class))),
            @ApiResponse(responseCode = "404", description = "El vuelo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/vuelos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Equipo> modifyVuelo(@PathVariable long id, @RequestBody Equipo nuevoVuelo) {
        Equipo vuelo = vueloService.modifyVuelo(id, nuevoVuelo);
        return new ResponseEntity<>(vuelo, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un vuelo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el vuelo", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El vuelo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/vuelos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteVuelo(@PathVariable long id) {
        vueloService.deleteVuelo(id);
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
