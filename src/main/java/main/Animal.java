package main;

public abstract class Animal extends Entity{
    private String state;
    private boolean isScanned;
    // constructori
    public Animal(String name, double mass) {
        super(name, mass, 0);
        this.state = "hungry";
    }
    public Animal(String name, double mass, String state) {
        super(name, mass, 0);
        this.state = state;
    }
    // getter
    public String getState() {
        return state;
    }
    public boolean getisScanned() {
        return isScanned;
    }
    // setter
    public void setState(String state) {
        this.state = state;
    }
    public void setIsScanned(boolean isScanned) {
        this.isScanned = isScanned;
    }
    public abstract double probability_attack();
    @Override
    public boolean isPlant() {
        return false;
    }
    @Override
    public boolean isAnimal() {
        return true;
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
