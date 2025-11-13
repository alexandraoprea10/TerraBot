package main;

public enum MagicNumbersDouble {
    //GrassLand Soil
    nitrogen(1.3),
    organicMatter(1.5),
    rootDensity(0.8),
    normalize(100.0),
    half(0.5),
    // Algae
    probAlgae(20.0),
    // Temperate Air
    humidity(0.7),
    pollen(0.1),
    airTemperate(84.0),
    toxic(0.8),
    penalty(15.0),
    treizeci(30.0),
    zece(10.0),
    temperatura(0.3),
    dustParticles(0.2),
    // DesertAir
    airDesert(65.0),
    waterRetentionDesert(0.3),
    // detrit
    atacDetr(90.0),
    sase(6.0),
    nouzeci(90.0),
    nitrogenForest(1.2),
    waterRetForest(1.5),
    leafliterSoil(0.3),
    waterRetFor2(0.6),
    leafLiter2(0.4),
    optzeci(80.0),
    saizeci(60.0),
    optcinci(85.0),
    oxygenMoss(0.8),
    patruzeci(40.0),
    sapteopt(78.0),
    zerounu(0.1),
    zerodoi(0.2),
    zerosapte(0.7),
    zeropatru(0.4),
    osutapatru2(142.0),
    ununu(1.1),
    doidoi(2.2),
    zerozerounu(0.01),
    optdoi(82.0),
    unucinci(1.5),
    zerounucinci(0.15),
    saptecinci(7.5),
    unu(1.0),
    zerozeroopt(0.08),
    zerotrei(0.3);

    private final double numar;
    MagicNumbersDouble(final double numar) {
        this.numar = numar;
    }
    public double getNumar() {
        return numar;
    }
}
