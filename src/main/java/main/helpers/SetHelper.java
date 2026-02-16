package main.helpers;

import main.Entity;
import main.animal.Animal;

import java.util.List;

import static main.helpers.ExistingHelper.verificaMoarteaPlantuta;


public final class SetHelper {
    private SetHelper() {

    }
    /**
     * Pune in entitate subiectul(adauga in lista de subiecte).
     * @param dimension dimensiunea matricei
     * @param mat matricea
     * @param fact tema
     * @param subj subiectul pe care il adaug
     * @return daca s-a efectuat cu succes sau nu
     */
    public static int setSubjects(final int dimension, final List<Entity>[][] mat,
                                  final String fact, final String subj) {
        int mergeSubj = 0;
        for (int a = 0; a < dimension; a++) {
            for (int b = 0; b < dimension; b++) {
                List<Entity> entities = mat[a][b];
                for (int p = 0; p < entities.size(); p++) {
                    Entity entity = entities.get(p);
                    if (entity.getisScanned()
                            && entity.getName().equals(fact)) {
                        mergeSubj = 1;
                        entity.setSubject(subj);
                    }
                }
            }
        }
        return mergeSubj;
    }
    /**
     * Verifica daca animalul s-a mutat si ii reface valoarea.
     * Elimina planta daca e moarta/ mancata.
     * @param dimension dimensiunea matricei
     * @param mat matricea
     * @param inceputIteratieAnimal cand e scanat animalul
     */
    public static void setAnimalAndCheckPlant(final int dimension,
                                               final List<Entity>[][] mat,
                                               final  int inceputIteratieAnimal) {
        for (int a = 0; a < dimension; a++) {
            for (int b = 0; b < dimension; b++) {
                List<Entity> entities = mat[a][b];
                for (int p = 0; p < entities.size(); p++) {
                    Entity entity = entities.get(p);
                    if (entity.isAnimal() && inceputIteratieAnimal != -1) {
                        Animal an =  (Animal) entity;
                        if (an.isMutat()) {
                            an.setMutat(false);
                        }
                    }
                    verificaMoarteaPlantuta(entities);
                }
            }
        }
    }
}
