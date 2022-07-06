/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.controller;

import DTO.DTOEquipo;
import DTO.ResultadoEquipo;
import static PracticaAPI.controller.Response.NOT_FOUND;
import PracticaAPI.domain.Equipo;
import PracticaAPI.exception.EquipoNotFoundException;
import PracticaAPI.exception.ResultadoNotFoundException;
import PracticaAPI.service.EquipoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alexP
 */
@RestController
@Tag(name = "equipos", description = "Lista de equipos")
public class EquipoController {
    
    private final Logger logger = LoggerFactory.getLogger(EquipoController.class);

    @Autowired
    private EquipoService equipoService;

    @Operation(summary = "Obtiene el listado de equipos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de equipos",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Equipo.class)))),
    })
    @GetMapping(value = "/equipos", produces = "application/json")
    public ResponseEntity<Set<DTOEquipo>> getEquipos() {
        logger.info("inicio getEquipos");
        Set<DTOEquipo> products = null;
        products = equipoService.findAll();
        logger.info("fin getEquipos");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un equipo determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el equipo", content = @Content(schema = @Schema(implementation = DTOEquipo.class))),
            @ApiResponse(responseCode = "404", description = "El jugador no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/equipos/{id}", produces = "application/json")
    public ResponseEntity<DTOEquipo> getEquipos(@PathVariable long id) {
        DTOEquipo product = equipoService.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el equipo", content = @Content(schema = @Schema(implementation = Equipo.class)))
    })
    @PostMapping(value = "/equipos", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Equipo> addEquipo(@RequestBody Equipo product) {
        Equipo addedProduct = equipoService.addEquipo(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un equipo en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el equipo", content = @Content(schema = @Schema(implementation = Equipo.class))),
            @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/equipos/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Equipo> modifyEquipo(@PathVariable long id, @RequestBody Equipo newProduct) {
        Equipo product = equipoService.modifyEquipo(id, newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un equipo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el equipo", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El equipo no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/equipos/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteEquipo(@PathVariable long id) {
        
        Optional<DTOEquipo> comprobar = equipoService.findById(id);
        equipoService.deleteEquipo(id);
        ResponseEntity<Response> respondo = null;
        if(comprobar.get().getIdJugadores().isEmpty()){
            equipoService.deleteEquipo(id);
            respondo = new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
        }
        else{
            respondo = new ResponseEntity<>(Response.errorResonse(501, "Este jugador tiene relaciones y no se pueden borrar"), HttpStatus.BAD_REQUEST);
        }
        
        return respondo;
    }

    @ExceptionHandler(EquipoNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(EquipoNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
    
    @Operation(summary = "Obtiene el resultado de un equipo determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe un resultado en el equipo", content = @Content(schema = @Schema(implementation = ResultadoEquipo.class))),
            @ApiResponse(responseCode = "404", description = "El resultado no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/equipos/{id}/resultados", produces = "application/json")
    public ResponseEntity<ResultadoEquipo> getResultadosEquipo(@PathVariable long id) {
        ResultadoEquipo product = equipoService.findResultadoEquipo(id)
                .orElseThrow(() -> new ResultadoNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}
