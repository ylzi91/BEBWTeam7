package yurilenzi.exceptions;

public class NothingGenException extends Exception{
    public <T> NothingGenException(Class<T> genericClass){
        super(genericClass.getSimpleName() + " non disponibile");
    }
}
