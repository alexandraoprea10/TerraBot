package main;

public final class TundraSoil extends Soil {
    private double permafrostDepth;
    // constructori
    public TundraSoil(final String name, final double mass) {
        super(name, mass);
    }
    public TundraSoil(final String name, final double mass,
                      final double nitrogen, final double waterRetention,
                      final double solidpH, final double organicMatter,
                      final double permafrostDepth) {
        super(name, mass, nitrogen,
                waterRetention, solidpH, organicMatter);
        this.permafrostDepth = permafrostDepth;
    }
    // getter
    public double getPermafrostDepth() {
        return permafrostDepth;
    }
    // setter
    public void setPermafrostDepth(final double permafrostDepth) {
        this.permafrostDepth = permafrostDepth;
    }
    @Override
    public double soilQuality() {
        double score = (getNitrogen() * MagicNumbersDouble.zerosapte.getNumar())
                + (getOrganicMatter() * MagicNumbersDouble.half.getNumar())
                - (permafrostDepth * MagicNumbersDouble.unucinci.getNumar());
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersDouble.normalize.getNumar(), score));
        double finalResult = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return finalResult;
    }
    @Override
    public double probabilityAttack() {
        double score =  (MagicNumbersInt.jumatate.getNumar() - permafrostDepth)
                / MagicNumbersInt.jumatate.getNumar()
                * MagicNumbersInt.suta.getNumar();
        return score;
    }
    @Override
    public boolean isForest() {
        return false;
    }
    @Override
    public boolean isSwamp() {
        return false;
    }
    @Override
    public boolean isDesertSoil() {
        return false;
    }
    @Override
    public boolean isGrassland() {
        return false;
    }
    @Override
    public boolean isTundra() {
        return true;
    }
}
