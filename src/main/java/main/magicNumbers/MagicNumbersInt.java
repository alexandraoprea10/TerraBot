package main.magicNumbers;

public enum MagicNumbersInt {
    // GrassLand Soil
    jumatate(50),
    trei_sferturi(75),
    suta(100),
    oxygen(2),
    unu(1),
    doi(2),
    optzeci(80),
    mie(1000),
    zece(10),
    cinci(5),
    treicincizero(350),
    patruzero(40),
    saptezero(70),
    minusdoi(-2),
    maximum(9999),
    sapte(7),
    minustrei(-3);
    private final int numar;
    MagicNumbersInt(final int numar) {
        this.numar = numar;
    }
    public int getNumar() {
        return numar;
    }

}
