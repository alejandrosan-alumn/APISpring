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
public class Resultado {
    
    String nombreEquipoLocal, nombreEquipoVisitante, arbitro;
    int golesLocal, golesVisitante;

    public Resultado(Partido partido) {
    
        nombreEquipoLocal = partido.getEquipoLocal().getNombreEquipo();
        golesLocal = partido.getGolesLocal();
        nombreEquipoVisitante = partido.getEquipoVisitante().getNombreEquipo();
        golesVisitante = partido.getGolesVisitante();
        arbitro = partido.getArbitro();
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

    public String getArbitro() {
        return arbitro;
    }

    public void setArbitro(String arbitro) {
        this.arbitro = arbitro;
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
