package main;

public final class Detritivores extends Animal {
    // constructor
    public Detritivores(final String name, final double mass, final String state) {
        super(name, mass, state);
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersDouble.normalize.getNumar()
                - MagicNumbersDouble.atacDetr.getNumar())
                / MagicNumbersDouble.zece.getNumar();
        return score;
    }
}
