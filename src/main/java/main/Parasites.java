package main;

public final class Parasites extends Animal {
    // constructor
    public Parasites(final String name, final double mass, final String state) {
        super(name, mass, state);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.zece.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
