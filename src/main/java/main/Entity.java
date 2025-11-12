package main;

import java.util.ArrayList;
import java.util.List;

public abstract class Entity {
    private String name;
    private double mass;
    private int attack;
    private boolean isScanned;
    private List<String> subject = new ArrayList<>();

    // constructor
    public Entity(String name, double mass, int probability_attack) {
        this.name = name;
        this.mass = mass;
        this.attack = 0;
        this.subject = new ArrayList<>();
    }
    // getteri
    public String getName() {
        return name;
    }
    public double getMass() {
        return mass;
    }
    public int get_attack() {
        return attack;
    }
    public boolean getisScanned() {
        return isScanned;
    }
    public List<String> get_subject() {
        return subject;
    }
    // setteri
    public void setName(String name) {
        this.name = name;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public void set_attack(int probability) {
        this.attack = probability;
    }
    public void setIsScanned(boolean isScanned) {
        this.isScanned = isScanned;
    }
    public void set_subject(List<String> subject) {
        this.subject = subject;
    }
    public void set_subject(String subject) {
        this.subject.add(subject);
    }
    // acum pentru fiecare tip de entitate
    public abstract boolean isPlant();
    public abstract boolean isAnimal();
    public abstract boolean isWater();
    public abstract boolean isSoil();
    public abstract boolean isAir();
}
