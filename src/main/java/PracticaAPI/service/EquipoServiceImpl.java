/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.service;

import DTO.DTOEquipo;
import DTO.ResultadoEquipo;
import PracticaAPI.domain.Equipo;
import PracticaAPI.exception.EquipoNotFoundException;
import PracticaAPI.repository.EquipoRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexPC
 */
@Service
public class EquipoServiceImpl implements EquipoService {
    
    @Autowired
    private EquipoRepository equipoRepository;
    
    @Override
    public Set<DTOEquipo> findAll() {
        
        Set<Equipo> listAux = equipoRepository.findAll();
        Set<DTOEquipo> listDTO = new HashSet<DTOEquipo>();
        for(Equipo equipo : listAux){
            
            DTOEquipo equipodto = new DTOEquipo(equipo);
            listDTO.add(equipodto);
        }
        
        return listDTO;
    }

    @Override
    public Optional<DTOEquipo> findById(long id) {
        
        Optional<Equipo> listAux = equipoRepository.findById(id);
        DTOEquipo equipodto = new DTOEquipo(listAux.get());
        Optional<DTOEquipo> optionEquipo = Optional.of(equipodto);
        return optionEquipo;
    }

    @Override
    public Equipo addEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo modifyEquipo(long id, Equipo newProduct) {
        Equipo product = equipoRepository.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));
        newProduct.setId(product.getId());
        return equipoRepository.save(newProduct);
    }

    @Override
    public void deleteEquipo(long id) {
        equipoRepository.findById(id)
                .orElseThrow(() -> new EquipoNotFoundException(id));
        equipoRepository.deleteById(id);
    }

    @Override
    public Optional<ResultadoEquipo> findResultadoEquipo(long id) {

        Optional<Equipo> listAux = equipoRepository.findById(id);
        ResultadoEquipo equipo = new ResultadoEquipo(listAux.get());
        Optional<ResultadoEquipo> optionEquipo = Optional.of(equipo);
        return optionEquipo;
    }
}
