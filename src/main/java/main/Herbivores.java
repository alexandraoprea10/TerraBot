package main;

public class Herbivores extends Animal {
    // constructor
    public Herbivores(String name, double mass, String state) {
        super(name, mass, state);
    }
    @Override
    public double probability_attack() {
        double score = (100.0 - 85.0) / 10.0;
        return score;
    }
}
