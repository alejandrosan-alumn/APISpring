package PracticaAPI.exception;

/**
 * Excepción de jugador no encontrado
 * @author AlexPC
 * @version Curso 2021-2022
 */
public class JugadorNotFoundException extends RuntimeException {

    public JugadorNotFoundException() {
        super();
    }

    public JugadorNotFoundException(String message) {
        super(message);
    }

    public JugadorNotFoundException(long id) {
        super("jugador not found: " + id);
    }
}
