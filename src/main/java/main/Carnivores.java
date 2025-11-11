package main;

public class Carnivores extends Animal {
    // constructor
    public Carnivores(String name, double mass, String state) {
        super(name, mass, state);
    }
    @Override
    public double probability_attack() {
        double score = (100.0 - 30.0) / 10.0;
        return score;
    }
}
