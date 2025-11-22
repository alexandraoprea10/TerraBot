package main;

public final class Polar extends Air {
    private double iceCrystalConcentration;
    private double windSpeed;
    // constructori
    public Polar(final String nume, final double mass) {
        super(nume, mass);
        this.iceCrystalConcentration = 0.0;
    }
    public Polar(final String nume, final double mass, final double humidity,
                 final double temperature, final double oxygenLevel,
                 final double iceCrystalConcentration, final String typ) {
        super(nume, mass,  humidity, temperature, oxygenLevel, typ);
        this.iceCrystalConcentration = iceCrystalConcentration;
    }
    // getter
    public double getIceCrystalConcentration() {
        return iceCrystalConcentration;
    }
    public double getWindSpeed() {
        return windSpeed;
    }
    // setter
    public void setIceCrystalConcentration(final double iceCrystalConcentration) {
        this.iceCrystalConcentration = iceCrystalConcentration;
    }
    public void setWindSpeed(final double windSpeed) {
        this.windSpeed = windSpeed;
    }
    @Override
    public double airQuality() {
        double score = ((getOxygenLevel() * MagicNumbersInt.doi.getNumar())
                + (MagicNumbersInt.suta.getNumar() - Math.abs(getTemperature()))
                - (iceCrystalConcentration * MagicNumbersDouble.half.getNumar()));
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersInt.suta.getNumar(), score));
        double result = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return result;
    }
    @Override
    public double airToxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.osutapatru2.getNumar());
        double result = Math.round(toxicityAQ
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        if (result < 0)
            return 0.0;
        return result;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.osutapatru2.getNumar());
        if (toxicityAQ > (MagicNumbersDouble.oxygenMoss.getNumar()
                * MagicNumbersDouble.osutapatru2.getNumar())) {
            return "toxic";
        }
        return "not toxic";
    }
    @Override
    public String resultEvent() {
        return "polarStorm";
    }
    @Override
    public double updateAirQuality() {
        double result = getAirQuality()
                - (windSpeed * MagicNumbersDouble.zerodoi.getNumar());
        return result;
    }
    @Override
    public boolean isTropical() {
        return false;
    }
    @Override
    public boolean isPolar() {
        return true;
    }
    @Override
    public boolean isTemperate() {
        return false;
    }
    @Override
    public boolean isDesert() {
        return false;
    }
    @Override
    public boolean isMountain() {
        return false;
    }
}
