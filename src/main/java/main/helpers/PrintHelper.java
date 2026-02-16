package main.helpers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import main.Entity;
import main.Water;
import main.animal.Animal;
import main.magicNumbers.MagicNumbersDouble;
import main.plant.Plant;
import main.air.Air;
import main.air.DesertAir;
import main.air.MountainAir;
import main.air.TemperateAir;
import main.air.TropicalAir;
import main.air.Polar;
import main.soil.DesertSoil;
import main.soil.ForestSoil;
import main.soil.Soil;
import main.soil.SwampSoil;
import main.soil.GrasslandSoil;
import main.soil.TundraSoil;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

public final class PrintHelper {
    private PrintHelper() {

    }
    /**
     * Printeaza sol.
     *
     * @param solulet solul de pe patratica
     * @return caracteristicile solului
     */
    public static ObjectNode printSoil(final Soil solulet,
                                       final ObjectMapper mapper) {
        ObjectNode soilNode = mapper.createObjectNode();
        soilNode.put("type", solulet.getClass().getSimpleName());
        soilNode.put("name", solulet.getName());
        soilNode.put("mass", solulet.getMass());
        soilNode.put("nitrogen", solulet.getNitrogen());
        soilNode.put("waterRetention", solulet.getWaterRetention());
        soilNode.put("soilpH", solulet.getSoilPh());
        soilNode.put("organicMatter", solulet.getOrganicMatter());
        soilNode.put("soilQuality", solulet.soilQuality());
        if (solulet.isForest()) {
            soilNode.put("leafLitter", ((ForestSoil) solulet).getLeafLitter());
        } else if (solulet.isSwamp()) {
            soilNode.put("waterLogging", ((SwampSoil) solulet).getWaterLogging());
        } else if (solulet.isDesertSoil()) {
            soilNode.put("salinity", ((DesertSoil) solulet).getSalinity());
        } else if (solulet.isGrassland()) {
            soilNode.put("rootDensity", ((GrasslandSoil) solulet).getRootDensity());
        } else if (solulet.isTundra()) {
            soilNode.put("permafrostDepth", ((TundraSoil) solulet).getPermafrostDepth());
        }
        return soilNode;
    }

    /**
     * Printeaza planta.
     *
     * @param plantuta planta de pe patratica
     * @return caracteristicile plantei
     */
    public static ObjectNode printPlant(final Plant plantuta,
                                        final ObjectMapper mapper) {
        ObjectNode plantNode = mapper.createObjectNode();
        plantNode.put("type", plantuta.getClass().getSimpleName());
        plantNode.put("name", plantuta.getName());
        plantNode.put("mass", plantuta.getMass());
        // plantNode.put("state", plantuta.getMaturityLevel());
        return plantNode;
    }

    /**
     * Printeaza animalul.
     *
     * @param animalut animalul de pe patratica
     * @return caracteristicile animalului
     */
    public static ObjectNode printAnimal(final Animal animalut,
                                         final ObjectMapper mapper) {
        ObjectNode animalNode = mapper.createObjectNode();
        animalNode.put("type", animalut.getClass().getSimpleName());
        animalNode.put("name", animalut.getName());
        animalNode.put("mass", animalut.getMass());
        return animalNode;
    }

    /**
     * Printeaza apa.
     *
     * @param apita apa de pe patratica
     * @return caracteristicile apei
     */
    public static ObjectNode printWater(final Water apita,
                                        final ObjectMapper mapper) {
        ObjectNode waterNode = mapper.createObjectNode();
        waterNode.put("type", apita.getType());
        waterNode.put("name", apita.getName());
        waterNode.put("mass", apita.getMass());
        // waterNode.put("scanat", apita.getisScanned());
        return waterNode;
    }

    /**
     * Printeaza aer
     *
     * @param airut aerul de pe patratica
     * @return caracteristicile aerului
     */
    public static ObjectNode printAir(final Air airut,
                                      final ObjectMapper mapper) {
        ObjectNode airNode = mapper.createObjectNode();
        airNode.put("type", airut.getClass().getSimpleName());
        airNode.put("name", airut.getName());
        airNode.put("mass", airut.getMass());
        airNode.put("humidity", airut.getHumidity());
        airNode.put("temperature", airut.getTemperature());
        airNode.put("oxygenLevel", airut.getOxygenLevel());
        airNode.put("airQuality", airut.getAirQuality());
        return airNode;
    }

