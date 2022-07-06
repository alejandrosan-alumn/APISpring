/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.service;

import PracticaAPI.domain.Jugador;
import PracticaAPI.domain.Equipo;
import PracticaAPI.exception.ProductNotFoundException;
import PracticaAPI.repository.ProductRepository;
import PracticaAPI.repository.VueloRepository;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author alexP
 */
@Service
public class VueloServiceImpl implements VueloService {
    
    @Autowired
    private VueloRepository vueloRepository;

    @Override
    public Set<Equipo> findAll() {
        return vueloRepository.findAll();
    }

    @Override
    public Set<Equipo> findByOrigen(String origen) {
        return vueloRepository.findByOrigen(origen);
    }
    
    @Override
    public Set<Equipo> findByDestino(String destino) {
        return vueloRepository.findByDestino(destino);
    }
    
    @Override
    public Set<Equipo> findByNumeroEscalas(int escalas) {
        return vueloRepository.findByNumeroEscalas(escalas);
    }

    @Override
    public Optional<Equipo> findById(long id) {
        return vueloRepository.findById(id);
    }

    @Override
    public Equipo addVuelo(Equipo vuelo) {
        return vueloRepository.save(vuelo);
    }

    @Override
    public Equipo modifyVuelo(long id, Equipo nuevoVuelo) {
        Equipo vuelo = vueloRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        nuevoVuelo.setId(vuelo.getId());
        return vueloRepository.save(nuevoVuelo);
    }

    @Override
    public void deleteVuelo(long id) {
        vueloRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        vueloRepository.deleteById(id);
    }
}
