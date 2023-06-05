package be.aboutcoding;

import be.aboutcoding.domain.Sensor;

import java.util.ArrayList;

/**
 * This would be basic solution to the validation problem. Very simple: there is the validation algorithm, the main class
 * to start the application and something that represents a sensor.
 */
public class SensorValidationProcess {

    private SensorRepository repository = new SensorRepository();

    public void start(int... ids) {
        //Step 1: get sensors for the following ids
        var temperatureSensors = repository.getSensorsWithIdIn(ids);

        //Step 2: validate all sensors
        var invalidSensors = new ArrayList<Sensor>();
        for (var sensor : temperatureSensors) {
            if (!sensor.hasValidFirmware()) {
                invalidSensors.add(sensor);
            }
        }

        //Step 3: give result feedback
        invalidSensors.forEach(sensor -> System.out.println(sensor.getId() + " is invalid"));
    }
}
