package main.Plant;

import main.MagicNumbersDouble;

public final class Algae extends Plant {
    // constructor
    public Algae(final String name, final double mass,
                 final double oxygen, final String maturityLevel, final String typ) {
        super(name, mass, maturityLevel, oxygen, typ);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigenFromPlant() {
        return MagicNumbersDouble.half.getNumar();
    }
    @Override
    public double probabilityAttack() {
        double score = MagicNumbersDouble.probAlgae.getNumar()
                / MagicNumbersDouble.normalize.getNumar();
        return score;
    }
}
