package be.aboutcoding;

import java.util.ArrayList;
import java.util.List;

/**
 * This is basically the main 'algorithm'. There are more places in this algorithm where you potentially could use the
 * OCP. However, since there currently are no other variations except for the difference in versioning of the sensor
 * firmware, I would say that proactively adding interfaces here would not benefit the code at all.
 *
 * As for the 'hasValidFirmwareVersion()' example:
 * You can think of an interface as some kind of pivot point for change: the method or methods in an interface will stay
 * pretty much the same, but you can have several implementations which can be swapped at runtime. That means that this code,
 * this process code in this class, doesn't have to change if we would need another way of validating a firmware version.
 *
 */
public class SensorValidationProcess {

    public void start(int...ids) {
        //step 1: fetch the target sensors with the given ids
        SensorFactory factory = new SensorFactory();
        List<Sensor> sensors = factory.getSensors(ids);

        //step 2: validate all of them
        List<Boolean> result = new ArrayList<>();
        for (Sensor sensor : sensors) {
            result.add(sensor.hasValidFirmwareVersion()); // Polymorphism at work here!
        }

        //step 3: give result feedback
        long amountInvalid = result.stream()
                .filter(isValid -> isValid.equals(false))
                .count();

        System.out.println("There are " + amountInvalid +
                " sensors with invalid firmware.");
    }
}
