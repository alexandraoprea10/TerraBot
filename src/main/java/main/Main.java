package main;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CommandInput;
import fileio.InputLoader;
import fileio.SimulationInput;
import fileio.TerritorySectionParamsInput;
import main.air.Air;

import main.commands.PrintKnowledgeBase;
import main.commands.GetEnergyStatus;
import main.commands.MoveRobot;
import main.commands.PrintEnvConditions;
import main.commands.PrintMap;
import main.commands.ScanObject;
import main.createTerritory.AddWater;
import main.createTerritory.AddAir;
import main.createTerritory.AddAnimals;
import main.createTerritory.AddSoil;
import main.createTerritory.AddPlants;
import main.helpers.CalculateHelper;
import main.helpers.ExistingHelper;
import main.helpers.InteractionsHelper;
import main.helpers.MovingAnimalHelper;
import main.helpers.ReturnHelper;
import main.helpers.SetHelper;
import main.helpers.WeatherUpdateHelper;
import main.magicNumbers.MagicNumbersDouble;
import main.magicNumbers.MagicNumbersInt;
import main.plant.Plant;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.LinkedHashMap;
import java.util.ArrayList;

import static main.helpers.ExistingHelper.verificaMoarteaPlantuta;

/**
 * The entry point to this homework. It runs the checker that tests your implementation.
 */
public final class Main {

    private Main() {
    }

