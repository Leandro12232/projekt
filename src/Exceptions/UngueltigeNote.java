package Exceptions;

public class UngueltigeNote extends RuntimeException {
    public UngueltigeNote(String message) {
        super(message);
    }
}
