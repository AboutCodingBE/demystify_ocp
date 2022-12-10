package be.aboutcoding;

/**
 * This could possibly be another implementation. A different kind of sensor with yet another way of versioning.
 */
public class HumiditySensor implements Sensor{

    private static final String VALID_FIRMWARE_VERSION = "99";

    private final int id;
    private final String currentVersion;
    private final String make;
    private final String model;

    public HumiditySensor(int id, String currentVersion, String make, String model){
        this.id = id;
        this.currentVersion = currentVersion;
        this.make = make;
        this.model = model;
    }

    @Override
    public boolean hasValidFirmwareVersion() {
        // implement the details here
        return false;
    }

}
