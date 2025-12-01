package main.Animal;

import main.MagicNumbersDouble;

public final class Detritivores extends Animal {
    // constructor
    public Detritivores(final String name, final double mass,
                        final String state, final String type) {
        super(name, mass, state, type);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.atacDetr.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
