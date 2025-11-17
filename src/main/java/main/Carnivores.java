package main;

public final class Carnivores extends Animal {
    // constructor
    public Carnivores(final String name, final double mass, final String state, final String type) {
        super(name, mass, state, type);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.treizeci.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
