package be.aboutcoding.domain;

public class PressureSensor implements FirmwareValidation {

    private static final Integer VALID_FIRMWARE_VERSION = 237;

    private final int id;
    private final String currentFirmwareVersion;
    private final String make;
    private final String model;

    public PressureSensor(int id, String currentFirmwareVersion, String make, String model) {
        this.id = id;
        this.currentFirmwareVersion = currentFirmwareVersion;
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return id;
    }

    public String getCurrentFirmwareVersion() {
        return currentFirmwareVersion;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public ValidationResult hasValidFirmware() {
        try {
            var isValid =  Integer.parseInt(this.currentFirmwareVersion) >= VALID_FIRMWARE_VERSION;
            return new ValidationResult(this.id, isValid);
        }
        catch(NumberFormatException formatException) {
            return new ValidationResult(id, false);
        }
    }
}