package main.animal;

import main.magicNumbers.MagicNumbersDouble;

public final class Omnivores extends Animal {
    // constructor
    public Omnivores(final String name, final double mass,
                     final String state, final String type) {
        super(name, mass, state, type);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.saizeci.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
