/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import PracticaAPI.domain.Equipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alexP
 */
public class ResultadoEquipo {
    
    String nombreEquipo;
    List<String> equipoRival, arbitro;
    List<Integer> goles, golesRival;

    public ResultadoEquipo(Equipo equipo) {
    
        nombreEquipo = equipo.getNombreEquipo();
        ExtraerDatosPartidos(equipo);
    }
    
    private void ExtraerDatosPartidos(Equipo equipo){
        
        equipoRival = new ArrayList<>();
        arbitro = new ArrayList<>();
        goles = new ArrayList<>();
        golesRival = new ArrayList<>();
        
        for(int i = 0; i < equipo.getPartidosJugadosLocal().size(); i++){
            
            equipoRival.add(equipo.getPartidosJugadosLocal().get(i).getEquipoVisitante().getNombreEquipo());
            arbitro.add(equipo.getPartidosJugadosLocal().get(i).getArbitro());
            goles.add(equipo.getPartidosJugadosLocal().get(i).getGolesLocal());
            golesRival.add(equipo.getPartidosJugadosLocal().get(i).getGolesVisitante());
        }
        for(int i = 0; i < equipo.getPartidosJugadosVisitante().size(); i++){
            
            equipoRival.add(equipo.getPartidosJugadosVisitante().get(i).getEquipoLocal().getNombreEquipo());
            arbitro.add(equipo.getPartidosJugadosVisitante().get(i).getArbitro());
            goles.add(equipo.getPartidosJugadosVisitante().get(i).getGolesVisitante());
            golesRival.add(equipo.getPartidosJugadosVisitante().get(i).getGolesLocal());
        }
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public List<String> getEquipoRival() {
        return equipoRival;
    }

    public void setEquipoRival(List<String> equipoRival) {
        this.equipoRival = equipoRival;
    }

    public List<String> getArbitro() {
        return arbitro;
    }

    public void setArbitro(List<String> arbitro) {
        this.arbitro = arbitro;
    }

    public List<Integer> getGoles() {
        return goles;
    }

    public void setGoles(List<Integer> goles) {
        this.goles = goles;
    }

    public List<Integer> getGolesRival() {
        return golesRival;
    }

    public void setGolesRival(List<Integer> golesRival) {
        this.golesRival = golesRival;
    }
    
    
}
