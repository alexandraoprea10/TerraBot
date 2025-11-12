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
public class Main {

    private Main(){
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final ObjectWriter WRITER = MAPPER.writer().withDefaultPrettyPrinter();

    public static double calculate_probabilitate(List <Entity> entities) {
        double calcul_probabilitate = 0.0;
        for (int k = 0; k < entities.size(); k++) {
            Entity entity = entities.get(k);
            if (entity.isAir()) {
                Air aer = (Air) entity;
                calcul_probabilitate = calcul_probabilitate + aer.air_toxicity();
            } else if (entity.isSoil()) {
                Soil sol = (Soil) entity;
                calcul_probabilitate = calcul_probabilitate + sol.probability_attack();
            } else if (entity.isPlant()) {
                Plant plantuta = (Plant) entity;
                calcul_probabilitate = calcul_probabilitate + plantuta.probability_attack();
            } else if (entity.isAnimal()) {
                Animal animalut = (Animal) entity;
                calcul_probabilitate = calcul_probabilitate + animalut.probability_attack();
            }
        }
        return calcul_probabilitate;
    }
    public static int calculate_objects(List <Entity> entities) {
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
    public static ObjectNode print_soil(Soil solulet) {
        ObjectNode soilNode = MAPPER.createObjectNode();
        soilNode.put("type", solulet.getClass().getSimpleName());
        soilNode.put("name", solulet.getName());
        soilNode.put("mass", solulet.getMass());
        soilNode.put("nitrogen", solulet.getNitrogen());
        soilNode.put("waterRetention", solulet.getWaterRetention());
        soilNode.put("soilpH", solulet.getSoilPh());
        soilNode.put("organicMatter", solulet.getOrganicMatter());
        soilNode.put("soilQuality", solulet.soil_quality());
        if (solulet.isForest())
            soilNode.put("leafLitter", ((ForestSoil) solulet).getLeafLitter());
        else if (solulet.isSwamp())
            soilNode.put("waterLogging", ((SwampSoil) solulet).getWaterLogging());
        else if (solulet.isDesertSoil())
            soilNode.put("salinity", ((DesertSoil) solulet).getSalinity());
        else if (solulet.isGrassland())
            soilNode.put("rootDensity", ((GrasslandSoil) solulet).getRootDensity());
        else if (solulet.isTundra())
            soilNode.put("permafrostDepth", ((TundraSoil) solulet).getPermafrostDepth());
        return soilNode;
    }
    public static ObjectNode print_plant(Plant plantuta) {
        ObjectNode plantNode = MAPPER.createObjectNode();
        plantNode.put("type", plantuta.getClass().getSimpleName());
        plantNode.put("name", plantuta.getName());
        plantNode.put("mass", plantuta.getMass());
        return plantNode;
    }
    public static ObjectNode print_animal(Animal animalut) {
        ObjectNode animalNode = MAPPER.createObjectNode();
        animalNode.put("type", animalut.getClass().getSimpleName());
        animalNode.put("name", animalut.getName());
        animalNode.put("mass", animalut.getMass());
        return  animalNode;
    }
    public static ObjectNode print_water(Water apita) {
        ObjectNode waterNode = MAPPER.createObjectNode();
        waterNode.put("type", apita.getType());
        waterNode.put("name", apita.getName());
        waterNode.put("mass", apita.getMass());
        // waterNode.put("scanat", apita.getisScanned());
        return waterNode;
    }
    public static ObjectNode print_air(Air airut) {
        ObjectNode airNode = MAPPER.createObjectNode();
        airNode.put("type", airut.getClass().getSimpleName());
        airNode.put("name", airut.getName());
        airNode.put("mass", airut.getMass());
        airNode.put("humidity", airut.getHumidity());
        airNode.put("temperature", airut.getTemperature());
        airNode.put("oxygenLevel", airut.getOxygenLevel());
        airNode.put("airQuality", airut.getAir_quality());
        return airNode;
    }
    public static void print_air_without_event(Air airut, ObjectNode airNode) {
        if (airut.isMountain())
            airNode.put("altitude", ((MountainAir) airut).getAltitude());
        else if (airut.isTemperate())
            airNode.put("pollenLevel", ((TemperateAir) airut).getPollenLevel());
        else if (airut.isTropical())
            airNode.put("co2Level", ((TropicalAir) airut).getCo2Level());
        else if (airut.isPolar())
            airNode.put("iceCrystalConcentration", ((Polar) airut).getIceCrystalConcentration());
        else if (airut.isDesert())
            airNode.put("dustParticles", ((DesertAir) airut).getDustParticles());
    }
    public static void print_air_with_event(Air airut,  ObjectNode airNode) {
        if (airut.isMountain())
            airNode.put("numberOfHikers", ((MountainAir) airut).getNumberOfHikers());
        else if (airut.isTemperate())
            airNode.put("season", ((TemperateAir) airut).getSeason());
        else if (airut.isTropical())
            airNode.put("rainfall", ((TropicalAir) airut).getRainfall());
        else if (airut.isPolar())
            airNode.put("windSpeed", ((Polar) airut).getWindSpeed());
        else if (airut.isDesert())
            airNode.put("desertStorm", ((DesertAir) airut).getDesertStorm());
    }
    public static boolean exist_plant(List <Entity> entities) {
        for (int i = 0 ; i <  entities.size() ; i++) {
            if (entities.get(i).isPlant())
                return true;
        }
        return false;
    }
    public static boolean exist_animal(List <Entity> entities) {
        for (int i = 0 ; i <  entities.size() ; i++) {
            if (entities.get(i).isAnimal())
                return true;
        }
        return false;
    }
    public static boolean exist_water(List <Entity> entities) {
        for (int i = 0 ; i <  entities.size() ; i++) {
            if (entities.get(i).isWater())
                return true;
        }
        return false;
    }
    public static Plant return_plant(List <Entity> entities) {
        for (int i = 0 ; i <   entities.size() ; i++) {
            Entity entity = entities.get(i);
            if (entity.isPlant())
                return (Plant) entity;
        }
        return null;
    }
    public static Animal return_animal(List <Entity> entities) {
        for (int i = 0 ; i <   entities.size() ; i++) {
            Entity entity = entities.get(i);
            if (entity.isAnimal())
                return (Animal) entity;
        }
        return null;
    }
    public static Soil return_soil(List <Entity> entities) {
        for (int i = 0 ; i <   entities.size() ; i++) {
            Entity entity = entities.get(i);
            if (entity.isSoil())
                return (Soil) entity;
        }
        return null;
    }
    public static Water return_water(List <Entity> entities) {
        for (int i = 0 ; i <   entities.size() ; i++) {
            Entity entity = entities.get(i);
            if (entity.isWater())
                return (Water) entity;
        }
        return null;
    }
    public static Air return_air(List <Entity> entities) {
        for (int i = 0 ; i <   entities.size() ; i++) {
            Entity entity = entities.get(i);
            if (entity.isAir())
                return (Air) entity;
        }
        return null;
    }
    public static void interactiuni_every_iteration(List <Entity> entities, int ok_inter_animal) {
        Plant planta =  return_plant(entities);
        Animal animal =  return_animal(entities);
        Soil soil =  return_soil(entities);
        Water water =  return_water(entities);
        Air air =  return_air(entities);
        if (air != null && animal != null && air.toxicity().equals("toxic"))
            animal.setState("sick");
//        if (soil != null && planta != null)
//            planta.setOxygen_curent(planta.getOxygen_curent() + 0.2);
        if (soil != null && planta != null) {
            planta.setNivel_crestere(planta.getNivel_crestere() + 0.2);
        }
        if (water != null && planta != null && water.getisScanned()) {
            planta.setNivel_crestere(planta.getNivel_crestere() + 0.2);
        }
        if (planta != null && air != null && planta.getisScanned()) {
            if (planta.getNivel_crestere() >= 1.0) {
                if (planta.getMaturity_level().equals("young"))
                    planta.setMaturity_level("mature");
                else if (planta.getMaturity_level().equals("mature"))
                    planta.setMaturity_level("old");
                else if (planta.getMaturity_level().equals("old"))
                    planta.setMaturity_level("dead");
                planta.setNivel_crestere(0);
            }
            air.setOxygenLevel(Math.round((air.getOxygenLevel() + planta.oxigen_from_plant() + planta.maturity_oxigen_level()) * 100.0) / 100.0);
            air.setAir_quality(Math.round(air.air_quality() * 100.0) / 100.0);
        }
        if (ok_inter_animal == 1) {
            if (planta != null && water != null) {
                soil.setOrganicMatter(soil.getOrganicMatter() + 0.8);
            }
            else if (planta != null || water != null) {
                soil.setOrganicMatter(soil.getOrganicMatter() + 0.5);
            }
        }
        if (animal != null && water != null && animal.getisScanned())
            animal.setMass(animal.getMass() + water.getMass());
    }
    public static void interactiuni_every_two_iterations(List <Entity> entities) {
        Plant planta = return_plant(entities);
        Animal animal = return_animal(entities);
        Soil soil = return_soil(entities);
        Water water = return_water(entities);
        Air air = return_air(entities);
        if (water != null && air != null && water.getisScanned()) {
            double result = Math.round((air.getHumidity() + 0.1) * 100.0) / 100.0;
            air.setHumidity(result);
            air.setAir_quality(Math.round(air.air_quality() * 100.0) / 100.0);
        }
        if (water != null && soil != null && water.getisScanned()) {
            double result = Math.round((soil.getWaterRetention() + 0.1) * 100.0) / 100.0;
            soil.setWaterRetention(result);
        }
    }
    public static void muta_robotelul(Robot robotel, List <Entity>[][] mat, int dimension) {
        int pos_stanga_i = robotel.getPoz_x();
        int pos_stanga_j = robotel.getPoz_y() - 1;

        int pos_sus_i = robotel.getPoz_x() - 1;
        int pos_sus_j = robotel.getPoz_y();

        int pos_dreapta_i = robotel.getPoz_x();
        int pos_dreapta_j = robotel.getPoz_y() + 1;

        int pos_jos_i = robotel.getPoz_x() + 1;
        int pos_jos_j = robotel.getPoz_y();

        int scor_stanga = -2;
        int scor_dreapta = -2;
        int scor_jos = -2;
        int scor_sus = -2;
        int pozitie_mutare_i = 0;
        int pozitie_mutare_j = 0;
        int scor_minim = 9999;
        // verific pentru STANGA
        ObjectNode neighbors = MAPPER.createObjectNode();
        if (pos_jos_i >= 0 && pos_jos_j >= 0 && pos_jos_i < dimension && pos_jos_j < dimension) {
            // iau scorul
            List<Entity> entities = mat[pos_jos_i][pos_jos_j];
            scor_jos = entities.get(0).get_attack();
            // node.put("jos", scor_jos);
        }
        if (pos_dreapta_i >= 0 && pos_dreapta_j >= 0 && pos_dreapta_i < dimension && pos_dreapta_j < dimension) {
            // iau scorul
            List<Entity> entities = mat[pos_dreapta_i][pos_dreapta_j];
            scor_dreapta = entities.get(0).get_attack();
            // node.put("dreapta", scor_dreapta);
        }
        if (pos_sus_i >= 0 && pos_sus_j >= 0 && pos_sus_i < dimension && pos_sus_j < dimension) {
            // iau scorul
            List<Entity> entities = mat[pos_sus_i][pos_sus_j];
            scor_sus = entities.get(0).get_attack();
            // node.put("sus", scor_sus);
        }
        if (pos_stanga_i >= 0 && pos_stanga_j >= 0 && pos_stanga_i < dimension && pos_stanga_j < dimension) {
            List<Entity> entities = mat[pos_stanga_i][pos_stanga_j];
            scor_stanga = entities.get(0).get_attack();
            // node.put("stanga", scor_stanga);
        }
        if (scor_dreapta >= 0 && scor_dreapta < scor_minim) {
            scor_minim = scor_dreapta;
            pozitie_mutare_i = pos_dreapta_i;
            pozitie_mutare_j = pos_dreapta_j;
        }
        if (scor_jos >= 0 && scor_jos < scor_minim) {
            List<Entity> entities = mat[pos_jos_i][pos_jos_j];
            scor_minim = scor_jos;
            pozitie_mutare_i = pos_jos_i;
            pozitie_mutare_j = pos_jos_j;
        }
        if (scor_sus >= 0 && scor_sus < scor_minim) {
            scor_minim = scor_sus;
            pozitie_mutare_i = pos_sus_i;
            pozitie_mutare_j = pos_sus_j;
        }
        if (scor_stanga >= 0 && scor_stanga < scor_minim) {
            scor_minim = scor_stanga;
            pozitie_mutare_i = pos_stanga_i;
            pozitie_mutare_j = pos_stanga_j;
        }
        if (robotel.getBattery() - scor_minim >= 0) {
            robotel.setPoz_x(pozitie_mutare_i);
            robotel.setPoz_y(pozitie_mutare_j);
        }
        robotel.setBattery(robotel.getBattery() - scor_minim);
    }
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
        List <SimulationInput> simulations = inputLoader.getSimulations();
        SimulationInput simInput = simulations.get(0);
        String dimension_string = simInput.getTerritoryDim();
        int battery = simInput.getEnergyPoints();
        Robot robotel = new Robot(battery, 0, 0, false);
        int dimension = Integer.parseInt(dimension_string.split("x")[0]);
        List<Entity>[][] mat = new List[dimension][dimension];
        for (int i = 0 ; i < dimension; i++){
            for (int j = 0 ; j < dimension; j++){
                mat[i][j] = new ArrayList<>();
            }
        }
        int energyPoints = simInput.getEnergyPoints();
        TerritorySectionParamsInput territory = simInput.getTerritorySectionParams();
        List <SoilInput> soil = territory.getSoil();
        List <PlantInput> plant = territory.getPlants();
        List <AnimalInput> animal = territory.getAnimals();
        List <WaterInput> water = territory.getWater();
        List <AirInput> air = territory.getAir();
        for (int i = 0 ; i < territory.getSoil().size() ; i++) {
            SoilInput sol = territory.getSoil().get(i);
            for (int k = 0 ; k < sol.getSections().size(); k++) {
                PairInput loc = sol.getSections().get(k);
            Soil solul = null;
            if (sol.getType().equals("ForestSoil")) {
                solul = new ForestSoil(sol.getName(), sol.getMass(), sol.getNitrogen(), sol.getWaterRetention(), sol.getSoilpH(), sol.getOrganicMatter(), sol.getLeafLitter());
            }
            else if (sol.getType().equals("SwampSoil")) {
                solul = new SwampSoil(sol.getName(), sol.getMass(), sol.getNitrogen(), sol.getWaterRetention(), sol.getSoilpH(), sol.getOrganicMatter(), sol.getWaterLogging());
            }
            else if (sol.getType().equals("DesertSoil")) {
                solul = new DesertSoil(sol.getName(), sol.getMass(), sol.getNitrogen(), sol.getWaterRetention(), sol.getSoilpH(), sol.getOrganicMatter(), sol.getSalinity());
            }
            else if (sol.getType().equals("GrasslandSoil")) {
                solul = new GrasslandSoil(sol.getName(), sol.getMass(), sol.getNitrogen(), sol.getWaterRetention(), sol.getSoilpH(), sol.getOrganicMatter(), sol.getRootDensity());
            }
            else if (sol.getType().equals("TundraSoil")) {
                solul = new TundraSoil(sol.getName(), sol.getMass(), sol.getNitrogen(), sol.getWaterRetention(), sol.getSoilpH(), sol.getOrganicMatter(), sol.getPermafrostDepth());
            }
                mat[loc.getX()][loc.getY()].add(solul);
            }
        }
        for (int i = 0 ; i < territory.getPlants().size() ; i++) {
            PlantInput planta = territory.getPlants().get(i);
            for (int k = 0 ; k < planta.getSections().size(); k++) {
                PairInput loc = planta.getSections().get(k);
            Plant plantuta = null;
            if (planta.getType().equals("FloweringPlants")) {
                plantuta = new FloweringPlants(planta.getName(), planta.getMass(), 0,"young");
            }
            else if (planta.getType().equals("GymnospermsPlants")) {
                plantuta = new GymnospermsPlants(planta.getName(), planta.getMass(), 0, "young");
            }
            else if (planta.getType().equals("Ferns")) {
                plantuta = new Ferns(planta.getName(), planta.getMass(), 0, "young");
            }
            else if (planta.getType().equals("Mosses")) {
                plantuta = new Mosses(planta.getName(), planta.getMass(), 0, "young");
            }
            else if (planta.getType().equals("Algae")) {
                plantuta = new Algae(planta.getName(), planta.getMass(), 0, "young");
            }
                mat[loc.getX()][loc.getY()].add(plantuta);
            }

        }
        for (int i = 0 ; i < territory.getAnimals().size() ; i++){
            AnimalInput animalul = territory.getAnimals().get(i);
            for (int k = 0 ; k < animalul.getSections().size(); k++) {
                PairInput loc = animalul.getSections().get(k);
            Animal animalut = null;
            if (animalul.getType().equals("Herbivores")) {
                animalut = new Herbivores(animalul.getName(), animalul.getMass(), "hungry");
            }
            else if (animalul.getType().equals("Carnivores")) {
                animalut = new Carnivores(animalul.getName(), animalul.getMass(), "hungry");
            }
            else if (animalul.getType().equals("Omnivores")) {
                animalut = new Omnivores(animalul.getName(), animalul.getMass(), "hungry");
            }
            else if (animalul.getType().equals("Detritivores")) {
                animalut = new Detritivores(animalul.getName(), animalul.getMass(), "hungry");
            }
            else if (animalul.getType().equals("Parasites")) {
                animalut = new Parasites(animalul.getName(), animalul.getMass(), "hungry");
            }
                mat[loc.getX()][loc.getY()].add(animalut);
            }
        }
        for (int i = 0 ; i < territory.getWater().size() ; i++){
            WaterInput apa = territory.getWater().get(i);
            for (int k = 0 ; k < apa.getSections().size(); k++) {
                PairInput loc = apa.getSections().get(k);
            Water apita = new Water(apa.getName(), apa.getMass(), apa.getType(), apa.getSalinity(), apa.getPH(), apa.getPurity(), apa.getTurbidity(), apa.getContaminantIndex(), apa.isFrozen());
                mat[loc.getX()][loc.getY()].add(apita);
            }
        }
        for (int i = 0 ; i < territory.getAir().size() ; i++){
            AirInput aer = territory.getAir().get(i);
            for (int k = 0 ; k < aer.getSections().size(); k++) {
                PairInput loc = aer.getSections().get(k);
            Air aerut = null;
            if (aer.getType().equals("TropicalAir")) {
                // String nume, double mass, double humidity, double temperature, double oxygenLevel,
                aerut = new TropicalAir(aer.getName(), aer.getMass(), aer.getHumidity(), aer.getTemperature(), aer.getOxygenLevel(), aer.getCo2Level());
            }
            else if (aer.getType().equals("PolarAir")) {
                aerut = new Polar(aer.getName(), aer.getMass(), aer.getHumidity(), aer.getTemperature(), aer.getOxygenLevel(), aer.getIceCrystalConcentration());
            }
            else if (aer.getType().equals("TemperateAir")) {
                aerut = new TemperateAir(aer.getName(), aer.getMass(), aer.getHumidity(), aer.getTemperature(), aer.getOxygenLevel(), aer.getPollenLevel());
            }
            else if (aer.getType().equals("DesertAir")) {
                aerut = new DesertAir(aer.getName(), aer.getMass(), aer.getHumidity(), aer.getTemperature(), aer.getOxygenLevel(), aer.getDustParticles());
            }
            else if (aer.getType().equals("MountainAir")) {
                aerut = new MountainAir(aer.getName(), aer.getMass(), aer.getHumidity(), aer.getTemperature(), aer.getOxygenLevel(), aer.getAltitude());
            }
                aerut.setAir_quality(aerut.air_quality());
                mat[loc.getX()][loc.getY()].add(aerut);
            }
        }
        int iteratii = 0;
        int stamps = 0;
        int ok_schimbari_meteo = 0;
        int ok_scanari = 0;
        int s_a_scanat = 0;
        int incepe_iteratie = 0;
        int previous_stamp = 0;
        int ok_inter_animal = 0;
        int inceput_iteratie_animal = 0;
        List <CommandInput> commands = inputLoader.getCommands();
        int ok_simulation = 0;
        for (int i = 0; i < commands.size(); i++) {
            // double soilQuality = 0.0;
            double airQuality = 0.0;
            CommandInput command = commands.get(i);
            if ((command.getTimestamp() - previous_stamp >= stamps) && (robotel.isCharging() == true)) {
                robotel.setIsCharging(false);
                stamps = 0;
                previous_stamp = 0;
            }
            if (ok_scanari == 1) {
                if (s_a_scanat == 0) {
                    s_a_scanat = 1;
                    incepe_iteratie = iteratii;
                }
            }
            iteratii++;
            if (ok_scanari == 1) {
                for (int a = 0; a < dimension; a++) {
                    for (int b = 0; b < dimension; b++) {
                        List<Entity> entities = mat[a][b];
                        // node.put("adauga", return_plant(entities).getOxygen_curent());
                        interactiuni_every_iteration(entities, ok_inter_animal);
                        if (iteratii > incepe_iteratie && (iteratii - incepe_iteratie) % 2 == 0) {
//                            Water api =  return_water(entities);
//                            node.put("message", "Am intrat");
                            interactiuni_every_two_iterations(entities);
                        }
                    }
                }
            }
            for (int a = 0; a < dimension; a++) {
                for (int b = 0; b < dimension; b++) {
                    List<Entity> entities = mat[b][a];
                    double calcul_probabilitate = calculate_probabilitate(entities);
                    int obiecte = calculate_objects(entities);
                    double mean = -1, score = -1;
                    if (obiecte == 0)
                        mean = -1;
                    else mean = Math.abs(calcul_probabilitate / obiecte);
                    int result = (int) Math.round(mean);
                    for (int p = 0; p < entities.size(); p++) {
                        Entity entity = entities.get(p);
                        entity.set_attack(result);
                    }
                }
            }
            ObjectNode node = MAPPER.createObjectNode();
            node.put("command", command.getCommand());
            if (command.getCommand().equals("startSimulation")) {
                node.put("message", "Simulation has started.");
                ok_simulation = 1;
//                for (int a = 0; a < dimension; a++) {
//                    for (int b = 0; b < dimension; b++) {
//                        List<Entity> entities = mat[b][a];
//                        ObjectNode entityNode = MAPPER.createObjectNode();
//                        ArrayNode entitiesNode = MAPPER.createArrayNode();
//                        entitiesNode.add(b);
//                        entitiesNode.add(a);
//                        double calcul_probabilitate = calculate_probabilitate(entities);
//                        int obiecte = calculate_objects(entities);
//                        double mean = -1, score = -1;
//                        if (obiecte == 0)
//                            mean = -1;
//                        else mean = Math.abs(calcul_probabilitate / obiecte);
//                        int result = (int) Math.round(mean);
//                        for (int p = 0; p < entities.size(); p++) {
//                            Entity entity = entities.get(p);
//                            entity.set_attack(result);
//                        }
//                    }
//                }
            }
            else if (command.getCommand().equals("printEnvConditions")) {
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.getBattery() <= 0) {
                    node.put("message", "ERROR: Not enough battery left. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    ObjectNode env = MAPPER.createObjectNode();
                    List<Entity> entities = mat[robotel.getPoz_x()][robotel.getPoz_y()];
                    for (int k = 0; k < entities.size(); k++) {
                        Entity entity = entities.get(k);
                        if (entity.isSoil()) {
                            Soil solulet = (Soil) entity;
                            ObjectNode soilNode = print_soil(solulet);
                            env.set("soil", soilNode);
                        } else if (entity.isPlant()) {
                            Plant plantuta = (Plant) entity;
                            ObjectNode plantNode = print_plant(plantuta);
                            env.set("plants", plantNode);
                        } else if (entity.isAnimal()) {
                            Animal animalut = (Animal) entity;
                            ObjectNode animalNode = print_animal(animalut);
                            env.set("animals", animalNode);
                        } else if (entity.isWater()) {
                            Water apita = (Water) entity;
                            ObjectNode waterNode = print_water(apita);
                            env.set("water", waterNode);
                        } else if (entity.isAir()) {
                            Air airut = (Air) entity;
                            ObjectNode airNode = print_air(airut);
                            if (ok_schimbari_meteo == 0) {
                                print_air_without_event(airut, airNode);
                            } else {
                                print_air_with_event(airut, airNode);
                            }
                            env.set("air", airNode);
                        }
                    }
                    node.set("output", env);
                }
            }
            else if (command.getCommand().equals("printMap")) {
                //  "section" : [ 0, 0 ],
                //    "totalNrOfObjects" : 3,
                //    "airQuality" : "moderate",
                //    "soilQuality" : "poor"
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.getBattery() <= 0) {
                    node.put("message", "ERROR: Not enough battery left. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
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
                                if (entities.get(p).isWater())
                                    contor++;
                                if (entities.get(p).isPlant())
                                    contor++;
                                if (entities.get(p).isAnimal())
                                    contor++;
                            }
                            entityNode.put("totalNrOfObjects", contor);
                            String air_quality = null;
                            String soil_quality = null;
                            for (int k = 0; k < entities.size(); k++) {
                                Entity entity = entities.get(k);
                                if (entity.isAir()) {
                                    Air aer = (Air) entity;
                                    double valoare_aer = aer.getAir_quality();
                                    air_quality = aer.result_air(valoare_aer);
                                } else if (entity.isSoil()) {
                                    Soil sol = (Soil) entity;
                                    double valoare_sol = sol.soil_quality();
                                    soil_quality = sol.result_soil(valoare_sol);
                                }
                            }
                            // entityNode.put("scor", entities.get(0).get_attack());
                            entityNode.put("airQuality", air_quality);
                            entityNode.put("soilQuality", soil_quality);
                            mapNode.add(entityNode);
                        }
                    }
                    node.set("output", mapNode);
                }
            } else if (command.getCommand().equals("endSimulation")) {
                ok_simulation = 0;
                node.put("message", "Simulation has ended.");
            }
            else if (command.getCommand().equals("moveRobot")) {
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.getBattery() < 0) {
                    node.put("message", "ERROR: Not enough battery left. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    robotel.setFosta_baterie(robotel.getBattery());
                    muta_robotelul(robotel, mat, dimension);
                    node.put("command", command.getCommand());
                    if (robotel.getBattery() < 0) {
                        robotel.setBattery(robotel.getFosta_baterie());
                        node.put("message", "ERROR: Not enough battery left. Cannot perform action");
                    } else {
                        node.put("message", "The robot has successfully moved to position (" + robotel.getPoz_x() + ", " + robotel.getPoz_y() + ").");
                    }
                }
            }
            else if (command.getCommand().equals("scanObject")) {
                int se_poate = 0;
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else if (robotel.getBattery() < 7) {
                    node.put("message", "ERROR: Not enough battery left. Cannot perform action");
                }
                else {
                    ok_scanari = 1;
                    List<Entity> entities = mat[robotel.getPoz_x()][robotel.getPoz_y()];
                    String color = command.getColor();
                    String smell = command.getSmell();
                    String sound = command.getSound();
                    if (exist_water(entities) && (color.equals("none")) && (smell.equals("none") && (sound.equals("none")))) {
                        se_poate = 1;
                        Water apa = return_water(entities);
                        apa.setIsScanned(true);
                        apa.setMoment_scanare(iteratii);
                        // node.put("apa", apa.getisScanned());
                        node.put("message", "The scanned object is water.");
                    } else if (exist_plant(entities) && (!color.equals("none")) && (!smell.equals("none") && (sound.equals("none")))) {
                        se_poate = 1;
                        Plant planta = return_plant(entities);
                        planta.setIsScanned(true);
                        planta.setMoment_scanare(iteratii);
                        node.put("message", "The scanned object is a plant.");
                    } else if (exist_animal(entities) && (!color.equals("none")) && (!smell.equals("none") && (!sound.equals("none")))) {
                        se_poate = 1;
                        Animal animalul = return_animal(entities);
                        animalul.setIsScanned(true);
                        node.put("message", "The scanned object is an animal.");
                        inceput_iteratie_animal = iteratii;
                    }
                    else {
                        node.put("message", "ERROR: Object not found. Cannot perform action");
                    }
                    if (se_poate == 1)
                    robotel.setBattery(robotel.getBattery() - 7);
                }
            } else if (command.getCommand().equals("learnFact")) {
            } else if (command.getCommand().equals("improveEnvConditions")) {
            } else if (command.getCommand().equals("getEnergyStatus")) {
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    node.put("command", "getEnergyStatus");
                    node.put("message", "TerraBot has " + robotel.getBattery() + " energy points left.");
                }
            }
            else if (command.getCommand().equals("rechargeBattery")) {
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    robotel.setBattery(robotel.getBattery() + command.getTimeToCharge());
                    stamps = command.getTimeToCharge();
                    previous_stamp = command.getTimestamp();
                    node.put("message", "Robot battery is charging.");
                    robotel.setIsCharging(true);
                }
            }
            else if (command.getCommand().equals("changeWeatherConditions")) {
                if (ok_simulation == 0) {
                    node.put("message", "ERROR: Simulation not started. Cannot perform action");
                } else if (robotel.isCharging() == true) {
                    node.put("message", "ERROR: Robot still charging. Cannot perform action");
                } else {
                    ok_schimbari_meteo = 1;
                    List<Entity> entities = mat[robotel.getPoz_x()][robotel.getPoz_y()];
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
                                trop.setAir_quality(trop.update_air_quality());
                            } else if (aer.isPolar()) {
                                Polar polar = (Polar) aer;
                                String eveniment = command.getImprovementType();
                                double wind = command.getWindSpeed();
                                polar.setEvent(eveniment);
                                polar.setWindSpeed(wind);
                                polar.setAir_quality(polar.update_air_quality());
                            } else if (aer.isTemperate()) {
                                TemperateAir temperate = (TemperateAir) aer;
                                String eveniment = command.getImprovementType();
                                String seas = command.getSeason();
                                temperate.setEvent(eveniment);
                                temperate.setSeason(seas);
                                temperate.setAir_quality(temperate.update_air_quality());
                            } else if (aer.isDesert()) {
                                DesertAir desert = (DesertAir) aer;
                                String eveniment = command.getImprovementType();
                                boolean deser = command.isDesertStorm();
                                desert.setEvent(eveniment);
                                desert.setDesertStorm(deser);
                                desert.setAir_quality(desert.update_air_quality());
                            } else if (aer.isMountain()) {
                                MountainAir mountain = (MountainAir) aer;
                                String eveniment = command.getImprovementType();
                                int number = command.getNumberOfHikers();
                                mountain.setEvent(eveniment);
                                mountain.setNumberOfHikers(number);
                                mountain.setAir_quality(mountain.update_air_quality());
                            }
                        }
                    }
                    node.put("message", "The weather has changed.");
                }
            }
