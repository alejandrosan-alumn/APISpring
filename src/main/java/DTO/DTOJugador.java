/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import PracticaAPI.domain.Jugador;
import java.time.LocalDateTime;

/**
 *
 * @author alexP
 */
public class DTOJugador {
    
    private String nombreJugador,posicionJugador, nacionalidadJugador ,nombreEquipoPertenece;
    private long idEquipoPertenece, idJugadordto;
    private int dorsal;
    private String fechaNacimientoJugador;

    public DTOJugador() {
    }
    
    public DTOJugador(Jugador dtoJugador) {
        
        idJugadordto = dtoJugador.getId();
        nombreJugador = dtoJugador.getNombre();
        dorsal = dtoJugador.getDorsal();
        posicionJugador = dtoJugador.getPosicion();
        fechaNacimientoJugador = dtoJugador.getFechaNacimiento();
        nacionalidadJugador = dtoJugador.getNacionalidad();
        
        idEquipoPertenece = dtoJugador.getEquipoJugador().getId();
        nombreEquipoPertenece = dtoJugador.getEquipoJugador().getNombreEquipo();
    }

    public long getIdJugadordto() {
        return idJugadordto;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public String getPosicionJugador() {
        return posicionJugador;
    }

    public void setPosicionJugador(String posicionJugador) {
        this.posicionJugador = posicionJugador;
    }

    public String getNacionalidadJugador() {
        return nacionalidadJugador;
    }

    public void setNacionalidadJugador(String nacionalidadJugador) {
        this.nacionalidadJugador = nacionalidadJugador;
    }

    public String getNombreEquipoPertenece() {
        return nombreEquipoPertenece;
    }

    public void setNombreEquipoPertenece(String nombreEquipoPertenece) {
        this.nombreEquipoPertenece = nombreEquipoPertenece;
    }

    public long getIdEquipoPertenece() {
        return idEquipoPertenece;
    }

    public void setIdEquipoPertenece(long idEquipoPertenece) {
        this.idEquipoPertenece = idEquipoPertenece;
    }

    public int getDorsal() {
        return dorsal;
    }

    public void setDorsal(int dorsal) {
        this.dorsal = dorsal;
    }

    public String getFechaNacimientoJugador() {
        return fechaNacimientoJugador;
    }

    public void setFechaNacimientoJugador(String fechaNacimientoJugador) {
        this.fechaNacimientoJugador = fechaNacimientoJugador;
    }
    
    
}
