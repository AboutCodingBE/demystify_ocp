package be.aboutcoding;

import java.util.ArrayList;
import java.util.Random;

class SensorRepository {

    public ArrayList<Sensor> getSensorsWithIdIn(int[] ids) {
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
        return temperatureSensors;
    }
}
