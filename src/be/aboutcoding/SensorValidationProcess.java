package be.aboutcoding;

import be.aboutcoding.domain.FirmwareValidation;
import be.aboutcoding.domain.TemperatureSensor;
import be.aboutcoding.domain.ValidationResult;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * This would be basic solution to the validation problem. Very simple: there is the validation algorithm, the main class
 * to start the application and something that represents a sensor.
 */
public class SensorValidationProcess {

    private SensorRepository repository = new SensorRepository();

    public void start(int... ids) {
        //Step 1: get sensors for the following ids
        var sensors = repository.getSensorsWithIdIn(ids);

        //Step 2: validate all sensors
        var invalidSensors = new ArrayList<ValidationResult>();
        for (FirmwareValidation sensor : sensors) {
            var result = sensor.hasValidFirmware();
            if (!result.isValid()) {
                invalidSensors.add(result);
            }
        }

        //Step 3: give result feedback
        invalidSensors.forEach(result -> System.out.println(result.getId() + " is invalid"));
    }
}
