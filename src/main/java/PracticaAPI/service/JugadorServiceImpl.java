package PracticaAPI.service;

import DTO.DTOJugador;
import PracticaAPI.domain.Jugador;
import PracticaAPI.exception.JugadorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import PracticaAPI.repository.JugadorRepository;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Implementación del Service de jugadores
 * @author AlexPC
 * @version Curso 2021-2022
 */
@Service
public class JugadorServiceImpl implements JugadorService {

    @Autowired
    private JugadorRepository jugadorRepository;

    @Override
    public Set<DTOJugador> findAll() {
        
        Set<Jugador> listAux = jugadorRepository.findAll();
        Set<DTOJugador> listDTO = new HashSet<DTOJugador>();
        //ArrayList<DTOJugador> listaDTO = new ArrayList<>();
        for(Jugador jugador : listAux){
            
            DTOJugador jugadordto = new DTOJugador(jugador);
            listDTO.add(jugadordto);
        }
        
        return listDTO;
    }

    /*@Override
    public Set<DTOJugador> findByCategory(String category) {
        return jugadorRepository.findByCategory(category);
    }*/

    @Override
    public Optional<DTOJugador> findById(long id) {
        
        Optional<Jugador> listAux = jugadorRepository.findById(id);
        DTOJugador jugadordto = new DTOJugador(listAux.get());
        Optional<DTOJugador> optionJugador = Optional.of(jugadordto);
        return optionJugador;
    }

    @Override
    public Jugador addJugador(Jugador product) {
        return jugadorRepository.save(product);
    }

    @Override
    public Jugador modifyJugador(long id, Jugador newProduct) {
        Jugador product = jugadorRepository.findById(id)
                .orElseThrow(() -> new JugadorNotFoundException(id));
        newProduct.setId(product.getId());
        return jugadorRepository.save(newProduct);
    }

    @Override
    public void deleteJugador(long id) {
        jugadorRepository.findById(id)
                .orElseThrow(() -> new JugadorNotFoundException(id));
        jugadorRepository.deleteById(id);
    }
}
