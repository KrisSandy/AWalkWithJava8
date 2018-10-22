package io.krishna.lsda.assignment2;

import java.util.Arrays;
import java.util.List;

public class WeatherMain {

    public static void main(String[] args) {

//      create a new list of measurement class with some test readings.

        List<Measurement> measurementsA = Arrays.asList(
                new Measurement(10, 26.4),
                new Measurement(11, 28.1),
                new Measurement(12, 29.0),
                new Measurement(13, 31.3),
                new Measurement(14, 32.8),
                new Measurement(15, 34.1),
                new Measurement(16, 33.3),
                new Measurement(17, 30.2),
                new Measurement(18, 29.7));

//      create a new WeatherStation object

        WeatherStation weatherStationA = new WeatherStation("GALWAY", measurementsA);

        System.out.println("\nQuestion 1");
        System.out.println("----------");

        System.out.println("Weather station measurements: ");
        System.out.println(measurementsA.toString());


//      Test 1: Check the max value between 11 and 13
        System.out.println("Max value between 11 and 13 : " + weatherStationA.maxTemperature(11, 13));
//
//      Test 2: Check the max value between 10 and 18
        System.out.println("Max value between 10 and 18 : " + weatherStationA.maxTemperature(10, 18));
//
//      Test 3: Check the max value between a range beyond the boundary
        System.out.println("Max value between 8 and 22 : " + weatherStationA.maxTemperature(8, 22));

//      Test 4: Check to see whether dummy value is returned, if there are
//              no measurements in the specified range
        System.out.print("Max value between 3 and 7 : ");
        double maxTemp = weatherStationA.maxTemperature(3,7);
        if (maxTemp == Double.MIN_VALUE) {
            System.out.println("No readings in this range");
        } else {
            System.out.println(maxTemp);
        }

        //    Question 2

        System.out.println("\nQuestion 2");
        System.out.println("----------");

        List<Measurement> measurementsB = Arrays.asList(
                new Measurement(10, 23.4),
                new Measurement(11, 24.1),
                new Measurement(12, 26.0),
                new Measurement(13, 30.3),
                new Measurement(14, 33.2),
                new Measurement(15, 32.2),
                new Measurement(16, 33.7));

        WeatherStation weatherStationB = new WeatherStation("DUBLIN", measurementsB);

//        stations = Arrays.asList(weatherStationA, weatherStationB);

//        weatherStationB.countTemperatures(30.0, 28.3, 2.4);

        WeatherStation weatherStationC = new WeatherStation("DUBLIN", Arrays.asList(
                new Measurement(10, 20.0),
                new Measurement(11, 11.7),
                new Measurement(12, -5.4),
                new Measurement(13, 18.7),
                new Measurement(14, 20.9)));

        WeatherStation weatherStationD = new WeatherStation("DUBLIN", Arrays.asList(
                new Measurement(10, 8.4),
                new Measurement(11, 19.2),
                new Measurement(12, 7.2)));

//      Count Temperature Test 1

        WeatherStation.stations = Arrays.asList(weatherStationC, weatherStationD);

        List<MapReducePair> counts1 = weatherStationD.countTemperatures (19.0,10.8,2.1);

        System.out.println("\nFinal output");
        counts1.stream().forEach(System.out::println);

//      Count Temperature Test 2

        WeatherStation.stations = Arrays.asList(weatherStationA, weatherStationB, weatherStationC, weatherStationD);

        List<MapReducePair> counts2 = weatherStationD.countTemperatures (25.0,30,1.5);

        System.out.println("\nFinal output");
        counts2.stream().forEach(System.out::println);

    }
}
