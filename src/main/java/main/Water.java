package main;

public class Water extends Entity{
    private String type;
    private double salinity;
    private double pH;
    private double purity;
    private double turbidity;
    private double contaminantIndex;
    private boolean isFrozen;
    private boolean isScanned;
    // constructori
    public Water(String name, double mass) {
        super(name, mass, 0);
        this.salinity = 0.0;
        this.purity = 0.0;
        this.turbidity = 0.0;
        this.contaminantIndex = 0.0;
        this.pH = 0.0;
        this.isFrozen = false;
    }
    public Water(String name, double mass, String type, double salinity, double pH, double purity, double turbidity, double contaminantIndex, boolean isFrozen) {
        super(name, mass, 0);
        this.type = type;
        this.salinity = salinity;
        this.purity = purity;
        this.turbidity = turbidity;
        this.contaminantIndex = contaminantIndex;
        this.pH = pH;
        this.isFrozen = isFrozen;
    }
    // getteri
    public String getType() {
        return type;
    }
    public double getSalinity() {
        return salinity;
    }
    public double getPurity() {
        return purity;
    }
    public double getTurbidity() {
        return turbidity;
    }
    public double getContaminantIndex() {
        return contaminantIndex;
    }
    public double getPH() {
        return pH;
    }
    public boolean isFrozen() {
        return isFrozen;
    }
    public boolean getisScanned() {
        return isScanned;
    }
    // setteri
    public void setType(String type) {
        this.type = type;
    }
    public void setSalinity(double salinity) {
        this.salinity = salinity;
    }
    public void setPurity(double purity) {
        this.purity = purity;
    }
    public void setTurbidity(int turbidity) {
        this.turbidity = turbidity;
    }
    public void setContaminantIndex(double contaminantIndex) {
        this.contaminantIndex = contaminantIndex;
    }
    public void setPH(double pH) {
        this.pH = pH;
    }
    public void setIsFrozen(boolean isFrozen) {
        this.isFrozen = isFrozen;
    }
    public void setIsScanned(boolean isScanned) {
        this.isScanned = isScanned;
    }
    public double water_quality() {
        double purity_score = purity / 100;
        double pH_score = 1 - Math.abs(pH - 7.5) / 7.5;
        double salinity_score = 1 - (salinity / 350);
        double turbidity_score = 1 - (turbidity / 100.0);
        double contaminant_score = 1 - (contaminantIndex / 100);
        double frozen_score;
        if (isFrozen)
            frozen_score = 0.0;
        else frozen_score = 1.0;
        double water_q = (0.3 * purity_score + 0.2 * pH_score + 0.15 * salinity_score + 0.1 * turbidity_score + 0.15 * contaminant_score + 0.2 * frozen_score) * 100.0;
        return water_q;
    }
    public String result_water(double quality) {
        if (quality < 40)
            return "Poor";
        else if ((quality >= 40) && (quality <= 70))
            return "Moderate";
        return "Good";
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
        return true;
    }
    @Override
    public boolean isSoil() {
        return false;
    }
    @Override
    public boolean isAir() {
        return false;
    }
}
