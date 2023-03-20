package be.aboutcoding;

/**
 * This is an implementation of the 'Sensor' interface. This implementation will provide us details that will tell us
 * how to accomplish what is wanted.
 */
public class Sensor {

    private static final String VALID_FIRMWARE_VERSION = "59.1.12Rev4";

    private final int id;
    private final String currentFirmwareVersion;
    private final String make;
    private final String model;

    public Sensor(int id, String firmwareVersion, String make, String model) {
        this.id = id;
        this.currentFirmwareVersion = firmwareVersion;
        this.make = make;
        this.model = model;
    }

    public int getId() {
        return this.id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getCurrentFirmwareVersion() {
        return currentFirmwareVersion;
    }
}
