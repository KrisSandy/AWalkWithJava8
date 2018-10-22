package io.krishna.lsda.assignment2;

import java.util.*;
import java.util.stream.Collectors;

// WeatherStation Class
//----------------------

// This class consists of three attributes.
// 1. city : City of the weather station
// 2. measurements: this attribute is an ArrayList of type Measurement.
//    It will hold all the measurements of a weather station
// 3. stations: An ArrayList of type WeatherStation.

public class WeatherStation {

    private String city;
    private List<Measurement> measurements;
    public static List<WeatherStation> stations;

//    Below constructor accepts city and list of measurements as parameters and
//    assigns them to the corresponding class fields.

    public WeatherStation(String city, List<Measurement> measurements) {
        this.city = city;
        this.measurements = measurements;
    }

//    Method maxTemperature accepts two parameters, startTime and endTime
//    between which the maximum temperature is calculated and returned.

//    This method uses Java 8 streams to find the maximum value of temperature.
//    The operations performed are
//    1) filter the measurement values which are greater than startTime and
//       less than endTime.
//    2) extract the temperature values from each measurements
//    2) get the maximum temperature from the list.
//
//    An additional check has been added to ensure that there are some temperature
//    values present between the given start and end times. If the stream operation
//    returns nothing then a dummy value (minimum value of double) is returned.

    public double maxTemperature(int startTime, int endTime) {

        Optional<Double> maxTemp =
                measurements.stream()
                            .filter(x -> (x.getTime() >= startTime && x.getTime() <= endTime)) // filter the measurements which are between startTime and endTime
                            .map(Measurement::getTemperature) // extract the temperature value from each measurement
                            .max(Comparator.comparing(Double::valueOf)); // get the max value of temperature

// check if above stream operation returned a valid value, if not return
// return minimum value of double (double value)
        return maxTemp.isPresent() ? maxTemp.get() : Double.MIN_VALUE;
    }

//    Method countTemperatures accepts three parameters, t1, t2 and r and it
//    returns the counts of temperatures in the range of t1-r, t1+r and count of temperatures
//    in the range of t2-r, t2+r.

//    It follows a map reduce architecture.
//    1) Map phase takes the stations list as input and for each reading in the range of
//       t1-r and t1+r, emits a new key value pair (MapReducePair) with t1 as key and number 1 as value.
//       Same thing will be done for t2. This phase runs in parallel

//    2) Shuffle phase takes the output of the map (List of MapReducePair) and groups all the keys
//       together and and creates a list of values for each corresponding key.
//       The output will be similar to {(t1, [1,1,1,1]), (t2, [1,1])}

//    3) Reduce phase will take the shuffle output and for each key, it sums the values and emits
//       a MapReducePair with temperature as key and count as value. Reduce operation will run in parallel.


    public List<MapReducePair> countTemperatures(double t1, double t2, double r) {


//        Map Stage

        List<MapReducePair> mapperOutput = new ArrayList<>();

        stations.stream()
                .flatMap(x -> x.measurements.parallelStream()) //flattens all the measurements from
                .forEach(x -> {
                    double t = x.getTemperature(); // extract temperature from measurement
                    if (t > t1 - r && t < t1 + r) { // check for range in t1-r and t1+r
                        mapperOutput.add(new MapReducePair(t1, 1)); // create new MapReducePair with t1 as key and 1 as value
                    }

                    if (t > t2 - r && t < t2 + r) { // check for range in t2-r and t2+r
                        mapperOutput.add(new MapReducePair(t2, 1)); // create new MapReducePair with t2 as key and 1 as value
                    }
                });

        System.out.println("Mapper Output:");
        mapperOutput.stream().map(MapReducePair::toString).forEach(System.out::println);

//        Shuffle Stage

        Map<Double, List<Integer>> shuffleOutput = new HashMap<>(); // new Map to store the key and list of corresponding values.

        mapperOutput.stream()
                .forEach(x -> {
                    List<Integer> values = shuffleOutput.get(x.getKey()); // extract value by key from shuffle output.
                    if(values == null) { //If the key doesn't exist in the shuffle output add a new Entry
                        shuffleOutput.put(x.getKey(), new ArrayList<>(Arrays.asList(x.getValue())));
                    } else {
                        values.add(x.getValue()); // if key exist, just add the value to existing list
                    }
                });

        System.out.println("\nShuffle Output:");
        shuffleOutput.entrySet().stream().forEach(System.out::println);

//        Reduce stage

        List<MapReducePair> reduceOutput =

                shuffleOutput.entrySet().parallelStream() // runs the reduce phase in parallel
                        // emits a new MapReduce with temperature as key and count as value.
                    .map(x -> new MapReducePair(x.getKey(), x.getValue().stream().mapToInt(Integer::intValue).sum()))
                    .collect(Collectors.toList());


        System.out.println("\nReduce Output:");
        reduceOutput.stream().forEach(System.out::println);

        return reduceOutput;

    }

}
