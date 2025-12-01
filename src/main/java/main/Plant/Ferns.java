package main.Plant;

import main.MagicNumbersDouble;

public final class Ferns extends Plant {
    // constructor
    public Ferns(final String name, final double mass,
                 final double oxygen, final String maturityLevel, final String typ) {
        super(name, mass, maturityLevel, oxygen, typ);
    }
    // aflu oxigenul din fiecare categorie

    /**
     * Returnez oxigenul din planta
     * @return
     */
    @Override
    public double oxigenFromPlant() {
        return 0.0;
    }

    /**
     * Calculeaza probabilitatea de atac.
     * @return
     */
    @Override
    public double probabilityAttack() {
        double score = MagicNumbersDouble.treizeci.getNumar()
                / MagicNumbersDouble.normalize.getNumar();
        return score;
    }
}
