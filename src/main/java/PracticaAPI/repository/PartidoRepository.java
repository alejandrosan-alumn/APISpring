/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.repository;

import PracticaAPI.domain.Partido;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author alexP
 */
@Repository
public interface PartidoRepository extends CrudRepository<Partido, Long> {
    
    Set<Partido> findAll();
}
