package PracticaAPI.service;

import PracticaAPI.domain.Jugador;

import java.util.Optional;
import java.util.Set;

/**
 * Service de productos
 * @author Santiago Faci
 * @version Curso 2020-2021
 */

public interface ProductService {

    Set<Jugador> findAll();
    Set<Jugador> findByCategory(String category);
    Optional<Jugador> findById(long id);
    Jugador addProduct(Jugador product);
    Jugador modifyProduct(long id, Jugador newProduct);
    void deleteProduct(long id);
}
