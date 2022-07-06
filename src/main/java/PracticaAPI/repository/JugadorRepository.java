package PracticaAPI.repository;

import DTO.DTOJugador;
import PracticaAPI.domain.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositorio de jugadores
 * @author AlexPC
 * @version Curso 2021-2022
 */
@Repository
public interface JugadorRepository extends CrudRepository<Jugador, Long> {

    Set<Jugador> findAll();
}
