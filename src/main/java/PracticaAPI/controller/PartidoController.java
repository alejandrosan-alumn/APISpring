/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.controller;

import DTO.DTOPartido;
import DTO.Resultado;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RestController;
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
import PracticaAPI.domain.Partido;
import PracticaAPI.exception.PartidoNotFoundException;
import PracticaAPI.service.PartidoService;
import java.util.Optional;
import org.springframework.stereotype.Controller;
/**
 *
 * @author alexP
 */
@RestController
@Tag(name = "partidos", description = "Lista de partidos")
public class PartidoController {
    
    private final Logger logger = LoggerFactory.getLogger(PartidoController.class);

    @Autowired
    private PartidoService partidoService;

    @Operation(summary = "Obtiene el listado de partidos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de partidos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Partido.class)))),
    })
    @GetMapping(value = "/partidos", produces = "application/json")
    public ResponseEntity<Set<DTOPartido>> getPartidos() {
        logger.info("inicio getPartidos");
        Set<DTOPartido> products = null;
        products = partidoService.findAll();
        logger.info("fin getPartidos");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un partido determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el partido", content = @Content(schema = @Schema(implementation = DTOPartido.class))),
            @ApiResponse(responseCode = "404", description = "El partido no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/partidos/{id}", produces = "application/json")
    public ResponseEntity<DTOPartido> getPartidos(@PathVariable long id) {
        DTOPartido product = partidoService.findById(id)
                .orElseThrow(() -> new PartidoNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el partido", content = @Content(schema = @Schema(implementation = Partido.class)))
    })
    @PostMapping(value = "/partidos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Partido> addPartido(@RequestBody Partido product) {
        Partido addedProduct = partidoService.addPartido(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un partido en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el partido", content = @Content(schema = @Schema(implementation = Partido.class))),
            @ApiResponse(responseCode = "404", description = "El partido no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/partidos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Partido> modifyPartido(@PathVariable long id, @RequestBody Partido newProduct) {
        Partido product = partidoService.modifyPartido(id, newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un partido")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el partido", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El partido no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/partidos/{id}", produces = "application/json")
    public ResponseEntity<Response> deletePartido(@PathVariable long id) {
        //partidoService.deletePartido(id);
        Optional<DTOPartido> comprobar = partidoService.findById(id);
        ResponseEntity<Response> respondo = null;
        if(comprobar.get().getIdEquipoLocal() <= 0&&comprobar.get().getIdEquipoVisitante() <= 0){
            partidoService.deletePartido(id);
            respondo = new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
        }
        else{
            respondo = new ResponseEntity<>(Response.errorResonse(501, "Este partido tiene equipos asociados"), HttpStatus.BAD_REQUEST);
        }
        return respondo;
    }

    @ExceptionHandler(PartidoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(PartidoNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @Operation(summary = "Obtiene el listado de resultados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de resultados",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Partido.class)))),
    })
    @GetMapping(value = "/resultados", produces = "application/json")
    public ResponseEntity<Set<Resultado>> getResultado() {
        logger.info("inicio getResultados");
        Set<Resultado> products = null;
        products = partidoService.findResultado();
        logger.info("fin getPartidos");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
