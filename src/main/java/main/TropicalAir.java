package main;

public final class TropicalAir extends Air {
    private double co2Level;
    private double rainfall;
    // constructori
    public TropicalAir(final String nume, final double mass) {
        super(nume, mass);
        this.co2Level = 0.0;
    }
    public TropicalAir(final String nume, final double mass, final double humidity,
                       final double temperature, final double oxygenLevel, final double co2Level) {
        super(nume, mass,  humidity, temperature, oxygenLevel);
        this.co2Level = co2Level;
        this.rainfall = 0.0;
    }
    // getter
    public double getCo2Level() {
        return co2Level;
    }
    public double getRainfall() {
        return rainfall;
    }
    // setter
    public void setCo2Level(final double co2Level) {
        this.co2Level = co2Level;
    }
    public void setRainfall(final double rainfall) {
        this.rainfall = rainfall;
    }
    @Override
    public double airQuality() {
        double score = ((getOxygenLevel()
                * MagicNumbersInt.doi.getNumar())
                + (getHumidity() * MagicNumbersDouble.half.getNumar())
                - (co2Level * MagicNumbersDouble.zerozerounu.getNumar()));
        double normalizeScore = Math.max(0, Math.min(MagicNumbersInt.suta.getNumar(), score));
        double result = Math.round(normalizeScore
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return result;
    }
    @Override
    public double airToxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.optdoi.getNumar());
        double result = Math.round(toxicityAQ
                * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        double normalizeScore = Math.max(0,
                Math.min(MagicNumbersInt.suta.getNumar(), result));
        double res = Math.round(normalizeScore * MagicNumbersDouble.normalize.getNumar())
                / MagicNumbersDouble.normalize.getNumar();
        return res;
    }
    @Override
    public String toxicity() {
        double toxicityAQ = MagicNumbersInt.suta.getNumar()
                * (MagicNumbersInt.unu.getNumar() - airQuality()
                / MagicNumbersDouble.optdoi.getNumar());
        if (toxicityAQ > (MagicNumbersDouble.oxygenMoss.getNumar()
                * MagicNumbersDouble.optdoi.getNumar())) {
            return "toxic";
        } else {
            return "not toxic";
        }
    }
    @Override
    public String resultEvent() {
        return "rainfall";
    }
    @Override
    public double updateAirQuality() {
        double result = getAirQuality() + (rainfall
                * MagicNumbersDouble.waterRetentionDesert.getNumar());
        return result;
    }
    @Override
    public boolean isTropical() {
        return true;
    }
    @Override
    public boolean isPolar() {
        return false;
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
