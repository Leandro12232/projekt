package Exceptions;

public class LeereNamenSIndNichtErlaubt extends RuntimeException {
    public LeereNamenSIndNichtErlaubt(String message) {
        super(message);
    }
}
