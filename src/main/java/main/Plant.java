package main;

public abstract class Plant extends Entity {
    private String maturity_level;
    private double nivel_crestere;
    private double oxygen_curent;
    private boolean isScanned;
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
    public double getNivel_crestere() {
        return nivel_crestere;
    }
    public boolean getisScanned() {
        return isScanned;
    }
    // setter
    public void setMaturity_level(String maturity_level) {
        this.maturity_level = maturity_level;
    }
    public void setOxygen_curent(double oxygen_curent) {
        this.oxygen_curent = oxygen_curent;
    }
    public void setNivel_crestere(double nivel_crestere) {
        this.nivel_crestere = nivel_crestere;
    }
    public void setIsScanned(boolean isScanned) {
        this.isScanned = isScanned;
    }
    // oxigenul din fiecare categorie
    public double maturity_oxigen_level() {
        double result = 0.0;
        if (maturity_level.equals("young")){
            result = 0.2;
        }
        else if (maturity_level.equals("mature")) {
            result = 0.7;
        }
        else if (maturity_level.equals("old")) {
            result = 0.4;
        }
        return Math.round(result * 100.0) / 100.0;
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
