package main;

public final class Omnivores extends Animal {
    // constructor
    public Omnivores(final String name, final double mass,
                     final String state) {
        super(name, mass, state);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.saizeci.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
