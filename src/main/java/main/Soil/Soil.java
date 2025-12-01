package main.Soil;

import main.Entity;
import main.MagicNumbersInt;

public abstract class Soil extends Entity {
    private double nitrogen;
    private double waterRetention;
    private double soilPh;
    private double organicMatter;
    // constructori
    public Soil(final String name, final double mass) {
        super(name, mass, 0, " ");
        this.nitrogen = 0;
        this.waterRetention = 0;
        this.soilPh = 0;
    }
    public Soil(final String name, final double mass,
                final double nitrogen, final double waterRetention,
                final double soilPh, final double organicMatter, final String typ) {
        super(name, mass, 0, typ);
        this.nitrogen = nitrogen;
        this.waterRetention = waterRetention;
        this.soilPh = soilPh;
        this.organicMatter = organicMatter;
    }
    // getteri

    /**
     * Returneaza nitrogenul pentru sol.
     * @return
     */
    public double getNitrogen() {
        return nitrogen;
    }

    /**
     * Returneaza waterretention
     * @return
     */
    public double getWaterRetention() {
        return waterRetention;
    }

    /**
     * Returneaza Ph-ul solului.
     * @return
     */
    public double getSoilPh() {
        return soilPh;
    }

    /**
     * Returneaza organic matter pentru sol.
     * @return
     */
    public double getOrganicMatter() {
        return organicMatter;
    }
    // setteri

    /**
     * Seteaza nitrogenul pentru sol.
     * @param nitro
     */
    public void setNitrogen(final double nitro) {
        this.nitrogen = nitro;
    }

    /**
     * Seteaza waterRet pentru sol.
     * @param waterRet
     */
    public void setWaterRetention(final double waterRet) {
        this.waterRetention = waterRet;
    }

    /**
     * Seteaza soilPh pentru sol.
     * @param soilPH
     */
    public void setSoilPh(final double soilPH) {
        this.soilPh = soilPH;
    }

    /**
     * Seteaza organic matter pentru sol.
     * @param organicMat
     */
    public void setOrganicMatter(final double organicMat) {
        this.organicMatter = organicMat;
    }

    /**
     * Calculeaza calitatea solului.
     * @return
     */
    public abstract double soilQuality();

    /**
     * Calculeaza probabilitatea de atac
     * @return
     */
    public abstract double probabilityAttack();

    /**
     * Retruneaza ce tip de calitate are solul.
     * @param quality
     * @return
     */
    public String resultSoil(final double quality) {
        if (quality < MagicNumbersInt.patruzero.getNumar()) {
            return "poor";
        } else if ((quality >= MagicNumbersInt.patruzero.getNumar())
                && (quality <= MagicNumbersInt.saptezero.getNumar())) {
            return "moderate";
        }
        return "good";
    }

    /**
     * Returnez false ca e soil.
     * @return
     */
    @Override
    public final boolean isPlant() {
        return false;
    }

    /**
     * Returnez false ca e soil.
     * @return
     */
    @Override
    public final boolean isAnimal() {
        return false;
    }

    /**
     * Returnez false ca e soil.
     * @return
     */
    @Override
    public final boolean isWater() {
        return false;
    }

    /**
     * Returnez true ca entitatea e sol.
     * @return
     */
    @Override
    public final boolean isSoil() {
        return true;
    }

    /**
     * Returnez false ca e soil.
     * @return
     */
    @Override
    public final boolean isAir() {
        return false;
    }

    /**
     * Verific daca e forest.
     * @return
     */
    public abstract boolean isForest();

    /**
     * Verific daca e swamp.
     * @return
     */
    public abstract boolean isSwamp();

    /**
     * Verific daca e desert.
     * @return
     */
    public abstract boolean isDesertSoil();

    /**
     * Verific daca e Grassland.
     * @return
     */
    public abstract boolean isGrassland();

    /**
     * Verific daca e tundra.
     * @return
     */
    public abstract boolean isTundra();
}
