package main;

public class Parasites extends Animal {
    // constructor
    public Parasites(String name, double mass, String state) {
        super(name, mass, state);
    }
    @Override
    public double probability_attack() {
        double score = (100.0 - 10.0) / 10.0;
        return score;
    }
}
