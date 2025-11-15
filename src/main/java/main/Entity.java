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
    public Entity(final String name, final double mass, final int probabilityAttack) {
        this.name = name;
        this.mass = mass;
        this.attack = 0;
        this.isScanned = false;
        this.subject = new ArrayList<>();
    }
    // getteri

    /**
     * Returneaza numele entitatii.
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Returneaza masa entitatii.
     * @return
     */
    public double getMass() {
        return mass;
    }

    /**
     * Returneaza probabilitatea de atac.
     * @return
     */
    public int getAttack() {
        return attack;
    }

    /**
     * Returneaza daca obiectul este scanat sau nu.
     * @return
     */
    public boolean getisScanned() {
        return isScanned;
    }

    /**
     * Returneaza lista de subiecte din learnFact
     * @return
     */
    public List<String> getSubject() {
        return subject;
    }
    // setteri

    /**
     * Seteaza numele entitatii.
     * @param nume
     */
    public void setName(final String nume) {
        this.name = nume;
    }

    /**
     * Seteaza masa entitatii.
     * @param mas
     */
    public void setMass(final double mas) {
        this.mass = mas;
    }

    /**
     * Seteaza probabilitatea de atac
     * @param probab
     */
    public void setAttack(final int probab) {
        this.attack = probab;
    }

    /**
     * Seteaza scanarea obiectului.
     * @param scanned
     */
    public void setIsScanned(final boolean scanned) {
        this.isScanned = scanned;
    }

    /**
     * Seteaza subiectul, daca e lista
     * @param subj
     */
    public void setSubject(final List<String> subj) {
        this.subject = subj;
    }

    /**
     * Seteaza subiectul, daca il adauga in lista
     * @param subj
     */
    public void setSubject(final String subj) {
        this.subject.add(subj);
    }
    // acum pentru fiecare tip de entitate

    /**
     * Verific daca e planta
     * @return
     */
    public abstract boolean isPlant();

    /**
     * Verific daca e animal
     * @return
     */
    public abstract boolean isAnimal();

    /**
     * Verific daca e apa
     * @return
     */
    public abstract boolean isWater();

    /**
     * Verifica daca e sol
     * @return
     */
    public abstract boolean isSoil();

    /**
     * Verifica daca e aer
     * @return
     */
    public abstract boolean isAir();
}
