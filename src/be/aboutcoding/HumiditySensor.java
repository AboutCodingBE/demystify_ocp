package be.aboutcoding;

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
