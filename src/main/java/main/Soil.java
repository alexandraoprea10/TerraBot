package main;

public abstract class Soil extends Entity{
    private double nitrogen;
    private double waterRetention;
    private double soilPh;
    private double organicMatter;
    // constructori
    public Soil(String name, double mass) {
        super(name, mass, 0);
        this.nitrogen = 0;
        this.waterRetention = 0;
        this.soilPh = 0;
    }
    public Soil(String name, double mass, double nitrogen, double waterRetention, double soilPh, double organicMatter) {
        super(name, mass, 0);
        this.nitrogen = nitrogen;
        this.waterRetention = waterRetention;
        this.soilPh = soilPh;
        this.organicMatter = organicMatter;
    }
    // getteri
    public double getNitrogen() {
        return nitrogen;
    }
    public double getWaterRetention() {
        return waterRetention;
    }
    public double getSoilPh() {
        return soilPh;
    }
    public double getOrganicMatter() {
        return organicMatter;
    }
    // setteri
    public void setNitrogen(double nitrogen) {
        this.nitrogen = nitrogen;
    }
    public void setWaterRetention(double waterRetention) {
        this.waterRetention = waterRetention;
    }
    public void setSoilPh(double soilPh) {
        this.soilPh = soilPh;
    }
    public void setOrganicMatter(double organicMatter) {
        this.organicMatter = organicMatter;
    }
    public abstract double soil_quality();
    public abstract double probability_attack();
    public String result_soil(double quality) {
        if (quality < 40)
            return "poor";
        else if ((quality >= 40) && (quality <= 70))
            return "moderate";
        return "good";
    }
    @Override
    public boolean isPlant() {
        return false;
    }
    @Override
    public boolean isAnimal() {
        return false;
    }
    @Override
    public boolean isWater() {
        return false;
    }
    @Override
    public boolean isSoil() {
        return true;
    }
    @Override
    public boolean isAir() {
        return false;
    }
    public abstract boolean isForest();
    public abstract boolean isSwamp();
    public abstract boolean isDesertSoil();
    public abstract boolean isGrassland();
    public abstract boolean isTundra();
}