    /**
     * Printeaza aer fara eveniment.
     *
     * @param airut   aerul de pe patratica
     * @param airNode ajuta la printare
     */
    public static void printAirWithoutEvent(final Air airut,
                                            final ObjectNode airNode,
                                            final ObjectMapper mapper) {
        if (airut.isMountain()) {
            airNode.put("altitude", ((MountainAir) airut).getAltitude());
        } else if (airut.isTemperate()) {
            airNode.put("pollenLevel", ((TemperateAir) airut).getPollenLevel());
        } else if (airut.isTropical()) {
            double co2 = Math.round(((TropicalAir) airut).getCo2Level()
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar();
            airNode.put("co2Level", co2);
        } else if (airut.isPolar()) {
            airNode.put("iceCrystalConcentration", ((Polar) airut).getIceCrystalConcentration());
        } else if (airut.isDesert()) {
            airNode.put("dustParticles", ((DesertAir) airut).getDustParticles());
        }
    }

    /**
     * Printeaza aer cu eveniment.
     *
     * @param airut   aerul de pe patratica
     * @param airNode nodul pentru printare
     */
    public static void printAirWithEvent(final Air airut,
                                         final ObjectNode airNode,
                                         final ObjectMapper mapper) {
        if (airut.isMountain()) {
            airNode.put("altitude", ((MountainAir) airut).getAltitude());
        } else if (airut.isTemperate()) {
            airNode.put("pollenLevel", ((TemperateAir) airut).getPollenLevel());
        } else if (airut.isTropical()) {
            double result = Math.round(((TropicalAir) airut).getCo2Level()
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar();
            airNode.put("co2Level", result);
        } else if (airut.isPolar()) {
            airNode.put("iceCrystalConcentration", ((Polar) airut).getIceCrystalConcentration());
        } else if (airut.isDesert()) {
            airNode.put("desertStorm", ((DesertAir) airut).getDesertStorm());
        }
    }

    /**
     * Realiezeaza printarea entitatilor de pe patratica respectiva.
     *
     * @param entities         lista de entitati
     * @param okSchimbariMeteo contor daca s-a intrat pe changeEnvConditions
     * @return nodul pentru printare
     */
    public static ObjectNode printEnvCond(final List<Entity> entities,
                                          final int okSchimbariMeteo,
                                          final ObjectMapper mapper) {
        ObjectNode env = mapper.createObjectNode();
        for (int k = 0; k < entities.size(); k++) {
            Entity entity = entities.get(k);
            if (entity.isSoil()) {
                Soil solulet = (Soil) entity;
                ObjectNode soilNode = printSoil(solulet, mapper);
                env.set("soil", soilNode);
            } else if (entity.isPlant()) {
                Plant plantuta = (Plant) entity;
                if (!plantuta.getMaturityLevel().equals("out")) {
                    ObjectNode plantNode = printPlant(plantuta, mapper);
                    env.set("plants", plantNode);
                }
            } else if (entity.isAnimal()) {
                Animal animalut = (Animal) entity;
                ObjectNode animalNode = printAnimal(animalut, mapper);
                env.set("animals", animalNode);
            } else if (entity.isWater()) {
                Water apita = (Water) entity;
                ObjectNode waterNode = printWater(apita, mapper);
                env.set("water", waterNode);
            } else if (entity.isAir()) {
                Air airut = (Air) entity;
                ObjectNode airNode = printAir(airut, mapper);
                if (okSchimbariMeteo == 0) {
                    printAirWithoutEvent(airut, airNode, mapper);
                } else {
                    printAirWithEvent(airut, airNode, mapper);
                }
                env.set("air", airNode);
            }
        }
        return env;
    }

    /**
     * Printeaza subiectele. Pentru printknowledgeBase.
     *
     * @param factsAndSubjects lista de subiecte si topicuri
     * @return nodul pe care il printez
     */
    public static ArrayNode printSubjects(final LinkedHashMap<String,
                List<String>> factsAndSubjects, final ObjectMapper mapper) {
        ArrayNode subiecte = mapper.createArrayNode();
        Set<Map.Entry<String, List<String>>> subjs = factsAndSubjects.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = subjs.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> subj = iterator.next();
            String fact = subj.getKey();
            List<String> subiect = subj.getValue();
            if (subiect != null && !subiect.isEmpty()) {
                ObjectNode adaugaS = mapper.createObjectNode();
                adaugaS.put("topic", fact);
                ArrayNode vectorSub = mapper.createArrayNode();
                for (int j = 0; j < subiect.size(); j++) {
                    vectorSub.add(subiect.get(j));
                }
                adaugaS.put("facts", vectorSub);
                subiecte.add(adaugaS);
            }
        }
        return subiecte;
    }
}
