package awktal.mule;

class NoNextPlayerException extends RuntimeException {
    public NoNextPlayerException(String errorMessage) {
        super(errorMessage);
    }
}