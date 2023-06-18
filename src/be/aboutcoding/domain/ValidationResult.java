package be.aboutcoding.domain;

public class ValidationResult {

    private int id;
    private boolean isValid;

    public ValidationResult(int id, boolean isValid) {
        this.id = id;
        this.isValid = isValid;
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        return isValid;
    }
}
