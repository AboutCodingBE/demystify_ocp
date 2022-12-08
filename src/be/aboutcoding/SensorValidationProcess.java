package be.aboutcoding;

import java.util.ArrayList;
import java.util.List;

public class SensorValidationProcess {

    public void start(int...ids) {
        //step 1: fetch the target sensors with the given ids
        SensorFactory factory = new SensorFactory();
        List<Sensor> sensors = factory.getSensors(ids);

        //step 2: validate all of them
        List<Boolean> result = new ArrayList<>();
        for (Sensor sensor : sensors) {
            result.add(sensor.hasValidFirmwareVersion());
        }

        //step 3: give result feedback
        long amountInvalid = result.stream()
                .filter(isValid -> isValid.equals(false))
                .count();

        System.out.println("There are " + amountInvalid +
                " sensors with invalid firmware.");
    }
}
