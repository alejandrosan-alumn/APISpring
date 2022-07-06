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
public class ResultadoNotFoundException extends RuntimeException {

    public ResultadoNotFoundException() {
    
        super();
    }
    
    public ResultadoNotFoundException(String message) {
    
        super(message);
    }
    
    public ResultadoNotFoundException(long id){
    
        super("Resultado not found: "+id);
    }
}
