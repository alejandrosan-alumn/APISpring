/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.repository;

import PracticaAPI.domain.Equipo;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexP
 */
@Repository
public interface VueloRepository extends CrudRepository<Equipo, Long> {
    
    Set<Equipo> findAll();
    //Vuelo findByName(String name);
    Set<Equipo> findByOrigen(String origen);
    Set<Equipo> findByDestino(String destino);
    Set<Equipo> findByNumeroEscalas(int escalas);
    //Set<Vuelo> findByNameAndPrice(String name, float price);
}