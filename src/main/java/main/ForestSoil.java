package main;

public class ForestSoil extends Soil {
    private double leafLitter;
    // constructori
    public ForestSoil(String name, double mass) {
        super(name, mass);
    }
    public ForestSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double leafLitter) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.leafLitter = leafLitter;
    }
    // getter
    public double getLeafLitter() {
        return leafLitter;
    }
    // setter
    public void setLeafLitter(double leafLitter) {
        this.leafLitter = leafLitter;
    }
    @Override
    public double soil_quality() {
        double score = (getNitrogen() * 1.2) + (getOrganicMatter() * 2) + (getWaterRetention() * 1.5) + (leafLitter * 0.3);
        double normalizeScore = Math.max(0, Math.min(100.0, score));
        double finalResult = Math.round(normalizeScore * 100.0) / 100.0;
        return finalResult;
    }
    @Override
    public double probability_attack() {
        double score = (getWaterRetention() * 0.6 + leafLitter * 0.4) / 80 * 100;
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
