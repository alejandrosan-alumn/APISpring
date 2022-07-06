package PracticaAPI.repository;

import PracticaAPI.domain.Jugador;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Repositorio de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */
@Repository
public interface ProductRepository extends CrudRepository<Jugador, Long> {

    Set<Jugador> findAll();
    Jugador findByName(String name);
    Set<Jugador> findByCategory(String category);
    Set<Jugador> findByNameAndPrice(String name, float price);
}
