/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.service;
import DTO.DTOEquipo;
import DTO.ResultadoEquipo;
import PracticaAPI.domain.Equipo;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexP
 */
@Service
public interface EquipoService {
    
    Set<DTOEquipo>findAll();
    Optional<DTOEquipo> findById(long id);
    Equipo addEquipo(Equipo equipo);
    Equipo modifyEquipo(long id, Equipo nuevoEquipo);
    void deleteEquipo(long id);
    Optional<ResultadoEquipo> findResultadoEquipo(long id);
}
