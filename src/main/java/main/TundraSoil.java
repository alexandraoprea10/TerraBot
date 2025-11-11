package main;

public class TundraSoil extends Soil {
    private double permafrostDepth;
    // constructori
    public TundraSoil(String name, double mass) {
        super(name, mass);
    }
    public TundraSoil(String name, double mass, double nitrogen, double waterRetention, double solidpH, double organicMatter, double permafrostDepth) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
        this.permafrostDepth = permafrostDepth;
    }
    // getter
    public double getPermafrostDepth() {
        return permafrostDepth;
    }
    // setter
    public void setPermafrostDepth(double permafrostDepth) {
        this.permafrostDepth = permafrostDepth;
    }
    @Override
    public double soil_quality() {
        double score = (getNitrogen() * 0.7) + (getOrganicMatter() * 0.5) - (permafrostDepth * 1.5);
        double normalizeScore = Math.max(0, Math.min(100.0, score));
        double finalResult = Math.round(normalizeScore * 100.0) / 100.0;
        return finalResult;
    }
    @Override
    public double probability_attack() {
        double score =  (50 - permafrostDepth) / 50 * 100;
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
