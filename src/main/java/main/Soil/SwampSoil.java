package main.Soil;

import main.MagicNumbersDouble;
import main.MagicNumbersInt;

public final class SwampSoil extends Soil {
    private double waterLogging;
    // constructor
    public SwampSoil(final String name, final double mass) {
        super(name, mass);
    }
    public SwampSoil(final String name, final double mass, final double nitrogen,
                     final double waterRetention, final double solidpH, final double organicMatter,
                     final double waterLogging, final String typ) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter, typ);
        this.waterLogging = waterLogging;
    }
    // getter
    public double getWaterLogging() {
        return waterLogging;
    }
    // setter
    public void setWaterLogging(final double waterLogging) {
        this.waterLogging = waterLogging;
    }
    @Override
    public double soilQuality() {
        double score = (getNitrogen() * MagicNumbersDouble.ununu.getNumar()) + (getOrganicMatter()
                * MagicNumbersDouble.doidoi.getNumar())
                - (waterLogging * MagicNumbersInt.cinci.getNumar());
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersDouble.normalize.getNumar(), score));
        double finalResult = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return finalResult;
    }
    @Override
    public double probabilityAttack() {
        double score = waterLogging * MagicNumbersInt.zece.getNumar();
        return score;
    }
    @Override
    public boolean isForest() {
        return false;
    }
    @Override
    public boolean isSwamp() {
        return true;
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
        return false;
    }
}
