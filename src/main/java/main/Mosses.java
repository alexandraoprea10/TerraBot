package main;

public class Mosses extends Plant {
    // constructor
    public Mosses(String name, double mass, double oxygen, String maturity_level) {
        super(name, mass, "young", 0);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigen_from_plant() {
        return 0.8;
    }
    @Override
    public double probability_attack() {
        double score = 40.0 / 100.0;
        return score;
    }
}
