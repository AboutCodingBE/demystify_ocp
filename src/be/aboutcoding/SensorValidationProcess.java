package be.aboutcoding;

import java.util.ArrayList;
import java.util.Random;

/**
 * This would be basic solution to the validation problem. Very simple: there is the validation algorithm, the main class
 * to start the application and something that represents a sensor.
 */
public class SensorValidationProcess {

    private static final String VALID_FIRMWARE_VERSION = "59.1.12Rev4";

    public void start(int... ids) {
        var temperatureSensors = new ArrayList<Sensor>(ids.length);
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

            temperatureSensors.add(new Sensor(id, version, make, model));
        }

        var result = new ArrayList<Sensor>();
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
