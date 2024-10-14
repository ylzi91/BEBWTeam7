package yurilenzi.exceptions;

public class NotFoundException extends Exception {
    public NotFoundException(String id) {
        super(id + ": Non è stato trovato");
    }
}
