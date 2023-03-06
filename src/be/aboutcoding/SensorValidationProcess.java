package be.aboutcoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This is basically the main 'algorithm'. There are more places in this algorithm where you potentially could use the
 * OCP. However, since there currently are no other variations except for the difference in versioning of the sensor
 * firmware, I would say that proactively adding interfaces here would not benefit the code at all.
 * <p>
 * As for the 'hasValidFirmwareVersion()' example:
 * You can think of an interface as some kind of pivot point for change: the method or methods in an interface will stay
 * pretty much the same, but you can have several implementations which can be swapped at runtime. That means that this code,
 * this process code in this class, doesn't have to change if we would need another way of validating a firmware version.
 */
public class SensorValidationProcess {

    private static final String VALID_FIRMWARE_VERSION = "59.1.12Rev4";

    public void start(int... ids) {
        var temperatureSensors = new ArrayList<TemperatureSensor>(ids.length);
        for (int id : ids) {
            Random randomGenerator = new Random();
            var make = "ProSense";
            var model = "T1000";
            var randomMajor = randomGenerator.nextInt(60) + 1;
            var randomMinor = randomGenerator.nextInt(20) + 1;
            var randomPatdch = randomGenerator.nextInt(120) + 1;
            var randomRevision = randomGenerator.nextInt(10) + 1;

            String version = randomMajor + "." +
                    randomMinor +
                    "." +
                    randomPatdch +
                    "Rev" +
                    randomRevision;

            System.out.println("Id: " + id + " has version: " + version);

            temperatureSensors.add(new TemperatureSensor(id, version, make, model));
        }

        var result = new ArrayList<TemperatureSensor>();
        for (var sensor : temperatureSensors) {
            if (!VALID_FIRMWARE_VERSION.equals(sensor.getCurrentFirmwareVersion())) {
                var currentVersion = new SemanticVersion(sensor.getCurrentFirmwareVersion());
                var validVersion = new SemanticVersion(VALID_FIRMWARE_VERSION);
                if (!currentVersion.isEqualOrLargerThan(validVersion)) {
                    result.add(sensor);
                }
            }
        }

        result.forEach(sensor -> System.out.println(sensor.getId() + " is invalid"));
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
