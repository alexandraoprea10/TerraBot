package main;

public final class Herbivores extends Animal {
    // constructor
    public Herbivores(final String name, final double mass, final String state) {
        super(name, mass, state);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.optcinci.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
