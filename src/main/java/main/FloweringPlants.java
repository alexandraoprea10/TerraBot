package main;

public class FloweringPlants extends Plant {
    // constructor
    public FloweringPlants(String name, double mass, double oxygen, String maturity_level) {
        super(name, mass, maturity_level, oxygen);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigen_from_plant() {
        return 6.0;
    }
    @Override
    public double probability_attack() {
        double score = 90.0 / 100.0;
        return score;
    }
}
