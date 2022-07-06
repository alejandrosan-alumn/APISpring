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
public class PartidoNotFoundException extends RuntimeException {

    public PartidoNotFoundException() {
    
        super();
    }
    
    public PartidoNotFoundException(String message) {
   
        super(message);
    }
    
    public PartidoNotFoundException(long id) {
        
        super("partido not found: " + id);
    }
}
