/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import PracticaAPI.domain.Partido;

/**
 *
 * @author alexP
 */
public class DTOPartido {
    
    Long idPartido, idEquipoLocal, idEquipoVisitante;
    String fechaHoraJugada, arbitro, estadio, nombreEquipoLocal, nombreEquipoVisitante;
    int numAsistentes, golesLocal, golesVisitante;

    public DTOPartido(Partido dtoPartido) {
    
        idPartido = dtoPartido.getId();
        idEquipoLocal = dtoPartido.getEquipoLocal().getId();
        idEquipoVisitante = dtoPartido.getEquipoVisitante().getId();
        this.fechaHoraJugada = dtoPartido.getFechaJugado();
        arbitro = dtoPartido.getArbitro();
        estadio = dtoPartido.getEstadioJugado();
        nombreEquipoLocal = dtoPartido.getEquipoLocal().getNombreEquipo();
        nombreEquipoVisitante = dtoPartido.getEquipoVisitante().getNombreEquipo();
        numAsistentes = dtoPartido.getNumAsistente();
        golesLocal = dtoPartido.getGolesLocal();
        golesVisitante = dtoPartido.getGolesVisitante();
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public Long getIdEquipoLocal() {
        return idEquipoLocal;
    }

    public void setIdEquipoLocal(Long idEquipoLocal) {
        this.idEquipoLocal = idEquipoLocal;
    }

    public Long getIdEquipoVisitante() {
        return idEquipoVisitante;
    }

    public void setIdEquipoVisitante(Long idEquipoVisitante) {
        this.idEquipoVisitante = idEquipoVisitante;
    }

    public String getFechaHoraJugada() {
        return fechaHoraJugada;
    }

    public void setFechaHoraJugada(String fechaHoraJugada) {
        this.fechaHoraJugada = fechaHoraJugada;
    }

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
    }

    public String getEstadio() {
        return estadio;
    }

    public void setEstadio(String estadio) {
        this.estadio = estadio;
    }

    public String getNombreEquipoLocal() {
        return nombreEquipoLocal;
    }

    public void setNombreEquipoLocal(String nombreEquipoLocal) {
        this.nombreEquipoLocal = nombreEquipoLocal;
    }

    public String getNombreEquipoVisitante() {
        return nombreEquipoVisitante;
    }

    public void setNombreEquipoVisitante(String nombreEquipoVisitante) {
        this.nombreEquipoVisitante = nombreEquipoVisitante;
    }

    public int getNumAsistentes() {
        return numAsistentes;
    }

    public void setNumAsistentes(int numAsistentes) {
        this.numAsistentes = numAsistentes;
    }

    public int getGolesLocal() {
        return golesLocal;
    }

    public void setGolesLocal(int golesLocal) {
        this.golesLocal = golesLocal;
    }

    public int getGolesVisitante() {
        return golesVisitante;
    }

    public void setGolesVisitante(int golesVisitante) {
        this.golesVisitante = golesVisitante;
    }
    
    
    
}
