package main.Soil;

import main.MagicNumbersDouble;
import main.MagicNumbersInt;

public final class ForestSoil extends Soil {
    private double leafLitter;
    // constructori
    public ForestSoil(final String name, final double mass) {
        super(name, mass);
    }
    public ForestSoil(final String name, final double mass, final double nitrogen,
                      final double waterRetention, final double solidpH,
                      final double organicMatter, final double leafLitter, final String typ) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter, typ);
        this.leafLitter = leafLitter;
    }
    // getter
    public double getLeafLitter() {
        return leafLitter;
    }
    // setter
    public void setLeafLitter(final double leafLitter) {
        this.leafLitter = leafLitter;
    }
    @Override
    public double soilQuality() {
        double score = (getNitrogen() * MagicNumbersDouble.nitrogenForest.getNumar())
                + (getOrganicMatter() * MagicNumbersInt.doi.getNumar())
                + (getWaterRetention() * MagicNumbersDouble.waterRetForest.getNumar())
                + (leafLitter * MagicNumbersDouble.leafliterSoil.getNumar());
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersDouble.normalize.getNumar(), score));
        double finalResult = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return finalResult;
    }
    @Override
    public double probabilityAttack() {
        double score = (getWaterRetention() * MagicNumbersDouble.waterRetFor2.getNumar()
                + leafLitter * MagicNumbersDouble.leafLiter2.getNumar())
                / MagicNumbersInt.optzeci.getNumar()
                * MagicNumbersInt.suta.getNumar();
        return score;
    }
    @Override
    public boolean isForest() {
        return true;
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
        return false;
    }
}
