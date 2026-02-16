package main.soil;

import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;

public final class GrasslandSoil extends Soil {
    private double rootDensity;
    // constructori
    public GrasslandSoil(final String name, final double mass) {
        super(name, mass);
    }
    public GrasslandSoil(final String name, final double mass, final double nitrogen,
                         final double waterRetention, final double solidpH,
                         final double organicMatter, final double rootDensity, final String typ) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter, typ);
        this.rootDensity = rootDensity;
    }
    // getter
    public double getRootDensity() {
        return rootDensity;
    }
    // setter
    public void setRootDensity(final double rootDensity) {
        this.rootDensity = rootDensity;
    }
    @Override
    public double soilQuality() {
        double score = (getNitrogen() * MagicNumbersDouble.nitrogen.getNumar())
                + (getOrganicMatter() * MagicNumbersDouble.organicMatter.getNumar())
                + (rootDensity * MagicNumbersDouble.rootDensity.getNumar());
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersDouble.normalize.getNumar(), score));
        double finalResult = Math.round(normalizeScore * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return finalResult;
    }
    @Override
    public double probabilityAttack() {
        double score = ((MagicNumbersInt.jumatate.getNumar() - rootDensity)
                + getWaterRetention() * MagicNumbersDouble.half.getNumar())
                / MagicNumbersInt.trei_sferturi.getNumar() * MagicNumbersInt.suta.getNumar();
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
        return true;
    }
    @Override
    public boolean isTundra() {
        return false;
    }
}
