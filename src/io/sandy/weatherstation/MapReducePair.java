package io.krishna.lsda.assignment2;

// MapReducePair Class
//----------------------

// This class acts as a key value pair for passing data between Map, Shuffle and Reduce phase.
// It has two attributes
// 1) key
// 2) value


public class MapReducePair {

    private double key;
    private int value;

//  Below constructor accepts key and value as parameters and assigns
//  them to the class fields

    public MapReducePair(double key, int value) {
        this.key = key;
        this.value = value;
    }

    public double getKey() {
        return key;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "MapReducePair{" +
                "key=" + key +
                ", value=" + value +
                '}';
    }
}
