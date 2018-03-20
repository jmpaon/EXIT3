package exit3.model;

public class ModelException extends RuntimeException {

    private String reason;

    public ModelException(String reason) {
        super();
        this.reason = reason;
    }


}
