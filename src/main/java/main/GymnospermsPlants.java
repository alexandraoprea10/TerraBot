package main;

public class GymnospermsPlants extends Plant {
    // constructor
    public GymnospermsPlants(String name, double mass, double oxygen, String maturity_level) {
        super(name, mass, maturity_level, 0);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigen_from_plant() {
        return 0.0;
    }
    @Override
    public double probability_attack() {
        double score = 60.0 / 100.0;
        return score;
    }
}
