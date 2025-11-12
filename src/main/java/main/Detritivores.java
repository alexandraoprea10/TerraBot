package main;

public class Detritivores extends Animal {
    // constructor
    public Detritivores(String name, double mass, String state) {
        super(name, mass, state);
    }
    @Override
    public double probability_attack() {
        double score = (100.0 - 90.0) / 10.0;
        return score;
    }
}
