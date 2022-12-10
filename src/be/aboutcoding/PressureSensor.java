package be.aboutcoding;

/**
 * This is another implementation of the 'Sensor' interface. This implementation will provide us details that will tell us
 * how to accomplish what is wanted.
 */

public class PressureSensor implements Sensor {
    private static final String VALID_FIRMWARE_VERSION = "99";

    private final int id;
    private final String currentVersion;
    private final String make;
    private final String model;

    public PressureSensor(int id, String currentVersion, String make, String model) {
        this.id = id;
        this.currentVersion = currentVersion;
        this.make = make;
        this.model = model;
    }

    @Override
    public boolean hasValidFirmwareVersion() {
        try {
            int current = Integer.parseInt(currentVersion);
            int valid = Integer.parseInt(VALID_FIRMWARE_VERSION);

            return current >= valid;
        }
        catch (NumberFormatException nfe) {
            System.out.println("Not a valid version");
            return false;
        }
    }
}
