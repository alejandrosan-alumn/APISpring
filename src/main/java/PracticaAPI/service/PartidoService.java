/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.service;

import DTO.DTOPartido;
import DTO.Resultado;
import PracticaAPI.domain.Partido;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author alexP
 */
public interface PartidoService {
    
    Set<DTOPartido> findAll();
    //Set<DTOJugador> findByCategory(String category);
    Optional<DTOPartido> findById(long id);
    Partido addPartido(Partido partido);
    Partido modifyPartido(long id, Partido nuevoPartido);
    void deletePartido(long id);
    Set<Resultado> findResultado();
}
