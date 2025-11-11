package main;

public class SwampSoil extends Soil {
    private double waterLogging;
    // constructor
    public SwampSoil(String name, double mass) {
        super(name, mass);
    }
    public SwampSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double waterLogging) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.waterLogging = waterLogging;
    }
    // getter
    public double getWaterLogging() {
        return waterLogging;
    }
    // setter
    public void setWaterLogging(double waterLogging) {
        this.waterLogging = waterLogging;
    }
    @Override
    public double soil_quality() {
        double score = (getNitrogen() * 1.1) + (getOrganicMatter() * 2.2) - (waterLogging * 5);
        double normalizeScore = Math.max(0, Math.min(100.0, score));
        double finalResult = Math.round(normalizeScore * 100.0) / 100.0;
        return finalResult;
    }
    @Override
    public double probability_attack() {
        double score = waterLogging * 10;
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
