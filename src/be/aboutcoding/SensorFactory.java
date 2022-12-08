package be.aboutcoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SensorFactory {
    public List<Sensor> getTemperatureSensors(int[] ids) {
        List<Sensor> sensors = new ArrayList<>(ids.length);
        for (int id : ids) {
            TemperatureSensor sensor = createTemperatureSensor(id);
            sensors.add(sensor);
        }
        return sensors;
    }

    public List<PressureSensor> getPressureSensors(int[] ids) {
        List<PressureSensor> sensors = new ArrayList<>(ids.length);
        for (int id : ids) {
            PressureSensor sensor = createPressureSensor(id);
            sensors.add(sensor);
        }
        return sensors;
    }

    public List<Sensor> getSensors(int[] ids) {
        Random randomGenerator = new Random();
        List<Sensor> sensors = new ArrayList<>(ids.length);
        for (int id : ids) {
            int randomInt = randomGenerator.nextInt(100);
            if (randomInt % 2 == 0) {
                TemperatureSensor sensor = createTemperatureSensor(id);
                sensors.add(sensor);
            }
            else {
                PressureSensor sensor = createPressureSensor(id);
                sensors.add(sensor);
            }
        }

        return sensors;
    }

    private TemperatureSensor createTemperatureSensor(int id) {
        Random randomGenerator = new Random();
        String make = "ProSense";
        String model = "T1000";
        int randomMajor = randomGenerator.nextInt(60) + 1;
        int randomMinor = randomGenerator.nextInt(20) + 1;
        int randomPatdch = randomGenerator.nextInt(120) + 1;
        int randomRevision = randomGenerator.nextInt(10) + 1;

        String version = randomMajor + "." +
                randomMinor +
                "." +
                randomPatdch +
                "Rev" +
                randomRevision;

        System.out.println("Id: " + id + " has version: " + version);

        return new TemperatureSensor(id, version, make, model);
    }

    private PressureSensor createPressureSensor(int id) {
        Random randomGenerator = new Random();
        String make = "SensorX";
        String model = "PS50";
        int version = randomGenerator.nextInt(270) + 1;
        return new PressureSensor(id, String.valueOf(version), make, model);
    }
}
