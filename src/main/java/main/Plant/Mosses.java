package main.Plant;

import main.MagicNumbersDouble;

public final class Mosses extends Plant {
    // constructor
    public Mosses(final String name, final double mass,
                  final double oxygen, final String maturityLevel, final String typ) {
        super(name, mass, "young", 0, typ);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigenFromPlant() {
        return MagicNumbersDouble.oxygenMoss.getNumar();
    }
    @Override
    public double probabilityAttack() {
        double score = MagicNumbersDouble.patruzeci.getNumar()
                / MagicNumbersDouble.normalize.getNumar();
        return score;
    }
}
