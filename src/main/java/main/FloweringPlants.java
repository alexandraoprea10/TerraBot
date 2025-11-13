package main;

public final class FloweringPlants extends Plant {
    // constructor
    public FloweringPlants(final String name, final double mass,
                           final double oxygen, final String maturityLevel) {
        super(name, mass, maturityLevel, oxygen);
    }
    // aflu oxigenul din fiecare categorie
    @Override
    public double oxigenFromPlant() {
        return MagicNumbersDouble.sase.getNumar();
    }
    @Override
    public double probabilityAttack() {
        double score = MagicNumbersDouble.nouzeci.getNumar()
                / MagicNumbersDouble.normalize.getNumar();
        return score;
    }
}
