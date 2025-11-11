package main;

public class Omnivores extends Animal{
    private static final double posibility = (100.0 - 60.0) / 10.0;
    // constructor
    public Omnivores(String name, double mass, String state) {
        super(name, mass, state);
    }
    @Override
    public double probability_attack() {
        double score = (100.0 - 60.0) / 10.0;
        return score;
    }
}
