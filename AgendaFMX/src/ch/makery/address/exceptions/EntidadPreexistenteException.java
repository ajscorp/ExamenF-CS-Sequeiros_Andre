package ch.makery.address.exceptions;

public class EntidadPreexistenteException extends Exception {
    public EntidadPreexistenteException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    public EntidadPreexistenteException(String mensaje) {
        super(mensaje);
    }
}