//            node.put("baterie", robotel.getBattery());
//            node.put("scor", mat[0][1].get(0).get_attack());
            List <Entity> entities = mat[0][0];
            Animal animalul = return_animal(entities);
            if (animalul != null && animalul.getisScanned()) {
                if (inceput_iteratie_animal < iteratii && (iteratii - inceput_iteratie_animal) % 2 == 0 && animalul.getisScanned()) {
                    int exista_stanga = 1;
                    int exista_dreapta = 1;
                    int exista_sus = 1;
                    int exista_jos = 1;
                    int pos_stanga_i = robotel.getPoz_x();
                    int pos_stanga_j = robotel.getPoz_y() - 1;
                    if (pos_stanga_j < 0 || pos_stanga_j >= dimension)
                        exista_stanga = 0;
                    int pos_sus_i = robotel.getPoz_x() - 1;
                    int pos_sus_j = robotel.getPoz_y();
                    if (pos_sus_i < 0 || pos_sus_i >= dimension)
                        exista_sus = 0;
                    int pos_dreapta_i = robotel.getPoz_x();
                    int pos_dreapta_j = robotel.getPoz_y() + 1;
                    if (pos_dreapta_j < 0 || pos_dreapta_j >= dimension)
                        exista_dreapta = 0;
                    int pos_jos_i = robotel.getPoz_x() + 1;
                    int pos_jos_j = robotel.getPoz_y();
                    if (pos_jos_i < 0 || pos_jos_i >= dimension)
                        exista_jos = 0;
                    Plant planta_stanga = null;
                    Water apa_stanga = null;
                    Plant planta_dreapta = null;
                    Water apa_dreapta = null;
                    Plant planta_jos = null;
                    Water apa_jos = null;
                    Plant planta_sus = null;
                    Water apa_sus = null;
                    if (exista_stanga != 0) {
                        List<Entity> entities_stanga = mat[pos_stanga_i][pos_stanga_j];
                        apa_stanga = return_water(entities_stanga);
                        planta_stanga = return_plant(entities_stanga);
                    }
                    if (exista_dreapta != 0) {
                        List<Entity> entities_dreapta = mat[pos_dreapta_i][pos_dreapta_j];
                        apa_dreapta = return_water(entities_dreapta);
                        planta_dreapta = return_plant(entities_dreapta);
                    }
                    if (exista_jos != 0) {
                        List<Entity> entities_jos = mat[pos_jos_i][pos_jos_j];
                        apa_jos = return_water(entities_jos);
                        planta_jos = return_plant(entities_jos);
                    }
                    if (exista_sus != 0) {
                        List<Entity> entities_sus = mat[pos_sus_i][pos_sus_j];
                        apa_sus = return_water(entities_sus);
                        planta_sus = return_plant(entities_sus);
                    }
                    double calitate_apa = 0.0;
                    int poz_x = -1, poz_y = -1;
                    if (apa_sus != null && planta_sus != null) {
                        if (apa_sus.water_quality() > calitate_apa) {
                            calitate_apa = apa_sus.water_quality();
                            poz_x = pos_sus_i;
                            poz_y = pos_sus_j;
                        }
                    }
                    if (apa_jos != null && planta_jos != null) {
                        if (apa_jos.water_quality() > calitate_apa) {
                            calitate_apa = apa_jos.water_quality();
                            poz_x = pos_jos_i;
                            poz_y = pos_jos_j;
                        }
                    }
                    if (apa_dreapta != null && planta_dreapta != null) {
                        if (apa_dreapta.water_quality() > calitate_apa) {
                            calitate_apa = apa_dreapta.water_quality();
                            poz_x = pos_dreapta_i;
                            poz_y = pos_dreapta_j;
                        }
                    }
                    if (apa_stanga != null && planta_stanga != null) {
                        if (apa_stanga.water_quality() > calitate_apa) {
                            calitate_apa = apa_stanga.water_quality();
                            poz_x = pos_stanga_i;
                            poz_y = pos_stanga_j;
                        }
                    }
                    if (poz_x == -1) {
                        if (apa_sus != null && planta_sus != null) {
                            if (apa_sus.water_quality() > calitate_apa) {
                                calitate_apa = apa_sus.water_quality();
                                poz_x = pos_sus_i;
                                poz_y = pos_sus_j;
                            }
                        }
                        if (apa_jos != null && planta_jos != null) {
                            if (apa_jos.water_quality() > calitate_apa) {
                                calitate_apa = apa_jos.water_quality();
                                poz_x = pos_jos_i;
                                poz_y = pos_jos_j;
                            }
                        }
                        if (apa_dreapta != null && planta_dreapta != null) {
                            if (apa_dreapta.water_quality() > calitate_apa) {
                                calitate_apa = apa_dreapta.water_quality();
                                poz_x = pos_dreapta_i;
                                poz_y = pos_dreapta_j;
                            }
                        }
                        if (apa_stanga != null && planta_stanga != null) {
                            if (apa_stanga.water_quality() > calitate_apa) {
                                calitate_apa = apa_stanga.water_quality();
                                poz_x = pos_stanga_i;
                                poz_y = pos_stanga_j;
                            }
                        }
                        if (poz_x == -1) {
                            if (apa_sus != null || planta_sus != null) {
                                if (planta_sus != null) {
                                    poz_x = pos_sus_i;
                                    poz_y = pos_sus_j;
                                }
                            } else if (apa_dreapta != null || planta_dreapta != null) {
                                if (planta_dreapta != null) {
                                    poz_x = pos_dreapta_i;
                                    poz_y = pos_dreapta_j;
                                }
                            } else if (apa_jos != null || planta_jos != null) {
                                if (planta_jos != null) {
                                    poz_x = pos_jos_i;
                                    poz_y = pos_jos_j;
                                }
                            } else if (apa_stanga != null || planta_stanga != null) {
                                if (planta_stanga != null) {
                                    poz_x = pos_stanga_i;
                                    poz_y = pos_stanga_j;
                                }
                            }
                        }
                        if (poz_x == -1) {
                            if (apa_sus != null || planta_sus != null) {
                                if (apa_sus != null) {
                                    if (apa_sus.water_quality() > calitate_apa) {
                                        calitate_apa = apa_sus.water_quality();
                                        poz_x = pos_sus_i;
                                        poz_y = pos_sus_j;
                                    }
                                }
                            }
                            if (apa_dreapta != null || planta_dreapta != null) {
                                if (apa_dreapta != null) {
                                    if (apa_dreapta.water_quality() > calitate_apa) {
                                        calitate_apa = apa_dreapta.water_quality();
                                        poz_x = pos_dreapta_i;
                                        poz_y = pos_dreapta_j;
                                    }
                                }
                            }
                            if (apa_jos != null || planta_jos != null) {
                                if (apa_jos != null) {
                                    if (apa_jos.water_quality() > calitate_apa) {
                                        calitate_apa = apa_jos.water_quality();
                                        poz_x = pos_jos_i;
                                        poz_y = pos_jos_j;
                                    }
                                }
                            }
                            if (apa_stanga != null || planta_stanga != null) {
                                if (apa_stanga != null) {
                                    if (apa_stanga.water_quality() > calitate_apa) {
                                        calitate_apa = apa_stanga.water_quality();
                                        poz_x = pos_stanga_i;
                                        poz_y = pos_stanga_j;
                                    }
                                }
                            }
                        }
                    }
                    if (poz_x == -1) {
                        if (exista_sus == 1) {
                            poz_x = pos_sus_i;
                            poz_y = pos_sus_j;
                        } else if (exista_dreapta == 1) {
                            poz_x = pos_dreapta_i;
                            poz_y = pos_dreapta_j;
                        } else if (exista_jos == 1) {
                            poz_x = pos_jos_i;
                            poz_y = pos_jos_j;
                        } else if (exista_stanga == 1) {
                            poz_x = pos_stanga_i;
                            poz_y = pos_stanga_j;
                        }
                    }
                    if (animalul != null) {
                        if (animalul.getState().equals("Carnivore") || animalul.getState().equals("Parasite")) {
                            List<Entity> patratica = mat[poz_x][poz_y];
                            Animal animal_patratica = return_animal(patratica);
                            patratica.add(animalul);
                            entities.remove(animalul);
                            if (animal_patratica != null) {
                                animalul.setMass(animalul.getMass() + animal_patratica.getMass());
                                ok_inter_animal = 1;
                                //interactiune animal-sol
                                patratica.remove(animal_patratica);
                            } else {
                                Plant planta_curenta = return_plant(patratica);
                                Water apa_curenta = return_water(patratica);
                                if (planta_curenta != null) {
                                    planta_curenta.setMaturity_level("dead");
                                    animalul.setMass(animalul.getMass() + planta_curenta.getMass());
                                    animalul.setState("well-fed");
                                    ok_inter_animal = 1;
                                    // interactiune animal_soil cu 0.5
                                }
                                if (apa_curenta != null) {
                                    double water_to_drink = Math.min(animalul.getMass() * 0.08, apa_curenta.getMass());
                                    apa_curenta.setMass(apa_curenta.getMass() - water_to_drink);
                                    animalul.setMass(animalul.getMass() + water_to_drink);
                                    // animalul.setState("well-fed");
                                    ok_inter_animal = 1;
                                    // interactiune animal_soil cu 0.5
                                }
                                // daca sunt !NULL amandoua atunci fac 0.8
                            }
                        } else {
                            List<Entity> patratica = mat[poz_x][poz_y];
                            patratica.add(animalul);
                            entities.remove(animalul);
                            Plant planta_curenta = return_plant(patratica);
                            Water apa_curenta = return_water(patratica);
                            if (planta_curenta != null) {
                                planta_curenta.setMaturity_level("dead");
                                animalul.setMass(animalul.getMass() + planta_curenta.getMass());
                                ok_inter_animal = 1;
                                // interactiune animal_soil cu 0.5
                            }
                            if (apa_curenta != null) {
                                double water_to_drink = Math.min(animalul.getMass() * 0.08, apa_curenta.getMass());
                                apa_curenta.setMass(apa_curenta.getMass() - water_to_drink);
                                animalul.setMass(animalul.getMass() + water_to_drink);
                                ok_inter_animal = 1;
                                // interactiune animal_soil cu 0.5
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