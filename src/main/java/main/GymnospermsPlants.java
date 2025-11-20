package main;

public final class GymnospermsPlants extends Plant {
    // constructor
    public GymnospermsPlants(final String name, final double mass,
                             final double oxygen, final String maturityLevel, final String typ) {
        super(name, mass, maturityLevel, 0, typ);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigenFromPlant() {
        return 0.0;
    }
    @Override
    public double probabilityAttack() {
        double score = MagicNumbersDouble.saizeci.getNumar()
                / MagicNumbersDouble.normalize.getNumar();
        return score;
    }
}
