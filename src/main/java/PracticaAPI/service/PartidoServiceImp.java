/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.service;

import DTO.DTOPartido;
import DTO.Resultado;
import PracticaAPI.domain.Partido;
import PracticaAPI.exception.PartidoNotFoundException;
import PracticaAPI.repository.PartidoRepository;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexP
 */
@Service
public class PartidoServiceImp implements PartidoService {

    
    @Autowired
    private PartidoRepository partidoRepository;
    
    @Override
    public Set<DTOPartido> findAll() {

        Set<Partido> listAux = partidoRepository.findAll();
        Set<DTOPartido> listDTO = new HashSet<DTOPartido>();
        for(Partido partido : listAux){
            
            DTOPartido jugadordto = new DTOPartido(partido);
            listDTO.add(jugadordto);
        }
        
        return listDTO;
    }

    @Override
    public Optional<DTOPartido> findById(long id) {

        Optional<Partido> listAux = partidoRepository.findById(id);
        DTOPartido partidodto = new DTOPartido(listAux.get());
        Optional<DTOPartido> optionPartido = Optional.of(partidodto);
        return optionPartido;
    }

    @Override
    public Partido addPartido(Partido partido) {
        
        return partidoRepository.save(partido);
    }

    @Override
    public Partido modifyPartido(long id, Partido nuevoPartido) {

        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new PartidoNotFoundException(id));
        nuevoPartido.setId(partido.getId());
        return partidoRepository.save(nuevoPartido);
    }

    @Override
    public void deletePartido(long id) {

        partidoRepository.findById(id)
            .orElseThrow(() -> new PartidoNotFoundException(id));
        partidoRepository.deleteById(id);
    }

    @Override
    public Set<Resultado> findResultado() {

        Set<Partido> listAux = partidoRepository.findAll();
        Set<Resultado> listDTO = new HashSet<Resultado>();
        for(Partido partido : listAux){
            
            Resultado jugadordto = new Resultado(partido);
            listDTO.add(jugadordto);
        }
        
        return listDTO;
    }
}
