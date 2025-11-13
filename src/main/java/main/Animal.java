package main;

public abstract class Animal extends Entity {
    private String state;
    // constructori
    public Animal(final String name, final double mass) {
        super(name, mass, 0);
        this.state = "hungry";
    }
    public Animal(final String name, final double mass, final String state) {
        super(name, mass, 0);
        this.state = state;
    }
    // getter

    /**
     * Returnez starea animalului
     * @return
     */
    public String getState() {
        return state;
    }
    // setter

    /**
     * Setez daca animalul e well fed hungry sau sick
     * @param stat
     */
    public void setState(final String stat) {
        this.state = stat;
    }

    /**
     * Calculez probabilitatea de atac
     * @return
     */
    public abstract double probabilityAttack();

    /**
     * Returnez false, e aer.
     * @return
     */
    @Override
    public final boolean isPlant() {
        return false;
    }

    /**
     * Returnez false, e aer.
     * @return
     */
    @Override
    public final boolean isAnimal() {
        return true;
    }

    /**
     * Returnez false, e aer.
     * @return
     */
    @Override
    public final boolean isWater() {
        return false;
    }

    /**
     * Returnez false, e aer.
     * @return
     */
    @Override
    public final boolean isSoil() {
        return false;
    }

    /**
     * Returnez true doar daca e aer.
     * @return
     */
    @Override
    public final boolean isAir() {
        return false;
    }
}
