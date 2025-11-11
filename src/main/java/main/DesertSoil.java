package main;

public class DesertSoil extends Soil {
    private double salinity;
    // constructori
    public DesertSoil(String name, double mass) {
        super(name, mass);
    }
    public DesertSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double salinity) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.salinity = salinity;
    }
    // getter
    public double getSalinity() {
        return salinity;
    }
    // setter
    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }
    @Override
    public double soil_quality() {
        double score = (getNitrogen() * 0.5) + (getWaterRetention() * 0.3) - (salinity * 2);
        double normalizeScore = Math.max(0, Math.min(100.0, score));
        double finalResult = Math.round(normalizeScore * 100.0) / 100.0;
        return finalResult;
    }
    @Override
    public double probability_attack() {
        double score = (100 - getWaterRetention() + salinity) / 100 * 100;
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
