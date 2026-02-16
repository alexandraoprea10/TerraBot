package main.helpers;

import main.Entity;
import main.Water;
import main.animal.Animal;
import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;
import main.plant.Plant;

import java.util.List;

import static main.helpers.ExistingHelper.verificaMoarteaPlantuta;


public final class MovingAnimalHelper {
    private MovingAnimalHelper() {

    }
    /**
     * Muta animalul si seteaza mutatul animalului la true.
     * @param dimension dimensiunea matricei
     * @param inceputIteratieAnimal unde e aniamalul scanat
     * @param mat matricea
     * @param okInterAnimal contor daca a mancat
     * @param iteratii timestampul
     */
    public static void moveAnimal(final int dimension, final int inceputIteratieAnimal,
                                  final List<Entity>[][] mat,
                                  int okInterAnimal, final int iteratii) {
        for (int a = 0; a < dimension; a++) {
            for (int b = 0; b < dimension; b++) {
                List<Entity> entities = mat[a][b];
                for (int p = 0; p < entities.size(); p++) {
                    Entity entity = entities.get(p);
                    if (entity.isAnimal() && inceputIteratieAnimal != -1) {
                        Animal an =  (Animal) entity;
                        if (!an.isMutat()) {
                            okInterAnimal = whereisAnimalGoing(mat, (Animal) entity, iteratii,
                                    inceputIteratieAnimal, dimension,
                                    entities, a, b);
                            an.setMutat(true);
                        }
                    }
                    verificaMoarteaPlantuta(entities);
                }
            }
        }
    }
    /**
     * Cum se misca animalul, ce patratica alege, si ce alege sa faca(sa manance/bea).
     * @param mat matricea
     * @param animalul animalul de pe patratica
     * @param iteratii timestampul
     * @param inceputIteratieAnimal unde e scanat animalul
     * @param dimension dimensiunea matricei
     * @param entities lista de entitati
     * @return interanimal
     */
    public static int whereisAnimalGoing(final List<Entity>[][] mat, final Animal animalul,
                                         final int iteratii, final int inceputIteratieAnimal,
                                         final int dimension,
                                         final List<Entity> entities, final int a, final int b) {
        int okInterAnimal = 0;
        if (animalul != null && animalul.getisScanned()) {
            if (inceputIteratieAnimal < iteratii
                    && (iteratii - inceputIteratieAnimal)
                    % MagicNumbersInt.doi.getNumar() == 0) {
                int existaStanga = 1;
                int existaDreapta = 1;
                int existaSus = 1;
                int existaJos = 1;
                int posStangaI = a;
                int posStangaJ = b - 1;
                if (posStangaJ < 0 || posStangaJ >= dimension) {
                    existaStanga = 0;
                }
                int posSusI = a - 1;
                int posSusJ = b;
                if (posSusI < 0 || posSusI >= dimension) {
                    existaSus = 0;
                }
                int posDreaptaI = a;
                int posDreaptaJ = b + 1;
                if (posDreaptaJ < 0 || posDreaptaJ >= dimension) {
                    existaDreapta = 0;
                }
                int posJosI = a + 1;
                int posJosJ = b;
                if (posJosI < 0 || posJosI >= dimension) {
                    existaJos = 0;
                }
                Plant plantaStanga = null;
                Water apaStanga = null;
                Plant plantaDreapta = null;
                Water apaDreapta = null;
                Plant plantaJos = null;
                Water apaJos = null;
                Plant plantaSus = null;
                Water apaSus = null;
                if (existaStanga != 0) {
                    List<Entity> entitiesStanga = mat[posStangaI][posStangaJ];
                    apaStanga = ReturnHelper.returnWater(entitiesStanga);
                    plantaStanga = ReturnHelper.returnPlant(entitiesStanga);
                }
                if (existaDreapta != 0) {
                    List<Entity> entitiesDreapta = mat[posDreaptaI][posDreaptaJ];
                    apaDreapta = ReturnHelper.returnWater(entitiesDreapta);
                    plantaDreapta = ReturnHelper.returnPlant(entitiesDreapta);
                }
                if (existaJos != 0) {
                    List<Entity> entitiesJos = mat[posJosI][posJosJ];
                    apaJos = ReturnHelper.returnWater(entitiesJos);
                    plantaJos = ReturnHelper.returnPlant(entitiesJos);
                }
                if (existaSus != 0) {
                    List<Entity> entitiesSus = mat[posSusI][posSusJ];
                    apaSus = ReturnHelper.returnWater(entitiesSus);
                    plantaSus = ReturnHelper.returnPlant(entitiesSus);
                }
                // System.out.println("in stanga exista animal?", apaStanga);
                double calitateApa = 0.0;
                int pozX = -1, pozY = -1;
                if (apaJos != null && plantaJos != null
                        && !plantaJos.getMaturityLevel().equals("dead")
                        && plantaJos.getisScanned()) {
                    if (apaJos.waterQuality() > calitateApa) {
                        calitateApa = apaJos.waterQuality();
                        pozX = posJosI;
                        pozY = posJosJ;
                    }
                }
                if (apaDreapta != null && plantaDreapta != null
                        && !plantaDreapta.getMaturityLevel().equals("dead")
                        && plantaDreapta.getisScanned()) {
                    if (apaDreapta.waterQuality() > calitateApa) {
                        calitateApa = apaDreapta.waterQuality();
                        pozX = posDreaptaI;
                        pozY = posDreaptaJ;
                    }
                }
                if (apaSus != null && plantaSus != null
                        && !plantaSus.getMaturityLevel().equals("dead")
                        &&  plantaSus.getisScanned()) {
                    if (apaSus.waterQuality() > calitateApa) {
                        calitateApa = apaSus.waterQuality();
                        pozX = posSusI;
                        pozY = posSusJ;
                    }
                }
                if (apaStanga != null && plantaStanga != null
                        && !plantaStanga.getMaturityLevel().equals("dead")
                        &&  plantaStanga.getisScanned()) {
                    if (apaStanga.waterQuality() > calitateApa) {
                        calitateApa = apaStanga.waterQuality();
                        pozX = posStangaI;
                        pozY = posStangaJ;
                    }
                }
                if (pozX == -1) {
                    if (apaJos != null && apaJos.getisScanned()
                            && plantaJos != null
                            && !plantaJos.getMaturityLevel().equals("dead")
                            && plantaJos.getisScanned()) {
                        if (apaJos.waterQuality() > calitateApa) {
                            calitateApa = apaJos.waterQuality();
                            pozX = posJosI;
                            pozY = posJosJ;
                        }
                    }
                    if (apaDreapta != null && apaDreapta.getisScanned()
                            && plantaDreapta != null
                            && !plantaDreapta.getMaturityLevel().equals("dead")
                            && plantaDreapta.getisScanned()) {
                        if (apaDreapta.waterQuality() > calitateApa) {
                            calitateApa = apaDreapta.waterQuality();
                            pozX = posDreaptaI;
                            pozY = posDreaptaJ;
                        }
                    }
                    if (apaSus != null && apaSus.getisScanned()
                            && plantaSus != null
                            && !plantaSus.getMaturityLevel().equals("dead")
                            && plantaSus.getisScanned()) {
                        if (apaSus.waterQuality() > calitateApa) {
                            calitateApa = apaSus.waterQuality();
                            pozX = posSusI;
                            pozY = posSusJ;
                        }
                    }
                    if (apaStanga != null && plantaStanga != null
                            && !plantaStanga.getMaturityLevel().equals("dead")
                            && plantaStanga.getisScanned()) {
                        if (apaStanga.waterQuality() > calitateApa) {
                            calitateApa = apaStanga.waterQuality();
                            pozX = posStangaI;
                            pozY = posStangaJ;
                        }
                    }
                    if (pozX == -1) {
                        if ((apaJos != null && apaJos.getisScanned())
                                || (plantaJos != null
                                && !plantaJos.getMaturityLevel().equals("dead")
                                && plantaJos.getisScanned())) {
                            if (plantaJos != null) {
                                pozX = posJosI;
                                pozY = posJosJ;
                            }
                        } else if ((apaDreapta != null && apaDreapta.getisScanned())
                                || (plantaDreapta != null
                                && !plantaDreapta.getMaturityLevel().equals("dead")
                                && plantaDreapta.getisScanned())) {
                            if (plantaDreapta != null) {
                                pozX = posDreaptaI;
                                pozY = posDreaptaJ;
                            }
                        } else if ((apaSus != null && apaSus.getisScanned())
                                || (plantaSus != null
                                && !plantaSus.getMaturityLevel().equals("dead")
                                && plantaSus.getisScanned())) {
                            if (plantaSus != null) {
                                pozX = posSusI;
                                pozY = posSusJ;
                            }
                        } else if ((apaStanga != null && apaStanga.getisScanned())
                                || (plantaStanga != null
                                && !plantaStanga.getMaturityLevel().equals("dead")
                                && plantaStanga.getisScanned())) {
                            if (plantaStanga != null) {
                                pozX = posStangaI;
                                pozY = posStangaJ;
                            }
                        }
                    }
                    if (pozX == -1) {
                        if (apaSus != null || plantaSus != null) {
                            if (apaSus != null) {
                                if (apaSus.waterQuality() > calitateApa) {
                                    calitateApa = apaSus.waterQuality();
                                    pozX = posSusI;
                                    pozY = posSusJ;
                                }
                            }
                        } else if (apaDreapta != null || plantaDreapta != null) {
                            if (apaDreapta != null) {
                                if (apaDreapta.waterQuality() > calitateApa) {
                                    calitateApa = apaDreapta.waterQuality();
                                    pozX = posDreaptaI;
                                    pozY = posDreaptaJ;
                                }
                            }
                        } else if (apaJos != null || plantaJos != null) {
                            if (apaJos != null) {
                                if (apaJos.waterQuality() > calitateApa) {
                                    calitateApa = apaJos.waterQuality();
                                    pozX = posJosI;
                                    pozY = posJosJ;
                                }
                            }
                        } else if (apaStanga != null || plantaStanga != null) {
                            if (apaStanga != null) {
                                if (apaStanga.waterQuality() > calitateApa) {
                                    calitateApa = apaStanga.waterQuality();
                                    pozX = posStangaI;
                                    pozY = posStangaJ;
                                }
                            }
                        }
                    }
                }
                if (pozX == -1) {
                    if (existaDreapta == 1) {
                        pozX = posDreaptaI;
                        pozY = posDreaptaJ;
                    } else if (existaJos == 1) {
                        pozX = posJosI;
                        pozY = posJosJ;
                    } else if (existaSus == 1) {
                        pozX = posSusI;
                        pozY = posSusJ;
                    } else if (existaStanga == 1) {
                        pozX = posStangaI;
                        pozY = posStangaJ;
                    }
                }
                if (animalul.getType().equals("Carnivores")
                        || animalul.getType().equals("Parasites")) {
                    List<Entity> patratica = mat[pozX][pozY];
                    Animal animalPatratica = ReturnHelper.returnAnimal(patratica);
                    patratica.add(animalul);
                    // animalul.setType("adaugat");
                    entities.remove(animalul);
                    if (animalPatratica != null) {
                        animalul.setMass(animalul.getMass() + animalPatratica.getMass());
                        okInterAnimal = 1;
                        //interactiune animal-sol
                        animalPatratica.setType("out");
                    } else {
                        Plant plantaCurenta = ReturnHelper.returnPlant(patratica);
                        Water apaCurenta = ReturnHelper.returnWater(patratica);
                        if (plantaCurenta != null && plantaCurenta.getisScanned()) {
                            plantaCurenta.setMaturityLevel("dead");
                            // entities.remove(plantaCurenta);
                            animalul.setMass(animalul.getMass() + plantaCurenta.getMass());
                            // System.out.println("a mancat planta");
                            animalul.setState("well-fed");
                            okInterAnimal = 1;
                        }
                        if (apaCurenta != null && apaCurenta.getisScanned()) {
                            double waterToDrink = Math.min(animalul.getMass()
                                            * MagicNumbersDouble.zerozeroopt.getNumar(),
                                    apaCurenta.getMass());
                            apaCurenta.setMass(apaCurenta.getMass() - waterToDrink);
                            animalul.setMass(animalul.getMass() + waterToDrink);
                            // animalul.setState("well-fed");
                            okInterAnimal = 1;
                        }
                    }
                } else {
                    List<Entity> patratica = mat[pozX][pozY];
                    patratica.add(animalul);
                    entities.remove(animalul);
                    Plant plantaCurenta = ReturnHelper.returnPlant(patratica);
                    Water apaCurenta = ReturnHelper.returnWater(patratica);
                    if (plantaCurenta != null && plantaCurenta.getisScanned()) {
                        plantaCurenta.setMaturityLevel("dead");
                        // entities.remove(plantaCurenta);
                        animalul.setMass(animalul.getMass() + plantaCurenta.getMass());
                        okInterAnimal = 1;
                        // interactiune animal_soil cu MagicNumbersDouble.half.getNumar()
                    }
                    if (apaCurenta != null && apaCurenta.getisScanned()) {
                        double waterToDrink = Math.min(animalul.getMass()
                                        * MagicNumbersDouble.zerozeroopt.getNumar(),
                                apaCurenta.getMass());
                        apaCurenta.setMass(apaCurenta.getMass() - waterToDrink);
                        animalul.setMass(animalul.getMass() + waterToDrink);
                        okInterAnimal = 1;
                        // interactiune animal_soil cu MagicNumbersDouble.half.getNumar()
                    }
                }
            }
        }
        return okInterAnimal;
    }
}