    private static final ObjectMapper MAPPER = new ObjectMapper();
    public static final ObjectWriter WRITER = MAPPER.writer().withDefaultPrettyPrinter();
    /**
     * MAIN
     * @param inputPath input
     * @param outputPath output
     * @throws IOException erori
     */
    public static void action(final String inputPath,
                              final String outputPath) throws IOException {

        InputLoader inputLoader = new InputLoader(inputPath);
        ArrayNode output = MAPPER.createArrayNode();
        AddSoil newSoil = new AddSoil();
        AddPlants newPlants = new AddPlants();
        AddAnimals newAnimals = new AddAnimals();
        AddWater newWater = new AddWater();
        AddAir newAir = new AddAir();
        int okSimulation = 0;
        List<SimulationInput> simulations = inputLoader.getSimulations();
        int contorComenzi = 0;
        for (int o = 0; o < simulations.size(); o++) {
            LinkedHashMap<String, List<String>> factsAndSubjects = new LinkedHashMap<>();
            LinkedHashMap<String, List<String>> scanObjects = new LinkedHashMap<>();
            SimulationInput simInput = simulations.get(o);
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
            TerritorySectionParamsInput territory =
                    simInput.getTerritorySectionParams();
            newSoil.addSoil(territory, mat);
            newPlants.addPlants(territory, mat);
            newAnimals.addAnimals(territory, mat);
            newWater.addWater(territory, mat);
            newAir.addAir(territory, mat);
            int iteratii;
            int fostaIteratie = 0;
            int stamps = 0;
            int okSchimbariMeteo = 0;
            int okScanari = 0;
            int sAScanat = 0;
            int incepeIteratie = 0;
            int previousStamp = 0;
            int okInterAnimal = 0;
            int inceputIteratieAnimal = 0;
            int mergeSubj;
            int iteratieAer = -1;
            double fostaCalitate = 0.0;
            Air aerSchimb = null;
            List<CommandInput> commands = inputLoader.getCommands();
            int nextSimulare = 0;
            int opresteSimulare = 0;
            for (int i = contorComenzi; i < commands.size(); i++) {
                int okPlanta = 0;
                List<Entity> entit = mat[robotel.getPozX()][robotel.getPozY()];
                verificaMoarteaPlantuta(entit);
                CommandInput command = commands.get(i);
                iteratii = command.getTimestamp();
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
                if (iteratieAer + 2 == iteratii && aerSchimb != null) {
                    aerSchimb.setAirQuality(fostaCalitate);
                    iteratieAer = MagicNumbersInt.minustrei.getNumar();
                }
                if (okScanari == 1) {
                    // interactiuni pentru fiacre patratica + timestamps
                    InteractionsHelper.doInteractions(dimension, mat, iteratii,
                            fostaIteratie, robotel, incepeIteratie,
                            okInterAnimal, okSchimbariMeteo);
                }
                // muta animalul
                MovingAnimalHelper.moveAnimal(dimension, inceputIteratieAnimal,
                        mat, okInterAnimal, iteratii);
                // calculeaza probabilitatea fiecarei patratele
                CalculateHelper.calculateAttack(dimension, mat);
                ObjectNode node = MAPPER.createObjectNode();
                node.put("command", command.getCommand());
                if (command.getCommand().equals("startSimulation")) {
                    if (okSimulation == 1) {
                        node.put("message", "ERROR: Simulation already started."
                                + " Cannot perform action");
                    } else {
                        if (opresteSimulare == 1) {
                            nextSimulare = 1;
                            contorComenzi = i + 1;
                        }
                        node.put("message", "Simulation has started.");
                        okSimulation = 1;
                    }
                } else if (command.getCommand().equals("printEnvConditions")) {
                    PrintEnvConditions cmd = new PrintEnvConditions();
                    cmd.execute(okSimulation, node, opresteSimulare, contorComenzi, nextSimulare, i,
                            robotel, mat, MAPPER, dimension, command, factsAndSubjects);
                } else if (command.getCommand().equals("printMap")) {
                    PrintMap cmd = new PrintMap();
                    cmd.execute(okSimulation, node, opresteSimulare, contorComenzi, nextSimulare, i,
                            robotel, mat, MAPPER, dimension, command, factsAndSubjects);
                } else if (command.getCommand().equals("endSimulation")) {
                    if (okSimulation == 0) {
                        node.put("message", "ERROR: Simulation not started. Cannot perform action");
                    } else {
                        opresteSimulare = 1;
                        okSimulation = 0;
                        contorComenzi = i + 1;
                        node.put("message", "Simulation has ended.");
                    }
                } else if (command.getCommand().equals("moveRobot")) {
                    MoveRobot cmd = new MoveRobot();
                    cmd.execute(okSimulation, node, opresteSimulare, contorComenzi, nextSimulare, i,
                            robotel, mat, MAPPER, dimension, command, factsAndSubjects);
                } else if (command.getCommand().equals("scanObject")) {
                    int sePoate;
                    if (okSimulation == 0) {
                        node.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                    } else if (robotel.isCharging()) {
                        node.put("message",
                                "ERROR: Robot still charging. Cannot perform action");
                    } else if (robotel.getBattery()
                            < MagicNumbersInt.sapte.getNumar()) {
                        node.put("message",
                                "ERROR: Not enough energy to perform action");
                    } else {
                        okScanari = 1;
                        List<Entity> entities = mat[robotel.getPozX()][robotel.getPozY()];
                        String color = command.getColor();
                        String smell = command.getSmell();
                        String sound = command.getSound();
                        if (ExistingHelper.existPlant(entities)
                                && (!color.equals("none"))
                                && (!smell.equals("none")
                                && (sound.equals("none")))) {
                            okPlanta = 1;
                        } else if (ExistingHelper.existAnimal(entities)
                                && (!color.equals("none"))
                                && (!smell.equals("none")
                                && (!sound.equals("none")))) {
                            inceputIteratieAnimal = iteratii;
                        }
                        // scaneaza obiectul si returneaza daca merge sau nu
                        sePoate = ScanObject.scanObjects(entities, color, smell, sound, iteratii,
                                factsAndSubjects, scanObjects, node);
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
                    } else if (robotel.getBattery() < MagicNumbersInt.doi.getNumar()) {
                        node.put("message",
                                "ERROR: Not enough battery left. Cannot perform action");
                    } else {
                        String fact = command.getComponents();
                        String subj = command.getSubject();
                        // seteaza subiect
                        mergeSubj = SetHelper.setSubjects(dimension, mat, fact, subj);
                        if (factsAndSubjects.containsKey(fact)) {
                            List<String> subiecte = factsAndSubjects.get(fact);
                            if (subiecte != null && subiecte.contains(subj)) {
                                mergeSubj = 0;
                            }
                        }
                        if (mergeSubj == 1) {
                            if (!factsAndSubjects.containsKey(fact)) {
                                factsAndSubjects.put(fact, new ArrayList<>());
                            }
                            factsAndSubjects.get(fact).add(subj);
                            robotel.setBattery(robotel.getBattery() - 2);
                            node.put("message",
                                    "The fact has been successfully saved in the database.");
                        } else {
                            node.put("message",
                                    "ERROR: Subject not yet saved. Cannot perform action");
                        }
                    }
                } else if (command.getCommand().equals("printKnowledgeBase")) {
                    PrintKnowledgeBase cmd = new PrintKnowledgeBase();
                    cmd.execute(okSimulation, node, opresteSimulare, contorComenzi, nextSimulare, i,
                            robotel, mat, MAPPER, dimension, command, factsAndSubjects);
                } else if (command.getCommand().equals("improveEnvironment")) {
                    if (okSimulation == 0) {
                        node.put("message",
                                "ERROR: Simulation not started. Cannot perform action");
                    } else if (robotel.isCharging()) {
                        node.put("message",
                                "ERROR: Robot still charging. Cannot perform action");
                    } else if (robotel.getBattery() < MagicNumbersDouble.zece.getNumar()) {
                        node.put("message",
                                "ERROR: Not enough battery left. Cannot perform action");
                    } else {
                        String improvment = command.getImprovementType();
                        String name = command.getName();
                        int merge = 0;
                        mergeSubj = 0;
                        if (factsAndSubjects.containsKey(name)) {
                            mergeSubj = 1;
                        }
                        if (scanObjects.containsKey(name)) {
                            mergeSubj = 1;
                        }
                        if (mergeSubj == 1) {
                            node.put("message", "ERROR: Fact not yet saved. Cannot perform action");
                        }
                        if (mergeSubj == 1) {
                            // printeaza daca se intampla ceva si verifica si erorile
                            merge = WeatherUpdateHelper.improveEnv(dimension, name, mat,
                                    improvment, robotel, command, node);
                            robotel.setBattery(robotel.getBattery()
                                    - MagicNumbersInt.zece.getNumar());
                        } else if (mergeSubj == 0) {
                            node.put("message",
                                    "ERROR: Subject not yet saved. Cannot perform action");
                        } else if (merge == 0) {
                            node.put("message",
                                    "ERROR: Fact not yet saved. Cannot perform action");
                        }
                    }
                } else if (command.getCommand().equals("getEnergyStatus")) {
                    GetEnergyStatus cmd = new GetEnergyStatus();
                    cmd.execute(okSimulation, node, opresteSimulare, contorComenzi, nextSimulare, i,
                            robotel, mat, MAPPER, dimension, command, factsAndSubjects);
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
                    int merge;
                    if (okSimulation == 0) {
                        node.put("message", "ERROR: Simulation not started. Cannot perform action");
                    } else if (robotel.isCharging()) {
                        node.put("message", "ERROR: Robot still charging. Cannot perform action");
                    } else {
                        okSchimbariMeteo = 1;
                        List<Entity> entities = mat[robotel.getPozX()][robotel.getPozY()];
                        iteratieAer = command.getTimestamp();
                        fostaCalitate = ReturnHelper.returnAir(entities).getAirQuality();
                        aerSchimb = ReturnHelper.returnAir(entities);
                        // schimba calitate aerului
                        merge = WeatherUpdateHelper.changeWeather(entities, command);
                        if (merge == 1) {
                            node.put("message", "The weather has changed.");
                        } else {
                            node.put("message", "ERROR: The weather change does not "
                                    + "affect the environment."
                                    + " Cannot perform action");
                        }
                    }
                }
                // seteaza daca animalul e mutat sau daca a murit planta
                SetHelper.setAnimalAndCheckPlant(dimension, mat, inceputIteratieAnimal);
                if (okSimulation == 1) {
                    fostaIteratie = iteratii;
                }
                if (okPlanta == 1) {
                    Plant pl = ReturnHelper.returnPlant(entit);
                    if (pl != null) {
                        pl.setIsScanned(true);
                    }
                }
                node.put("timestamp", command.getTimestamp());
                output.add(node);
                if (nextSimulare == 1) {
                    break;
                }
            }
        }
        File outputFile = new File(outputPath);
        outputFile.getParentFile().mkdirs();
        WRITER.writeValue(outputFile, output);
    }
}
