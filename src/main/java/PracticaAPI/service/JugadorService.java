package PracticaAPI.service;

import DTO.DTOJugador;
import PracticaAPI.domain.Jugador;

import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 * Service de jugadores
 * @author AlexPC
 * @version Curso 2021-2022
 */
//@Service
public interface JugadorService {

    Set<DTOJugador> findAll();
    //Set<DTOJugador> findByCategory(String category);
    Optional<DTOJugador> findById(long id);
    Jugador addJugador(Jugador jugador);
    Jugador modifyJugador(long id, Jugador nuevoJugador);
    void deleteJugador(long id);
}
