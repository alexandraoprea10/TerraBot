package main.Soil;

import main.MagicNumbersDouble;
import main.MagicNumbersInt;

public final class DesertSoil extends Soil {
    private double salinity;
    // constructori
    public DesertSoil(final String name, final double mass) {
        super(name, mass);
    }
    public DesertSoil(final String name, final double mass, final double nitrogen,
                      final double waterRetention, final double solidpH,
                      final double organicMatter, final double salinity, final String typ) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter, typ);
        this.salinity = salinity;
    }
    // getter
    public double getSalinity() {
        return salinity;
    }
    // setter
    public void setSalinity(final double salinity) {
        this.salinity = salinity;
    }
    @Override
    public double soilQuality() {
        double score = (getNitrogen() * MagicNumbersDouble.half.getNumar())
                + (getWaterRetention() * MagicNumbersDouble.waterRetentionDesert.getNumar())
                - (salinity * MagicNumbersInt.doi.getNumar());
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersDouble.normalize.getNumar(), score));
        double finalResult = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return finalResult;
    }
    @Override
    public double probabilityAttack() {
        double score = (MagicNumbersInt.suta.getNumar()
                - getWaterRetention() + salinity)
                / MagicNumbersInt.suta.getNumar()
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
        return true;
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
