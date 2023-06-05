package be.aboutcoding;

import be.aboutcoding.domain.TemperatureSensor;

import java.util.ArrayList;
import java.util.Random;

class SensorRepository {

    public ArrayList<TemperatureSensor> getSensorsWithIdIn(int[] ids) {
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
        return temperatureSensors;
    }
}
