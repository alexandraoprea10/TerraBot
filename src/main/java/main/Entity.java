package main;

public abstract class Entity {
    private String name;
    private double mass;
    private int attack;

    // constructor
    public Entity(String name, double mass, int probability_attack) {
        this.name = name;
        this.mass = mass;
        this.attack = 0;
    }
    // getteri
    public String getName(){
        return name;
    }
    public double getMass(){
        return mass;
    }
    public int get_attack() {
        return attack;
    }
    // setteri
    public void setName(String name){
        this.name = name;
    }
    public void setMass(double mass){
        this.mass = mass;
    }
    public void set_attack(int probability){
        this.attack = probability;
    }
    // acum pentru fiecare tip de entitate
    public abstract boolean isPlant();
    public abstract boolean isAnimal();
    public abstract boolean isWater();
    public abstract boolean isSoil();
    public abstract boolean isAir();
}
