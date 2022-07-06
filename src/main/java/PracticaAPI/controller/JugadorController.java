package PracticaAPI.controller;

import DTO.DTOJugador;
import PracticaAPI.domain.Jugador;
import PracticaAPI.exception.JugadorNotFoundException;
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
import PracticaAPI.service.JugadorService;
import java.util.Optional;
import org.springframework.stereotype.Controller;

/**
 * Controlador para jugadores
 * @author AlexPC
 * @version Curso 2021-2022
 */
@RestController
@Tag(name = "jugadores", description = "Lista de jugadores")
public class JugadorController {

    private final Logger logger = LoggerFactory.getLogger(JugadorController.class);

    @Autowired
    private JugadorService jugadorService;

    @Operation(summary = "Obtiene el listado de jugadores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Listado de jugadores",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = Jugador.class)))),
    })
    @GetMapping(value = "/jugadores", produces = "application/json")
    public ResponseEntity<Set<DTOJugador>> getJugadores() {
        logger.info("inicio getJugadores");
        Set<DTOJugador> products = null;
        products = jugadorService.findAll();
        logger.info("fin getProducts");
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un jugador determinado")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Existe el jugador", content = @Content(schema = @Schema(implementation = DTOJugador.class))),
            @ApiResponse(responseCode = "404", description = "El jugador no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @GetMapping(value = "/jugadores/{id}", produces = "application/json")
    public ResponseEntity<DTOJugador> getJugadores(@PathVariable long id) {
        DTOJugador product = jugadorService.findById(id)
                .orElseThrow(() -> new JugadorNotFoundException(id));

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Registra un nuevo jugador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Se registra el jugador", content = @Content(schema = @Schema(implementation = Jugador.class)))
    })
    @PostMapping(value = "/jugadores", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Jugador> addJugador(@RequestBody Jugador product) {
        Jugador addedProduct = jugadorService.addJugador(product);
        return new ResponseEntity<>(addedProduct, HttpStatus.CREATED);
    }

    @Operation(summary = "Modifica un jugador en el catálogo")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se modifica el jugador", content = @Content(schema = @Schema(implementation = Jugador.class))),
            @ApiResponse(responseCode = "404", description = "El jugador no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @PutMapping(value = "/jugadores/{id}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Jugador> modifyJugador(@PathVariable long id, @RequestBody Jugador newProduct) {
        Jugador product = jugadorService.modifyJugador(id, newProduct);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un jugador")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Se elimina el jugador", content = @Content(schema = @Schema(implementation = Response.class))),
            @ApiResponse(responseCode = "404", description = "El jugador no existe", content = @Content(schema = @Schema(implementation = Response.class)))
    })
    @DeleteMapping(value = "/jugadores/{id}", produces = "application/json")
    public ResponseEntity<Response> deleteJugador(@PathVariable long id) {
        
        Optional<DTOJugador> comprobar = jugadorService.findById(id);
        ResponseEntity<Response> respondo = null;
        if(comprobar.get().getIdEquipoPertenece() <= 0){
            jugadorService.deleteJugador(id);
            respondo = new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);
        }
        else{
            respondo = new ResponseEntity<>(Response.errorResonse(501, "Este jugador tiene un equipo asociado"), HttpStatus.BAD_REQUEST);
        }
        
        return respondo;
    }

    @ExceptionHandler(JugadorNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Response> handleException(JugadorNotFoundException pnfe) {
        Response response = Response.errorResonse(NOT_FOUND, pnfe.getMessage());
        logger.error(pnfe.getMessage(), pnfe);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
