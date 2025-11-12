package main;

public class Algae extends Plant {
    // constructor
    public Algae(String name, double mass, double oxygen, String maturity_level) {
        super(name, mass, maturity_level, oxygen);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigen_from_plant() {
        return 0.5;
    }
    @Override
    public double probability_attack() {
        double score = 20.0 / 100.0;
        return score;
    }
}
