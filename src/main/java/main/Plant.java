package main;

public abstract class Plant extends Entity {
    private String maturity_level;
    private double oxygen_curent;
    // constructor
    public Plant(String name, double mass, String maturity_level,  double oxygen_curent) {
        super(name, mass, 0);
        this.maturity_level = maturity_level;
        this.oxygen_curent = oxygen_curent;
    }
    // getter
    public String getMaturity_level() {
        return maturity_level;
    }
    public double getOxygen_curent() {
        return oxygen_curent;
    }
    // setter
    public void setMaturity_level(String maturity_level) {
        this.maturity_level = maturity_level;
    }
    public void setOxygen_curent(double oxygen_curent) {
        this.oxygen_curent = oxygen_curent;
    }
    // oxigenul din fiecare categorie
    public double maturity_oxigen_level() {
        if (maturity_level.equals("young")){
            return 0.2;
        }
        else if (maturity_level.equals("mature")) {
            return 0.7;
        }
        else if (maturity_level.equals("old")) {
            return 0.4;
        }
        else return 0.0;
    }
    public abstract double oxigen_from_plant();
    public abstract double probability_attack();
    @Override
    public boolean isPlant() {
        return true;
    }
    @Override
    public boolean isAnimal() {
        return false;
    }
    @Override
    public boolean isWater() {
        return false;
    }
    @Override
    public boolean isSoil() {
        return false;
    }
    @Override
    public boolean isAir() {
        return false;
    }
}
