package be.aboutcoding;

/**
 * This is an implementation of the 'Sensor' interface. This implementation will provide us details that will tell us
 * how to accomplish what is wanted.
 */
public class TemperatureSensor implements Sensor {

    private static final String VALID_FIRMWARE_VERSION = "59.1.12Rev4";

    private final int id;
    private final String currentFirmwareVersion;
    private final String make;
    private final String model;

    public TemperatureSensor(int id, String firmwareVersion, String make, String model) {
        this.id = id;
        this.currentFirmwareVersion = firmwareVersion;
        this.make = make;
        this.model = model;
    }

    private int getId() {
        return this.id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    @Override
    public boolean hasValidFirmwareVersion() {
        if (VALID_FIRMWARE_VERSION.equals(this.currentFirmwareVersion)) {
            return true;
        }

        SemanticVersion currentVersion = new SemanticVersion(currentFirmwareVersion);
        SemanticVersion valid = new SemanticVersion(VALID_FIRMWARE_VERSION);

        return currentVersion.isEqualOrLargerThan(valid);
    }

    private static class SemanticVersion {
        int major = 0;
        int minor = 0;
        int patch = 0;
        int revision = 0;
        String rawVersion;

        SemanticVersion(String version) {
            this.rawVersion = version;
            parse();
        }

        void parse() {
            if (this.rawVersion != null && !this.rawVersion.isEmpty()) {
                String[] versionParts = rawVersion.split("\\.");
                try {
                    this.major = Integer.parseInt(versionParts[0]);
                    this.minor = Integer.parseInt(versionParts[1]);
                    String[] patchRevision = versionParts[2].split("Rev");
                    this.patch = Integer.parseInt(patchRevision[0]);

                    if (patchRevision.length == 2) {
                        this.revision = Integer.parseInt(patchRevision[1]);
                    }
                } catch (NumberFormatException formatException) {
                    this.major = 0;
                    this.minor = 0;
                    this.patch = 0;
                    this.revision = 0;
                }
            }
        }

        boolean isEqualOrLargerThan(SemanticVersion other) {
            if (other.major == this.major &&
                    other.minor == this.minor &&
                    other.patch == this.patch &&
                    other.revision == this.revision) {
                return true;
            }
            if (this.major > other.major) {
                return true;
            }
            if (this.major == other.major && this.major > other.minor) {
                return true;
            }
            if (this.major == other.major && this.minor == other.minor && this.patch > other.patch) {
                return true;
            } else return this.major == other.major && this.minor == other.minor && this.patch == other.patch &&
                    this.revision > other.revision;
        }
    }
}
