/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.service;

import PracticaAPI.domain.Equipo;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexP
 */
@Service
public interface VueloService {
    
    Set<Equipo> findAll();
    Set<Equipo> findByOrigen(String origen);
    Set<Equipo> findByDestino(String destino);
    Set<Equipo> findByNumeroEscalas(int escalas);
    Optional<Equipo> findById(long id);
    Equipo addVuelo(Equipo vuelo);
    Equipo modifyVuelo(long id, Equipo nuevoVuelo);
    void deleteVuelo(long id);
}
