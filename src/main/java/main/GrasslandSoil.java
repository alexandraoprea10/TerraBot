package main;

public final class GrasslandSoil extends Soil {
    private double rootDensity;
    // constructori
    public GrasslandSoil(final String name, final double mass) {
        super(name, mass);
    }
    public GrasslandSoil(final String name, final double mass, final double nitrogen, final double waterRetention, final double solidpH, final double organicMatter, final double rootDensity) {
        super(name, mass, nitrogen, waterRetention, solidpH, organicMatter);
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
    public double soil_quality() {
        double score = (getNitrogen() * 1.3) + (getOrganicMatter() * 1.5) + (rootDensity * 0.8);
        double normalizeScore = Math.max(0, Math.min(100.0, score));
        double finalResult = Math.round(normalizeScore * 100.0) / 100.0;
        return finalResult;
    }
    @Override
    public double probability_attack() {
        double score = ((50 - rootDensity) + getWaterRetention() * 0.5) / 75 * 100;
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
