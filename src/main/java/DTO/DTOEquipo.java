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
public class DTOEquipo {
    
    private String nombreEquipo, fechaFundacion, estadio, entrenador;
    private long idEquipoDto, presupuesto;
    private List<Long> idJugadores;
    private List<String> nombreJugadores;

    public DTOEquipo(Equipo dtoEquipo) {
    
        idEquipoDto = dtoEquipo.getId();
        nombreEquipo = dtoEquipo.getNombreEquipo();
        fechaFundacion = dtoEquipo.getFechaFundacion();
        estadio = dtoEquipo.getEstadio();
        entrenador = dtoEquipo.getEntrenador();
        presupuesto = dtoEquipo.getPresupuesto();
        ExtraerJugadores(dtoEquipo);
    }
    
    private void ExtraerJugadores(Equipo dtoEquipo){
        
        idJugadores = new ArrayList<>();
        nombreJugadores = new ArrayList<>();
        for(int i = 0; i < dtoEquipo.getJugadoresEquipo().size(); i++){
            
            Long id = dtoEquipo.getJugadoresEquipo().get(i).getId();
            String nombre = dtoEquipo.getJugadoresEquipo().get(i).getNombre();
            idJugadores.add(id);
            nombreJugadores.add(nombre);
        }
    }
    
    /*private void ExtraerEquipoLocal(Equipo dtoEquipo){
        
        idPartidosLocales = new ArrayList<>();
        golesEquiposLocales = new ArrayList<>();
        for(int i = 0; i < dtoEquipo.getPartidosJugadosLocal().size(); i++){
            
            Long id = dtoEquipo.getPartidosJugadosLocal().get(i).getId();
            int goles = dtoEquipo.getPartidosJugadosLocal().get(i).getGolesLocal();
            idPartidosLocales.add(id);
            golesEquiposLocales.add(goles);
        }
    }
    
    private void ExtraerEquipoVisitante(Equipo dtoEquipo){
        
        idPartidosVisitantes = new ArrayList<>();
        golesEquiposVisitantes = new ArrayList<>();
        for(int i = 0; i < dtoEquipo.getPartidosJugadosVisitante().size(); i++){
            
            Long id = dtoEquipo.getPartidosJugadosVisitante().get(i).getId();
            int goles = dtoEquipo.getPartidosJugadosVisitante().get(i).getGolesVisitante();
            idPartidosVisitantes.add(id);
            golesEquiposVisitantes.add(goles);
        }
    }*/

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public String getFechaFundacion() {
        return fechaFundacion;
    }

    public void setFechaFundacion(String fechaFundacion) {
        this.fechaFundacion = fechaFundacion;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getEntrenador() {
        return entrenador;
    }

    public void setEntrenador(String entrenador) {
        this.entrenador = entrenador;
    }

    public long getIdEquipoDto() {
        return idEquipoDto;
    }

    public void setIdEquipoDto(long idEquipoDto) {
        this.idEquipoDto = idEquipoDto;
    }

    public long getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(long presupuesto) {
        this.presupuesto = presupuesto;
    }

    public List<Long> getIdJugadores() {
        return idJugadores;
    }

    public void setIdJugadores(List<Long> idJugadores) {
        this.idJugadores = idJugadores;
    }

    public List<String> getNombreJugadores() {
        return nombreJugadores;
    }

    public void setNombreJugadores(List<String> nombreJugadores) {
        this.nombreJugadores = nombreJugadores;
    }
}
