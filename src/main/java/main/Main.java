package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.*;

import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.IOException;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {

    private Main() {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final ObjectWriter WRITER = MAPPER.writer().withDefaultPrettyPrinter();

    /**
     * Calculeaza probabilitatea
     * @param entities
     * @return
     */
    public static double calculateProbabilitate(final List<Entity> entities) {
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
                calculProbabilitate = calculProbabilitate + plantuta.probabilityAttack();
            } else if (entity.isAnimal()) {
                Animal animalut = (Animal) entity;
                calculProbabilitate = calculProbabilitate + animalut.probabilityAttack();
            }
        }
        return calculProbabilitate;
    }

    /**
     * Calculeaza numarul de obiecte
     * @param entities
     * @return
     */
    public static int calculateObjects(final List<Entity> entities) {
        int objects = 0;
        for (int k = 0; k < entities.size(); k++) {
            Entity entity = entities.get(k);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                objects++;
            } else if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                objects++;
            } else if (entity.isPlant()) {
                Plant plantuta = (Plant) entity;
                objects++;
            } else if (entity.isAnimal()) {
                Animal animalut = (Animal) entity;
                objects++;
            }
        }
        return objects;
    }

    /**
     * Printeaza sol.
     * @param solulet
     * @return
     */
    public static ObjectNode printSoil(final Soil solulet) {
        ObjectNode soilNode = MAPPER.createObjectNode();
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
     * @param plantuta
     * @return
     */
    public static ObjectNode printPlant(final Plant plantuta) {
        ObjectNode plantNode = MAPPER.createObjectNode();
        plantNode.put("type", plantuta.getClass().getSimpleName());
        plantNode.put("name", plantuta.getName());
        plantNode.put("mass", plantuta.getMass());
        return plantNode;
    }

    /**
     * Printeaza animalul.
     * @param animalut
     * @return
     */
    public static ObjectNode printAnimal(final Animal animalut) {
        ObjectNode animalNode = MAPPER.createObjectNode();
        animalNode.put("type", animalut.getClass().getSimpleName());
        animalNode.put("name", animalut.getName());
        animalNode.put("mass", animalut.getMass());
        return  animalNode;
    }

    /**
     * Printeaza apa.
     * @param apita
     * @return
     */
    public static ObjectNode printWater(final Water apita) {
        ObjectNode waterNode = MAPPER.createObjectNode();
        waterNode.put("type", apita.getType());
        waterNode.put("name", apita.getName());
        waterNode.put("mass", apita.getMass());
        // waterNode.put("scanat", apita.getisScanned());
        return waterNode;
    }

    /**
     * Printeaza aer
     * @param airut
     * @return
     */
    public static ObjectNode printAir(final Air airut) {
        ObjectNode airNode = MAPPER.createObjectNode();
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
     * @param airut
     * @param airNode
     */
    public static void printAirWithoutEvent(final Air airut, final ObjectNode airNode) {
        if (airut.isMountain()) {
            airNode.put("altitude", ((MountainAir) airut).getAltitude());
        } else if (airut.isTemperate()) {
            airNode.put("pollenLevel", ((TemperateAir) airut).getPollenLevel());
        } else if (airut.isTropical()) {
            airNode.put("co2Level", ((TropicalAir) airut).getCo2Level());
        } else if (airut.isPolar()) {
            airNode.put("iceCrystalConcentration", ((Polar) airut).getIceCrystalConcentration());
        } else if (airut.isDesert()) {
            airNode.put("dustParticles", ((DesertAir) airut).getDustParticles());
        }
    }

    /**
     * Printeaza aer cu eveniment.
     * @param airut
     * @param airNode
     */
    public static void printAirWithEvent(final Air airut, final ObjectNode airNode) {
        if (airut.isMountain()) {
            airNode.put("numberOfHikers", ((MountainAir) airut).getNumberOfHikers());
        } else if (airut.isTemperate()) {
            airNode.put("season", ((TemperateAir) airut).getSeason());
        } else if (airut.isTropical()) {
            airNode.put("rainfall", ((TropicalAir) airut).getRainfall());
        } else if (airut.isPolar()) {
            airNode.put("windSpeed", ((Polar) airut).getWindSpeed());
        } else if (airut.isDesert()) {
            airNode.put("desertStorm", ((DesertAir) airut).getDesertStorm());
        }
    }

    /**
     * Verific daca exista planta
     * @param entities
     * @return
     */
    public static boolean existPlant(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isPlant()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verific daca exista animal.
     * @param entities
     * @return
     */
    public static boolean existanimal(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isAnimal()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verific daca exista apa.
     * @param entities
     * @return
     */
    public static boolean existWater(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isWater()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Caut planta in patratica de entitati.
     * @param entities
     * @return
     */
    public static Plant returnPlant(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isPlant()) {
                return (Plant) entity;
            }
        }
        return null;
    }

    /**
     * Caut animal in patratica de entitati.
     * @param entities
     * @return
     */
    public static Animal returnAnimal(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAnimal()) {
                return (Animal) entity;
            }
        }
        return null;
    }

    /**
     * Caut sol in patratica de entitati.
     * @param entities
     * @return
     */
    public static Soil returnSoil(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil()) {
                return (Soil) entity;
            }
        }
        return null;
    }

    /**
     * Caut apa in patratica de entitati
     * @param entities
     * @return
     */
    public static Water returnWater(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isWater()) {
                return (Water) entity;
            }
        }
        return null;
    }

    /**
     * Cauta aer in patratica de entitati.
     * @param entities
     * @return
     */
    public static Air returnAir(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAir()) {
                return (Air) entity;
            }
        }
        return null;
    }

    /**
     * Se fac interactiunile automate la cate o iteratie.
     * @param entities
     * @param okInterAnimal
     */
    public static void interactiuniEveryIteration(final List<Entity> entities,
                                                  final int okInterAnimal) {
        Plant planta =  returnPlant(entities);
        Animal animal =  returnAnimal(entities);
        Soil soil =  returnSoil(entities);
        Water water =  returnWater(entities);
        Air air =  returnAir(entities);
        if (air != null
                && animal != null
                && air.toxicity().equals("toxic")) {
            animal.setState("sick");
        }
        if (soil != null && planta != null) {
            planta.setNivelCrestere(planta.getNivelCrestere()
                    + MagicNumbersDouble.zerodoi.getNumar());
        }
        if (water != null && planta != null && water.getisScanned()) {
            planta.setNivelCrestere(planta.getNivelCrestere()
                    + MagicNumbersDouble.zerodoi.getNumar());
        }
        if (planta != null && air != null && planta.getisScanned()) {
            if (planta.getNivelCrestere() >= 1.0) {
                if (planta.getMaturityLevel().equals("young")) {
                    planta.setMaturityLevel("mature");
                } else if (planta.getMaturityLevel().equals("mature")) {
                    planta.setMaturityLevel("old");
                } else if (planta.getMaturityLevel().equals("old")) {
                    planta.setMaturityLevel("dead");
                }
                planta.setNivelCrestere(0);
            }
            air.setOxygenLevel(Math.round((air.getOxygenLevel()
                    + planta.oxigenFromPlant()
                    + planta.maturityOxigenLevel())
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar());
            air.setAirQuality(Math.round(air.airQuality()
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar());
        }
        if (okInterAnimal == 1) {
            if (planta != null && water != null) {
                soil.setOrganicMatter(soil.getOrganicMatter()
                        + MagicNumbersDouble.oxygenMoss.getNumar());
            } else if (planta != null || water != null) {
                soil.setOrganicMatter(soil.getOrganicMatter()
                        + MagicNumbersDouble.half.getNumar());
            }
        }
        if (animal != null
                && water != null && animal.getisScanned()) {
            animal.setMass(animal.getMass() + water.getMass());
        }
    }

    /**
     * Se fac interactiunile automate la 2 iteratii.
     * @param entities
     */
    public static void interactiuniEveryTwoIterations(final List<Entity> entities) {
        Plant planta = returnPlant(entities);
        Animal animal = returnAnimal(entities);
        Soil soil = returnSoil(entities);
        Water water = returnWater(entities);
        Air air = returnAir(entities);
        if (water != null && air != null && water.getisScanned()) {
            double result = Math.round((air.getHumidity()
                    + MagicNumbersDouble.zerounu.getNumar())
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar();
            air.setHumidity(result);
            air.setAirQuality(Math.round(air.airQuality()
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar());
        }
        if (water != null && soil != null && water.getisScanned()) {
            double result = Math.round((soil.getWaterRetention()
                    + MagicNumbersDouble.zerounu.getNumar())
                    * MagicNumbersDouble.normalize.getNumar())
                    / MagicNumbersDouble.normalize.getNumar();
            soil.setWaterRetention(result);
        }
    }

    /**
     * Aici se muta robotelul pe una din patratelele vecine.
     * @param robotel
     * @param mat
     * @param dimension
     */
    public static void mutaRobotelul(final Robot robotel,
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
        ObjectNode neighbors = MAPPER.createObjectNode();
        if (posJosI >= 0
                && posJosJ >= 0 && posJosI < dimension
                && posJosJ < dimension) {
            // iau scorul
            List<Entity> entities = mat[posJosI][posJosJ];
            scorJos = entities.get(0).getAttack();
            // node.put("jos", scorJos);
        }
        if (posDreaptaI >= 0
                && posDreaptaJ >= 0 && posDreaptaI < dimension
                && posDreaptaJ < dimension) {
            // iau scorul
            List<Entity> entities = mat[posDreaptaI][posDreaptaJ];
            scorDreapta = entities.get(0).getAttack();
            // node.put("dreapta", scorDreapta);
        }
        if (posSusI >= 0
                && posSusJ >= 0 && posSusI < dimension
                && posSusJ < dimension) {
            // iau scorul
            List<Entity> entities = mat[posSusI][posSusJ];
            scorSus = entities.get(0).getAttack();
            // node.put("sus", scorSus);
        }
        if (posStangaI >= 0
                && posStangaJ >= 0 && posStangaI < dimension
                && posStangaJ < dimension) {
            List<Entity> entities = mat[posStangaI][posStangaJ];
            scorStanga = entities.get(0).getAttack();
            // node.put("stanga", scorStanga);
        }
        if (scorDreapta >= 0 && scorDreapta < scorMinim) {
            scorMinim = scorDreapta;
            pozitieMutareI = posDreaptaI;
            pozitieMutareJ = posDreaptaJ;
        }
        if (scorJos >= 0 && scorJos < scorMinim) {
            List<Entity> entities = mat[posJosI][posJosJ];
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

    /**
     * Se va actualiza oxigenul.
     * @param entities
     * @return
     */
    public static int updateazaOxigenul(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                aer.setOxygenLevel(aer.getOxygenLevel()
                        + MagicNumbersDouble.zerotrei.getNumar());
                aer.setAirQuality(aer.airQuality());
                return 1;
            }
        }
        return 0;
    }

    /**
     * Se actualizeaza orgmatter, e pentru testul cu improve.
     * @param entities
     * @return
     */
    public static int updateazaFertilizarea(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                sol.setOrganicMatter(sol.getOrganicMatter()
                        + MagicNumbersDouble.zerodoi.getNumar());
                return 1;
            }
        }
        return 0;
    }

    /**
     * Se actualizeaza humidity, e pentru testul cu improve.
     * @param entities
     * @return
     */
    public static int updateazaUmiditatea(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                aer.setHumidity(aer.getHumidity()
                        + MagicNumbersDouble.zerodoi.getNumar());
                aer.setOxygenLevel(aer.airQuality());
                return 1;
            }
        }
        return 0;
    }

    /**
     * Se actualizeaza waterRet, e pentru testul cu improve.
     * @param entities
     * @return
     */
    public static int updateazaWaterret(final List<Entity> entities) {
        for (int i = 0; i < entities.size(); i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                sol.setWaterRetention(sol.getWaterRetention()
                        + MagicNumbersDouble.zerodoi.getNumar());
                return 1;
            }
        }
        return 0;
    }

    /**
     * MAIN
     * @param inputPath
     * @param outputPath
     * @throws IOException
     */
    public static void action(final String inputPath,
                              final String outputPath) throws IOException {

        InputLoader inputLoader = new InputLoader(inputPath);
        ArrayNode output = MAPPER.createArrayNode();
        /*
         * TODO Implement your function here
         *
         * How to add output to the output array?
         * There are multiple ways to do this, here is one example:
         *
         *
         * ObjectNode objectNode = MAPPER.createObjectNode();
         * objectNode.put("field_name", "field_value");
         *
         * ArrayNode arrayNode = MAPPER.createArrayNode();
         * arrayNode.add(objectNode);
         *
         * output.add(arrayNode);
         * output.add(objectNode);
         *
         */

//        ObjectNode node = MAPPER.createObjectNode();
//        node.put("command", "startSimulation");
//        output.add(node);
//        node.put("message", "Simulation has started.");
//        output.add(node);
//        node.put("timestamp", "1");
//        output.add(node);
        List<SimulationInput> simulations = inputLoader.getSimulations();
        SimulationInput simInput = simulations.get(0);
        String dimensionString = simInput.getTerritoryDim();
        int battery = simInput.getEnergyPoints();
        Robot robotel = new Robot(battery, 0, 0, false);
        int dimension = Integer.parseInt(dimensionString.split("x")[0]);
        List<Entity>[][] mat = new List[dimension][dimension];
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                mat[i][j] = new ArrayList<>();
            }
        }
        int energyPoints = simInput.getEnergyPoints();
        TerritorySectionParamsInput territory =
                simInput.getTerritorySectionParams();
        List<SoilInput> soil = territory.getSoil();
        List<PlantInput> plant = territory.getPlants();
        List<AnimalInput> animal = territory.getAnimals();
        List<WaterInput> water = territory.getWater();
        List<AirInput> air = territory.getAir();
        for (int i = 0; i < territory.getSoil().size(); i++) {
            SoilInput sol = territory.getSoil().get(i);
            for (int k = 0; k < sol.getSections().size(); k++) {
                PairInput loc = sol.getSections().get(k);
            Soil solul = null;
            if (sol.getType().equals("ForestSoil")) {
                solul = new ForestSoil(sol.getName(), sol.getMass(),
                        sol.getNitrogen(), sol.getWaterRetention(),
                        sol.getSoilpH(), sol.getOrganicMatter(),
                        sol.getLeafLitter());
            } else if (sol.getType().equals("SwampSoil")) {
                solul = new SwampSoil(sol.getName(), sol.getMass(),
                        sol.getNitrogen(), sol.getWaterRetention(),
                        sol.getSoilpH(), sol.getOrganicMatter(),
                        sol.getWaterLogging());
            } else if (sol.getType().equals("DesertSoil")) {
                solul = new DesertSoil(sol.getName(), sol.getMass(),
                        sol.getNitrogen(), sol.getWaterRetention(),
                        sol.getSoilpH(), sol.getOrganicMatter(),
                        sol.getSalinity());
            } else if (sol.getType().equals("GrasslandSoil")) {
                solul = new GrasslandSoil(sol.getName(), sol.getMass(),
                        sol.getNitrogen(), sol.getWaterRetention(),
                        sol.getSoilpH(), sol.getOrganicMatter(),
                        sol.getRootDensity());
            } else if (sol.getType().equals("TundraSoil")) {
                solul = new TundraSoil(sol.getName(), sol.getMass(),
                        sol.getNitrogen(), sol.getWaterRetention(),
                        sol.getSoilpH(), sol.getOrganicMatter(),
                        sol.getPermafrostDepth());
            }
                mat[loc.getX()][loc.getY()].add(solul);
            }
        }
        for (int i = 0; i < territory.getPlants().size(); i++) {
            PlantInput planta = territory.getPlants().get(i);
            for (int k = 0; k < planta.getSections().size(); k++) {
                PairInput loc = planta.getSections().get(k);
            Plant plantuta = null;
            if (planta.getType().equals("FloweringPlants")) {
                plantuta = new FloweringPlants(planta.getName(),
                        planta.getMass(), 0, "young");
            } else if (planta.getType().equals("GymnospermsPlants")) {
                plantuta = new GymnospermsPlants(planta.getName(),
                        planta.getMass(), 0, "young");
            } else if (planta.getType().equals("Ferns")) {
                plantuta = new Ferns(planta.getName(),
                        planta.getMass(), 0, "young");
            } else if (planta.getType().equals("Mosses")) {
                plantuta = new Mosses(planta.getName(),
                        planta.getMass(), 0, "young");
            } else if (planta.getType().equals("Algae")) {
                plantuta = new Algae(planta.getName(),
                        planta.getMass(), 0, "young");
            }
                mat[loc.getX()][loc.getY()].add(plantuta);
            }

        }
        for (int i = 0; i < territory.getAnimals().size(); i++) {
            AnimalInput animalul = territory.getAnimals().get(i);
            for (int k = 0; k < animalul.getSections().size(); k++) {
                PairInput loc = animalul.getSections().get(k);
            Animal animalut = null;
            if (animalul.getType().equals("Herbivores")) {
                animalut = new Herbivores(animalul.getName(),
                        animalul.getMass(), "hungry");
            } else if (animalul.getType().equals("Carnivores")) {
                animalut = new Carnivores(animalul.getName(),
                        animalul.getMass(), "hungry");
            } else if (animalul.getType().equals("Omnivores")) {
                animalut = new Omnivores(animalul.getName(),
                        animalul.getMass(), "hungry");
            } else if (animalul.getType().equals("Detritivores")) {
                animalut = new Detritivores(animalul.getName(),
                        animalul.getMass(), "hungry");
            } else if (animalul.getType().equals("Parasites")) {
                animalut = new Parasites(animalul.getName(),
                        animalul.getMass(), "hungry");
            }
                mat[loc.getX()][loc.getY()].add(animalut);
            }
        }
        for (int i = 0; i < territory.getWater().size(); i++) {
            WaterInput apa = territory.getWater().get(i);
            for (int k = 0; k < apa.getSections().size(); k++) {
                PairInput loc = apa.getSections().get(k);
            Water apita = new Water(apa.getName(), apa.getMass(),
                    apa.getType(), apa.getSalinity(), apa.getPH(),
                    apa.getPurity(), apa.getTurbidity(),
                    apa.getContaminantIndex(), apa.isFrozen());
                mat[loc.getX()][loc.getY()].add(apita);
            }
        }
        for (int i = 0; i < territory.getAir().size(); i++) {
            AirInput aer = territory.getAir().get(i);
            for (int k = 0; k < aer.getSections().size(); k++) {
                PairInput loc = aer.getSections().get(k);
            Air aerut = null;
            if (aer.getType().equals("TropicalAir")) {
                aerut = new TropicalAir(aer.getName(),
                        aer.getMass(), aer.getHumidity(),
                        aer.getTemperature(), aer.getOxygenLevel(),
                        aer.getCo2Level());
            } else if (aer.getType().equals("PolarAir")) {
                aerut = new Polar(aer.getName(), aer.getMass(),
                        aer.getHumidity(), aer.getTemperature(),
                        aer.getOxygenLevel(),
                        aer.getIceCrystalConcentration());
            } else if (aer.getType().equals("TemperateAir")) {
                aerut = new TemperateAir(aer.getName(), aer.getMass(),
                        aer.getHumidity(), aer.getTemperature(),
                        aer.getOxygenLevel(), aer.getPollenLevel());
            } else if (aer.getType().equals("DesertAir")) {
                aerut = new DesertAir(aer.getName(),
                        aer.getMass(), aer.getHumidity(),
                        aer.getTemperature(), aer.getOxygenLevel(),
                        aer.getDustParticles());
            } else if (aer.getType().equals("MountainAir")) {
                aerut = new MountainAir(aer.getName(),
                        aer.getMass(), aer.getHumidity(),
                        aer.getTemperature(), aer.getOxygenLevel(),
                        aer.getAltitude());
            }
                aerut.setAirQuality(aerut.airQuality());
                mat[loc.getX()][loc.getY()].add(aerut);
            }
        }
        int iteratii = 0;
        int stamps = 0;
        int okSchimbariMeteo = 0;
        int okScanari = 0;
        int sAScanat = 0;
        int incepeIteratie = 0;
        int previousStamp = 0;
        int okInterAnimal = 0;
        int inceputIteratieAnimal = 0;
        List<CommandInput> commands = inputLoader.getCommands();
        int okSimulation = 0;
        for (int i = 0; i < commands.size(); i++) {
            // double soilQuality = 0.0;
            double airQuality = 0.0;
            CommandInput command = commands.get(i);
            if ((command.getTimestamp() - previousStamp >= stamps)
                    && (robotel.isCharging())) {
                robotel.setIsCharging(false);
                stamps = 0;
                previousStamp = 0;
            }
            if (okScanari == 1) {
                if (sAScanat == 0) {
                    sAScanat = 1;
                    incepeIteratie = iteratii;
                }
            }
            iteratii++;
            if (okScanari == 1) {
                for (int a = 0; a < dimension; a++) {
                    for (int b = 0; b < dimension; b++) {
                        List<Entity> entities = mat[a][b];
                        // node.put("adauga", return_plant(entities).getOxygen_curent());
                        interactiuniEveryIteration(entities, okInterAnimal);
                        if (iteratii > incepeIteratie && (iteratii - incepeIteratie) % 2 == 0) {
//                            Water api =  return_water(entities);
//                            node.put("message", "Am intrat");
                            interactiuniEveryTwoIterations(entities);
                        }
                    }
                }
            }
            for (int a = 0; a < dimension; a++) {
                for (int b = 0; b < dimension; b++) {
                    List<Entity> entities = mat[b][a];
                    double calculProbabilitate =
                            calculateProbabilitate(entities);
                    int obiecte = calculateObjects(entities);
                    double mean = -1, score = -1;
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
            ObjectNode node = MAPPER.createObjectNode();
            node.put("command", command.getCommand());
            if (command.getCommand().equals("startSimulation")) {
                node.put("message", "Simulation has started.");
                okSimulation = 1;
            } else if (command.getCommand().equals("printEnvConditions")) {
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.getBattery() <= 0) {
                    node.put("message",
                            "ERROR: Not enough battery left. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else {
                    ObjectNode env = MAPPER.createObjectNode();
                    List<Entity> entities = mat[robotel.getPozX()][robotel.getPozY()];
                    for (int k = 0; k < entities.size(); k++) {
                        Entity entity = entities.get(k);
                        if (entity.isSoil()) {
                            Soil solulet = (Soil) entity;
                            ObjectNode soilNode = printSoil(solulet);
                            env.set("soil", soilNode);
                        } else if (entity.isPlant()) {
                            Plant plantuta = (Plant) entity;
                            ObjectNode plantNode = printPlant(plantuta);
                            env.set("plants", plantNode);
                        } else if (entity.isAnimal()) {
                            Animal animalut = (Animal) entity;
                            ObjectNode animalNode = printAnimal(animalut);
                            env.set("animals", animalNode);
                        } else if (entity.isWater()) {
                            Water apita = (Water) entity;
                            ObjectNode waterNode = printWater(apita);
                            env.set("water", waterNode);
                        } else if (entity.isAir()) {
                            Air airut = (Air) entity;
                            ObjectNode airNode = printAir(airut);
                            if (okSchimbariMeteo == 0) {
                                printAirWithoutEvent(airut, airNode);
                            } else {
                                printAirWithEvent(airut, airNode);
                            }
                            env.set("air", airNode);
                        }
                    }
                    node.set("output", env);
                }
            } else if (command.getCommand().equals("printMap")) {
                //  "section" : [ 0, 0 ],
                //    "totalNrOfObjects" : 3,
                //    "airQuality" : "moderate",
                //    "soilQuality" : "poor"
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.getBattery() <= 0) {
                    node.put("message",
                            "ERROR: Not enough battery left. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else {
                    ArrayNode mapNode = MAPPER.createArrayNode();
                    for (int a = 0; a < dimension; a++) {
                        for (int b = 0; b < dimension; b++) {
                            List<Entity> entities = mat[b][a];
                            ObjectNode entityNode = MAPPER.createObjectNode();
                            ArrayNode entitiesNode = MAPPER.createArrayNode();
                            entitiesNode.add(b);
                            entitiesNode.add(a);
                            entityNode.set("section", entitiesNode);
                            int contor = 0;
                            for (int p = 0; p < entities.size(); p++) {
                                if (entities.get(p).isWater()) {
                                    contor++;
                                }
                                if (entities.get(p).isPlant()) {
                                    contor++;
                                }
                                if (entities.get(p).isAnimal()) {
                                    contor++;
                                }
                            }
                            entityNode.put("totalNrOfObjects", contor);
                            String airquality = null;
                            String soilQuality = null;
                            for (int k = 0; k < entities.size(); k++) {
                                Entity entity = entities.get(k);
                                if (entity.isAir()) {
                                    Air aer = (Air) entity;
                                    double valoareAer = aer.getAirQuality();
                                    airquality = aer.resultAir(valoareAer);
                                } else if (entity.isSoil()) {
                                    Soil sol = (Soil) entity;
                                    double valoareSol = sol.soilQuality();
                                    soilQuality = sol.resultSoil(valoareSol);
                                }
                            }
                            // entityNode.put("scor", entities.get(0).get_attack());
                            entityNode.put("airQuality", airquality);
                            entityNode.put("soilQuality", soilQuality);
                            mapNode.add(entityNode);
                        }
                    }
                    node.set("output", mapNode);
                }
            } else if (command.getCommand().equals("endSimulation")) {
                okSimulation = 0;
                node.put("message", "Simulation has ended.");
            } else if (command.getCommand().equals("moveRobot")) {
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.getBattery() < 0) {
                    node.put("message",
                            "ERROR: Not enough battery left. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else {
                    robotel.setFostaBaterie(robotel.getBattery());
                    mutaRobotelul(robotel, mat, dimension);
                    node.put("command", command.getCommand());
                    if (robotel.getBattery() < 0) {
                        robotel.setBattery(robotel.getFostaBaterie());
                        node.put("message",
                                "ERROR: Not enough battery left. Cannot perform action");
                    } else {
                        node.put("message",
                                "The robot has successfully moved to position ("
                                        + robotel.getPozX() + ", "
                                        + robotel.getPozY() + ").");
                    }
                }
            } else if (command.getCommand().equals("scanObject")) {
                int sePoate = 0;
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else if (robotel.getBattery()
                        < MagicNumbersInt.sapte.getNumar()) {
                    node.put("message",
                            "ERROR: Not enough battery left. Cannot perform action");
                } else {
                    okScanari = 1;
                    List<Entity> entities = mat[robotel.getPozX()][robotel.getPozY()];
                    String color = command.getColor();
                    String smell = command.getSmell();
                    String sound = command.getSound();
                    if (existWater(entities)
                            && (color.equals("none"))
                            && (smell.equals("none")
                            && (sound.equals("none")))) {
                        sePoate = 1;
                        Water apa = returnWater(entities);
                        apa.setIsScanned(true);
                        apa.setMomentScanare(iteratii);
                        // node.put("apa", apa.getisScanned());
                        node.put("message", "The scanned object is water.");
                    } else if (existPlant(entities)
                            && (!color.equals("none"))
                            && (!smell.equals("none")
                            && (sound.equals("none")))) {
                        sePoate = 1;
                        Plant planta = returnPlant(entities);
                        planta.setIsScanned(true);
                        planta.setMomentScanare(iteratii);
                        node.put("message", "The scanned object is a plant.");
                    } else if (existanimal(entities)
                            && (!color.equals("none"))
                            && (!smell.equals("none")
                            && (!sound.equals("none")))) {
                        sePoate = 1;
                        Animal animalul = returnAnimal(entities);
                        animalul.setIsScanned(true);
                        node.put("message",
                                "The scanned object is an animal.");
                        inceputIteratieAnimal = iteratii;
                    } else {
                        node.put("message",
                                "ERROR: Object not found. Cannot perform action");
                    }
                    if (sePoate == 1) {
                        robotel.setBattery(robotel.getBattery()
                                - MagicNumbersInt.sapte.getNumar());
                    }
                }
            } else if (command.getCommand().equals("learnFact")) {
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else {
                    int eBun = 0;
                    String fact = command.getComponents();
                    String subj = command.getSubject();
                    for (int a = 0; a < dimension; a++) {
                        for (int b = 0; b < dimension; b++) {
                            List<Entity> entities = mat[a][b];
                            for (int p = 0; p < entities.size(); p++) {
                                Entity entity = entities.get(p);
                                if (entity.getisScanned()
                                        && entity.getName().equals(fact)) {
                                    eBun = 1;
                                    entity.setSubject(subj);
                                }
                            }
                        }
                    }
                    if (eBun == 1) {
                        robotel.setBattery(robotel.getBattery() - 2);
                        node.put("message",
                                "The fact has been successfully saved in the database.");
                    } else {
                        node.put("message",
                                "ERROR: Subject not yet saved. Cannot perform action");
                    }
                }
            } else if (command.getCommand().equals("printKnowledgeBase")) {
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else {
                ArrayNode subiecte = MAPPER.createArrayNode();
                for (int a = 0; a < dimension; a++) {
                    for (int b = 0; b < dimension; b++) {
                        List<Entity> entities = mat[a][b];
                        for (int k = 0; k < entities.size(); k++) {
                            Entity entity = entities.get(k);
                            if (entity.getisScanned()
                                    && entity.getSubject() != null
                                    && !entity.getSubject().isEmpty()) {
                                ObjectNode adaugaComp = MAPPER.createObjectNode();
                                String component = entity.getName();
                                adaugaComp.put("topic", component);
                                ArrayNode adaugaSubj = MAPPER.createArrayNode();
                                List<String> subjects = entity.getSubject();
                                for (int p = 0; p < subjects.size(); p++) {
                                    String subiect =  subjects.get(p);
                                    adaugaSubj.add(subiect);
                                }
                                adaugaComp.put("facts", adaugaSubj);
                                subiecte.add(adaugaComp);
                            }
                        }
                    }
                }
                    for (int m = 0; m < subiecte.size() - 1; m++) {
                        for (int n = m + 1; n < subiecte.size(); n++) {
                            String primulFact = subiecte.get(m).get("topic").asText();
                            String alDoilea = subiecte.get(n).get("facts").asText();
                            if (primulFact.compareTo(alDoilea) > 0) {
                                ObjectNode tmp = (ObjectNode) subiecte.get(m);
                                subiecte.set(m, subiecte.get(n));
                                subiecte.set(n, tmp);
                            }
                        }
                }
                node.set("output",  subiecte);
                }
            } else if (command.getCommand().equals("improveEnvironment")) {
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else {
//                    List<Entity> entities = mat[robotel.getPoz_x()][robotel.getPoz_y()];
//                    for (int p = 0 ; p <  entities.size(); p++) {
//                        Entity entity = entities.get(p);
//                        if (entity.isAir())
//                            node.put("ba", entity.getisScanned());
//                    }
                    String improvment = command.getImprovementType();
                    int merge = 0;
                    for (int a = 0; a < dimension; a++) {
                        for (int b = 0; b < dimension; b++) {
                            List<Entity> entities = mat[a][b];
                            for (int p = 0; p < entities.size(); p++) {
                                Entity entity = entities.get(p);
                                if (entity.getisScanned()
                                        && entity.getSubject().size() != 0
                                        && !entity.getSubject().isEmpty()) {
                                    for (int k = 0; k < entity.getSubject().size(); k++) {
                                        String subject = entity.getSubject().get(k);
                                        String[] comanda = subject.split(" ");
                                        String ceModific = comanda[comanda.length - 1];
                                        // node.put("campul", ceModific);
                                        if (improvment.equals("plantVegetation")
                                                && entity.isPlant() && entity.getisScanned()) {
                                            int ok = updateazaOxigenul(mat[robotel.getPozX()]
                                                    [robotel.getPozY()]);
                                            if (ok == 1) {
                                                merge = 1;
                                                node.put("message",
                                                        "The " + ceModific
                                                                + " was planted successfully.");
                                            }
                                        } else if (improvment.equals("fertilizeSoil")
                                                && entity.isSoil() && entity.getisScanned()) {
                                            int ok = updateazaFertilizarea(mat[robotel.getPozX()]
                                                    [robotel.getPozY()]);
                                            if (ok == 1) {
                                                merge = 1;
                                                node.put("message",
                                                        "The soil was "
                                                                + "successfully fertilized using "
                                                                + ceModific + ".");
                                            }
                                        } else if (improvment.equals("increaseHumidity")
                                                && entity.isAir() && entity.getisScanned()) {
                                            int ok = updateazaUmiditatea(mat[robotel.getPozX()]
                                                    [robotel.getPozY()]);
                                            if (ok == 1) {
                                                merge = 1;
                                                node.put("message",
                                                        "The air humidity was "
                                                                + "successfully increased using "
                                                                + ceModific + ".");
                                            }
                                        } else if (improvment.equals("increaseMoisture")
                                                && entity.isWater() && entity.getisScanned()) {
                                            int ok = updateazaWaterret(mat[robotel.getPozX()]
                                                    [robotel.getPozY()]);
                                            if (ok == 1) {
                                                merge = 1;
                                                node.put("message",
                                                        "The soil moisture was "
                                                                + "successfully increased using "
                                                                + ceModific + ".");
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (merge == 0) {
                        node.put("message",
                                "ERROR: Subject not yet saved. Cannot perform action");
                    } else {
                        robotel.setBattery(robotel.getBattery()
                                - MagicNumbersInt.zece.getNumar());
                    }
                }
            } else if (command.getCommand().equals("getEnergyStatus")) {
                if (okSimulation == 0) {
                    node.put("message",
                            "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message",
                            "ERROR: Robot still charging. Cannot perform action");
                } else {
                    node.put("command", "getEnergyStatus");
                    node.put("message",
                            "TerraBot has " + robotel.getBattery() + " energy points left.");
                }
            } else if (command.getCommand().equals("rechargeBattery")) {
                if (okSimulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    robotel.setBattery(robotel.getBattery() + command.getTimeToCharge());
                    stamps = command.getTimeToCharge();
                    previousStamp = command.getTimestamp();
                    node.put("message", "Robot battery is charging.");
                    robotel.setIsCharging(true);
                }
            } else if (command.getCommand().equals("changeWeatherConditions")) {
                if (okSimulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging()) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    okSchimbariMeteo = 1;
                    List<Entity> entities = mat[robotel.getPozX()][robotel.getPozY()];
                    for (int k = 0; k < entities.size(); k++) {
                        Entity entity = entities.get(k);
                        if (entity.isAir()) {
                            Air aer = (Air) entity;
                            if (aer.isTropical()) {
                                TropicalAir trop = (TropicalAir) aer;
                                String eveniment = command.getImprovementType();
                                double rainfall = command.getRainfall();
                                trop.setEvent(eveniment);
                                trop.setRainfall(rainfall);
                                trop.setAirQuality(trop.updateAirQuality());
                            } else if (aer.isPolar()) {
                                Polar polar = (Polar) aer;
                                String eveniment = command.getImprovementType();
                                double wind = command.getWindSpeed();
                                polar.setEvent(eveniment);
                                polar.setWindSpeed(wind);
                                polar.setAirQuality(polar.updateAirQuality());
                            } else if (aer.isTemperate()) {
                                TemperateAir temperate = (TemperateAir) aer;
                                String eveniment = command.getImprovementType();
                                String seas = command.getSeason();
                                temperate.setEvent(eveniment);
                                temperate.setSeason(seas);
                                temperate.setAirQuality(temperate.updateAirQuality());
                            } else if (aer.isDesert()) {
                                DesertAir desert = (DesertAir) aer;
                                String eveniment = command.getImprovementType();
                                boolean deser = command.isDesertStorm();
                                desert.setEvent(eveniment);
                                desert.setDesertStorm(deser);
                                desert.setAirQuality(desert.updateAirQuality());
                            } else if (aer.isMountain()) {
                                MountainAir mountain = (MountainAir) aer;
                                String eveniment = command.getImprovementType();
                                int number = command.getNumberOfHikers();
                                mountain.setEvent(eveniment);
                                mountain.setNumberOfHikers(number);
                                mountain.setAirQuality(mountain.updateAirQuality());
                            }
                        }
                    }
                    node.put("message", "The weather has changed.");
                }
            }
//            node.put("baterie", robotel.getBattery());
//            node.put("scor", mat[0][1].get(0).get_attack());
            List<Entity> entities = mat[0][0];
            Animal animalul = returnAnimal(entities);
            if (animalul != null && animalul.getisScanned()) {
                if (inceputIteratieAnimal < iteratii
                        && (iteratii - inceputIteratieAnimal)
                        % MagicNumbersInt.doi.getNumar() == 0
                        && animalul.getisScanned()) {
                    int existaStanga = 1;
                    int existaDreapta = 1;
                    int existaSus = 1;
                    int existaJos = 1;
                    int posStangaI = robotel.getPozX();
                    int posStangaJ = robotel.getPozY() - 1;
                    if (posStangaJ < 0 || posStangaJ >= dimension) {
                        existaStanga = 0;
                    }
                    int posSusI = robotel.getPozX() - 1;
                    int posSusJ = robotel.getPozY();
                    if (posSusI < 0 || posSusI >= dimension) {
                        existaSus = 0;
                    }
                    int posDreaptaI = robotel.getPozX();
                    int posDreaptaJ = robotel.getPozY() + 1;
                    if (posDreaptaJ < 0 || posDreaptaJ >= dimension) {
                        existaDreapta = 0;
                    }
                    int posJosI = robotel.getPozX() + 1;
                    int posJosJ = robotel.getPozY();
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
                        apaStanga = returnWater(entitiesStanga);
                        plantaStanga = returnPlant(entitiesStanga);
                    }
                    if (existaDreapta != 0) {
                        List<Entity> entitiesDreapta = mat[posDreaptaI][posDreaptaJ];
                        apaDreapta = returnWater(entitiesDreapta);
                        plantaDreapta = returnPlant(entitiesDreapta);
                    }
                    if (existaJos != 0) {
                        List<Entity> entitiesJos = mat[posJosI][posJosJ];
                        apaJos = returnWater(entitiesJos);
                        plantaJos = returnPlant(entitiesJos);
                    }
                    if (existaSus != 0) {
                        List<Entity> entitiesSus = mat[posSusI][posSusJ];
                        apaSus = returnWater(entitiesSus);
                        plantaSus = returnPlant(entitiesSus);
                    }
                    double calitateApa = 0.0;
                    int pozX = -1, pozY = -1;
                    if (apaSus != null && plantaSus != null) {
                        if (apaSus.waterQuality() > calitateApa) {
                            calitateApa = apaSus.waterQuality();
                            pozX = posSusI;
                            pozY = posSusJ;
                        }
                    }
                    if (apaJos != null && plantaJos != null) {
                        if (apaJos.waterQuality() > calitateApa) {
                            calitateApa = apaJos.waterQuality();
                            pozX = posJosI;
                            pozY = posJosJ;
                        }
                    }
                    if (apaDreapta != null && plantaDreapta != null) {
                        if (apaDreapta.waterQuality() > calitateApa) {
                            calitateApa = apaDreapta.waterQuality();
                            pozX = posDreaptaI;
                            pozY = posDreaptaJ;
                        }
                    }
                    if (apaStanga != null && plantaStanga != null) {
                        if (apaStanga.waterQuality() > calitateApa) {
                            calitateApa = apaStanga.waterQuality();
                            pozX = posStangaI;
                            pozY = posStangaJ;
                        }
                    }
                    if (pozX == -1) {
                        if (apaSus != null && plantaSus != null) {
                            if (apaSus.waterQuality() > calitateApa) {
                                calitateApa = apaSus.waterQuality();
                                pozX = posSusI;
                                pozY = posSusJ;
                            }
                        }
                        if (apaJos != null && plantaJos != null) {
                            if (apaJos.waterQuality() > calitateApa) {
                                calitateApa = apaJos.waterQuality();
                                pozX = posJosI;
                                pozY = posJosJ;
                            }
                        }
                        if (apaDreapta != null && plantaDreapta != null) {
                            if (apaDreapta.waterQuality() > calitateApa) {
                                calitateApa = apaDreapta.waterQuality();
                                pozX = posDreaptaI;
                                pozY = posDreaptaJ;
                            }
                        }
                        if (apaStanga != null && plantaStanga != null) {
                            if (apaStanga.waterQuality() > calitateApa) {
                                calitateApa = apaStanga.waterQuality();
                                pozX = posStangaI;
                                pozY = posStangaJ;
                            }
                        }
                        if (pozX == -1) {
                            if (apaSus != null || plantaSus != null) {
                                if (plantaSus != null) {
                                    pozX = posSusI;
                                    pozY = posSusJ;
                                }
                            } else if (apaDreapta != null || plantaDreapta != null) {
                                if (plantaDreapta != null) {
                                    pozX = posDreaptaI;
                                    pozY = posDreaptaJ;
                                }
                            } else if (apaJos != null || plantaJos != null) {
                                if (plantaJos != null) {
                                    pozX = posJosI;
                                    pozY = posJosJ;
                                }
                            } else if (apaStanga != null || plantaStanga != null) {
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
                            }
                            if (apaDreapta != null || plantaDreapta != null) {
                                if (apaDreapta != null) {
                                    if (apaDreapta.waterQuality() > calitateApa) {
                                        calitateApa = apaDreapta.waterQuality();
                                        pozX = posDreaptaI;
                                        pozY = posDreaptaJ;
                                    }
                                }
                            }
                            if (apaJos != null || plantaJos != null) {
                                if (apaJos != null) {
                                    if (apaJos.waterQuality() > calitateApa) {
                                        calitateApa = apaJos.waterQuality();
                                        pozX = posJosI;
                                        pozY = posJosJ;
                                    }
                                }
                            }
                            if (apaStanga != null || plantaStanga != null) {
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
                        if (existaSus == 1) {
                            pozX = posSusI;
                            pozY = posSusJ;
                        } else if (existaDreapta == 1) {
                            pozX = posDreaptaI;
                            pozY = posDreaptaJ;
                        } else if (existaJos == 1) {
                            pozX = posJosI;
                            pozY = posJosJ;
                        } else if (existaStanga == 1) {
                            pozX = posStangaI;
                            pozY = posStangaJ;
                        }
                    }
                    if (animalul != null) {
                        if (animalul.getState().equals("Carnivore")
                                || animalul.getState().equals("Parasite")) {
                            List<Entity> patratica = mat[pozX][pozY];
                            Animal animalPatratica = returnAnimal(patratica);
                            patratica.add(animalul);
                            entities.remove(animalul);
                            if (animalPatratica != null) {
                                animalul.setMass(animalul.getMass() + animalPatratica.getMass());
                                okInterAnimal = 1;
                                //interactiune animal-sol
                                patratica.remove(animalPatratica);
                            } else {
                                Plant plantaCurenta = returnPlant(patratica);
                                Water apaCurenta = returnWater(patratica);
                                if (plantaCurenta != null) {
                                    plantaCurenta.setMaturityLevel("dead");
                                    animalul.setMass(animalul.getMass() + plantaCurenta.getMass());
                                    animalul.setState("well-fed");
                                    okInterAnimal = 1;
                                }
                                if (apaCurenta != null) {
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
                            Plant plantaCurenta = returnPlant(patratica);
                            Water apaCurenta = returnWater(patratica);
                            if (plantaCurenta != null) {
                                plantaCurenta.setMaturityLevel("dead");
                                animalul.setMass(animalul.getMass() + plantaCurenta.getMass());
                                okInterAnimal = 1;
                                // interactiune animal_soil cu MagicNumbersDouble.half.getNumar()
                            }
                            if (apaCurenta != null) {
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
            }
            // node.put("baterie", robotel.getBattery());
            node.put("timestamp", command.getTimestamp());
            output.add(node);
        }
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        WRITER.writeValue(outputFile, output);
    }
}
