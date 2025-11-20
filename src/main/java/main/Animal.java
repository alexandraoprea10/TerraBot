package main;

public abstract class Animal extends Entity {
    private String state;
    private String type;
    private boolean mutat;
    // constructori
    public Animal(final String name, final double mass) {
        super(name, mass, 0, " ");
        this.state = "hungry";
        this.mutat = false;
    }
    public Animal(final String name, final double mass, final String state, final String type) {
        super(name, mass, 0, type);
        this.state = state;
        this.type = type;
        this.mutat = false;
    }
    // getter

    /**
     * Returnez starea animalului
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Verific daca s-a mutat animalul.
     * @return
     */
    public boolean isMutat() {
        return mutat;
    }

    /**
     * REturnez tipul de animal, imi trebuie pentru t16.
     * @return
     */
    public String getType() {
        return type;
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
     * Setez type.
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Setez daca animalul s-a mutat.
     * @param mutat
     */
    public void  setMutat(final boolean mutat) {
        this.mutat = mutat;
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
