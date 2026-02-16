package main.helpers;

import main.Entity;
import main.Robot;
import main.magicNumbers.MagicNumbersInt;

import java.util.List;

public final class MovingRobotHelper {
    private MovingRobotHelper() {

    }
    /**
     * Aici se muta robotelul pe una din patratelele vecine.
     * @param robotel robotul
     * @param mat matrricea
     * @param dimension dimensiunea matricei
     */
    public static void moveRobotel(final Robot robotel,
                                   final List<Entity>[][] mat,
                                   final int dimension) {
        int posStangaI = robotel.getPozX();
        int posStangaJ = robotel.getPozY() - 1;

        int posSusI = robotel.getPozX() - 1;
        int posSusJ = robotel.getPozY();

        int posDreaptaI = robotel.getPozX();
        int posDreaptaJ = robotel.getPozY() + 1;

        int posJosI = robotel.getPozX() + 1;
        int posJosJ = robotel.getPozY();

        int scorStanga = MagicNumbersInt.minusdoi.getNumar();
        int scorDreapta = MagicNumbersInt.minusdoi.getNumar();
        int scorJos = MagicNumbersInt.minusdoi.getNumar();
        int scorSus = MagicNumbersInt.minusdoi.getNumar();
        int pozitieMutareI = 0;
        int pozitieMutareJ = 0;
        int scorMinim = MagicNumbersInt.maximum.getNumar();
        // verific pentru STANGA
        if (posJosI >= 0
                && posJosJ >= 0 && posJosI < dimension
                && posJosJ < dimension) {
            // iau scorul
            List<Entity> entities = mat[posJosI][posJosJ];
            // ca sa nu mai am asa multe warninguri
            scorJos = entities.getFirst().getAttack();
        }
        if (posDreaptaI >= 0
                && posDreaptaJ >= 0 && posDreaptaI < dimension
                && posDreaptaJ < dimension) {
            // iau scorul
            List<Entity> entities = mat[posDreaptaI][posDreaptaJ];
            // ca sa nu mai am asa multe warninguri
            scorDreapta = entities.getFirst().getAttack();
            // node.put("dreapta", scorDreapta);
        }
        if (posSusI >= 0
                && posSusJ >= 0 && posSusI < dimension
                && posSusJ < dimension) {
            // iau scorul
            List<Entity> entities = mat[posSusI][posSusJ];
            // ca sa nu mai am asa multe warninguri
            scorSus = entities.getFirst().getAttack();
            // node.put("sus", scorSus);
        }
        if (posStangaI >= 0
                && posStangaJ >= 0 && posStangaI < dimension
                && posStangaJ < dimension) {
            List<Entity> entities = mat[posStangaI][posStangaJ];
            // ca sa nu mai am asa multe warninguri
            scorStanga = entities.getFirst().getAttack();
            // node.put("stanga", scorStanga);
        }
        if (scorDreapta >= 0 && scorDreapta < scorMinim) {
            scorMinim = scorDreapta;
            pozitieMutareI = posDreaptaI;
            pozitieMutareJ = posDreaptaJ;
        }
        if (scorJos >= 0 && scorJos < scorMinim) {
            scorMinim = scorJos;
            pozitieMutareI = posJosI;
            pozitieMutareJ = posJosJ;
        }
        if (scorStanga >= 0 && scorStanga < scorMinim) {
            scorMinim = scorStanga;
            pozitieMutareI = posStangaI;
            pozitieMutareJ = posStangaJ;
        }
        if (scorSus >= 0 && scorSus < scorMinim) {
            scorMinim = scorSus;
            pozitieMutareI = posSusI;
            pozitieMutareJ = posSusJ;
        }
        if (robotel.getBattery() - scorMinim >= 0) {
            robotel.setPozX(pozitieMutareI);
            robotel.setPozY(pozitieMutareJ);
        }
        robotel.setBattery(robotel.getBattery() - scorMinim);
    }

}
