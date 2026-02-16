package main.helpers;

import main.Entity;
import main.air.Air;
import main.animal.Animal;
import main.plant.Plant;
import main.soil.Soil;

import java.util.List;

public final class CalculateHelper {
    private CalculateHelper() {

    }
    /**
     * Calculeaza probabilitatea
     * @param entities lista de entitati
     * @return probabilitatea gasita
     */
    public static double calculateProbability(final List<Entity> entities) {
        double calculProbabilitate = 0.0;
        for (int k = 0; k < entities.size(); k++) {
            Entity entity = entities.get(k);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                calculProbabilitate = calculProbabilitate + aer.airToxicity();
            } else if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                calculProbabilitate = calculProbabilitate + sol.probabilityAttack();
            } else if (entity.isPlant()) {
                Plant plantuta = (Plant) entity;
                if (!plantuta.getMaturityLevel().equals("dead")
                        || !(plantuta.getMaturityLevel().equals("out"))) {
                    calculProbabilitate = calculProbabilitate + plantuta.probabilityAttack();
                }
            } else if (entity.isAnimal()) {
                Animal animalut = (Animal) entity;
                if (!animalut.getType().equals("out")) {
                    calculProbabilitate = calculProbabilitate + animalut.probabilityAttack();
                }
            }
        }
        return calculProbabilitate;
    }

    /**
     * Calculeaza numarul de obiecte
     * @param entities lista de entitati
     * @return numarul de obiecte
     */
    public static int calculateObjects(final List<Entity> entities) {
        int objects = 0;
        for (int k = 0; k < entities.size(); k++) {
            Entity entity = entities.get(k);
            if (entity.isAir()) {
                objects++;
            } else if (entity.isSoil()) {
                objects++;
            } else if (entity.isPlant()) {
                Plant plantuta = (Plant) entity;
                if (!plantuta.getMaturityLevel().equals("dead")
                        || !(plantuta.getMaturityLevel().equals("out"))) {
                    objects++;
                }
            } else if (entity.isAnimal()) {
                Animal animalut = (Animal) entity;
                if (!animalut.getType().equals("out")) {
                    objects++;
                }
            }
        }
        return objects;
    }

    /**
     * Calculeaza numarul de obiecte + niste conditii.
     * @param entities lista de entitati
     * @return numarul de obiecte
     */
    public static int calculateObject(final List<Entity> entities) {
        int contor = 0;
        for (int p = 0; p < entities.size(); p++) {
            if (entities.get(p).isWater()) {
                contor++;
            }
            if (entities.get(p).isPlant()) {
                Plant pl =  (Plant) entities.get(p);
                if (!pl.getMaturityLevel().equals("out")) {
                    contor++;
                }
            }
            if (entities.get(p).isAnimal()) {
                Animal anim =  (Animal) entities.get(p);
                // entityNode.put("animal", anim.getType());
                if (!anim.getType().equals("out")) {
                    contor++;
                }
            }
        }
        return contor;
    }
    /**
     * Calculeaza probabilitatea de atac pentru fiecare patratica din matrice.
     * @param dimension dimensiunea matricei
     * @param mat matricea-teritoriul
     */
    public static void calculateAttack(final int dimension,
                                       final List<Entity>[][] mat) {
        for (int a = 0; a < dimension; a++) {
            for (int b = 0; b < dimension; b++) {
                List<Entity> entities = mat[b][a];
                double calculProbabilitate =
                        CalculateHelper.calculateProbability(entities);
                int obiecte = CalculateHelper.calculateObjects(entities);
                double mean;
                if (obiecte == 0) {
                    mean = -1;
                } else {
                    mean = Math.abs(calculProbabilitate / obiecte);
                }
                int result = (int) Math.round(mean);
                for (int p = 0; p < entities.size(); p++) {
                    Entity entity = entities.get(p);
                    entity.setAttack(result);
                }
            }
        }
    }
}
