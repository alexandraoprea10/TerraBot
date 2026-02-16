package main.animal;

import main.magicNumbers.MagicNumbersDouble;

public final class Herbivores extends Animal {
    // constructor
    public Herbivores(final String name, final double mass, final String state, final String type) {
        super(name, mass, state, type);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.optcinci.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
