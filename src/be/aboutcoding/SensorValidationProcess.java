package be.aboutcoding;

import java.util.ArrayList;
import java.util.Random;

/**
 * This would be basic solution to the validation problem. Very simple: there is the validation algorithm, the main class
 * to start the application and something that represents a sensor.
 */
public class SensorValidationProcess {

    private static final String VALID_FIRMWARE_VERSION = "59.1.12Rev4";
    private SensorRepository repository = new SensorRepository();

    public void start(int... ids) {
        //Step 1: get sensors for the following ids
        var temperatureSensors = repository.getSensorsWithIdIn(ids);

        var invalidSensors = new ArrayList<Sensor>();
        for (var sensor : temperatureSensors) {
            if (!VALID_FIRMWARE_VERSION.equals(sensor.getCurrentFirmwareVersion())) {
                var currentVersion = new SemanticVersion(sensor.getCurrentFirmwareVersion());
                var validVersion = new SemanticVersion(VALID_FIRMWARE_VERSION);
                if (!currentVersion.isEqualOrLargerThan(validVersion)) {
                    invalidSensors.add(sensor);
                }
            }
        }

        invalidSensors.forEach(sensor -> System.out.println(sensor.getId() + " is invalid"));
    }

    private static class SemanticVersion {
        int major = 0;
        int minor = 0;
        int patch = 0;
        int revision = 0;
        String rawVersion;

        SemanticVersion(String version) {
            this.rawVersion = version;
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
