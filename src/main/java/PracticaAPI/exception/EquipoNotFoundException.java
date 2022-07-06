/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PracticaAPI.exception;

/**
 *
 * @author alexP
 */
public class EquipoNotFoundException extends RuntimeException {

    public EquipoNotFoundException() {
    
        super();
    }
    
    public EquipoNotFoundException(String message) {
    
        super(message);
    }
    
    public EquipoNotFoundException(long id){
    
        super("Equipo not found: "+id);
    }
}
