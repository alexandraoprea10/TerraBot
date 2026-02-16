package main;

import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;

public class Water extends Entity {
    private String type;
    private double salinity;
    private double pH;
    private double purity;
    private double turbidity;
    private double contaminantIndex;
    private boolean isFrozen;
    private int momentScanare;
    // constructori
    public Water(final String name, final double mass) {
        super(name, mass, 0, " ");
        this.salinity = 0.0;
        this.purity = 0.0;
        this.turbidity = 0.0;
        this.contaminantIndex = 0.0;
        this.pH = 0.0;
        this.isFrozen = false;
        this.momentScanare = -1;
    }
    public Water(final String name, final double mass, final String type,
                 final double salinity, final double pH, final double purity,
                 final double turbidity, final double contaminantIndex,
                 final boolean isFrozen, final String typ) {
        super(name, mass, 0, typ);
        this.type = type;
        this.salinity = salinity;
        this.purity = purity;
        this.turbidity = turbidity;
        this.contaminantIndex = contaminantIndex;
        this.pH = pH;
        this.isFrozen = isFrozen;
    }
    // getteri

    /**
     * Returneaza tipul.
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Returneaza salinitatea.
     * @return
     */
    public double getSalinity() {
        return salinity;
    }

    /**
     * Returneaza puritatea apei.
     * @return
     */
    public double getPurity() {
        return purity;
    }

    /**
     * Returneaza turbiditatea apei.
     * @return
     */
    public double getTurbidity() {
        return turbidity;
    }

    /**
     * Returneaza indexul de contaminare al apei.
     * @return
     */
    public double getContaminantIndex() {
        return contaminantIndex;
    }

    /**
     * Returneaza PH-ul apei.
     * @return
     */
    public double getPH() {
        return pH;
    }

    /**
     * Returneaza daca apa e inghetata sau nu.
     * @return
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * Returneaza momentul in care a fost scanata apa.
     * @return
     */
    public int getMomentScanare() {
        return momentScanare;
    }
    // setteri

    /**
     * Seteaza tipul apei.
     * @param type
     */
    public void setType(final String type) {
        this.type = type;
    }

    /**
     * Seteaza salinitatea apei.
     * @param salinity
     */
    public void setSalinity(final double salinity) {
        this.salinity = salinity;
    }

    /**
     * Seteaza puritatea apei.
     * @param purity
     */
    public void setPurity(final double purity) {
        this.purity = purity;
    }

    /**
     * Seteaza turbiditatea apei.
     * @param turbidity
     */
    public void setTurbidity(final int turbidity) {
        this.turbidity = turbidity;
    }

    /**
     * Seteaza indexul de contaminare.
     * @param contaminantIndex
     */
    public void setContaminantIndex(final double contaminantIndex) {
        this.contaminantIndex = contaminantIndex;
    }

    /**
     * Seteaza PH-ul apei.
     * @param ph
     */
    public void setPh(final double ph) {
        this.pH = ph;
    }

    /**
     * Seteaza daca apa e inghetata sau nu.
     * @param isFrozen
     */
    public void setIsFrozen(final boolean isFrozen) {
        this.isFrozen = isFrozen;
    }

    /**
     * Seteaza momentul in care a fost scanata apa.
     * @param momentScanare
     */
    public void setMomentScanare(final int momentScanare) {
        this.momentScanare = momentScanare;
    }

    /**
     * Calculez calitatea apei.
     * @return
     */
    public double waterQuality() {
        double purityScore = purity / MagicNumbersInt.suta.getNumar();
        double pHScore = MagicNumbersInt.unu.getNumar() - Math.abs(pH
                - MagicNumbersDouble.saptecinci.getNumar())
                / MagicNumbersDouble.saptecinci.getNumar();
        double salinityScore = MagicNumbersInt.unu.getNumar()
                - (salinity / MagicNumbersInt.treicincizero.getNumar());
        double turbidityScore = MagicNumbersInt.unu.getNumar()
                - (turbidity / MagicNumbersInt.suta.getNumar());
        double contaminantScore = MagicNumbersInt.unu.getNumar()
                - (contaminantIndex / MagicNumbersInt.suta.getNumar());
        double frozenScore;
        if (!isFrozen) {
            frozenScore = 0.0;
        } else {
            frozenScore = MagicNumbersDouble.unu.getNumar();
        }
        double waterQ = (MagicNumbersDouble.temperatura.getNumar()
                * purityScore + MagicNumbersDouble.zerodoi.getNumar()
                * pHScore + MagicNumbersDouble.zerounucinci.getNumar()
                * salinityScore + MagicNumbersDouble.zerounu.getNumar()
                * turbidityScore + MagicNumbersDouble.zerounucinci.getNumar()
                * contaminantScore + MagicNumbersDouble.zerodoi.getNumar()
                * frozenScore) * MagicNumbersDouble.normalize.getNumar();
        return waterQ;
    }

    /**
     * Calculez ce calitate are apa.
     * @param quality
     * @return
     */
    public String resultWater(final double quality) {
        if (quality < MagicNumbersInt.patruzero.getNumar()) {
            return "Poor";
        } else if ((quality >= MagicNumbersInt.patruzero.getNumar())
                && (quality <= MagicNumbersInt.saptezero.getNumar())) {
            return "Moderate";
        }
        return "Good";
    }
    @Override
    public final boolean isPlant() {
        return false;
    }
    @Override
    public final boolean isAnimal() {
        return false;
    }
    @Override
    public final boolean isWater() {
        return true;
    }
    @Override
    public final boolean isSoil() {
        return false;
    }
    @Override
    public final boolean isAir() {
        return false;
    }
}
