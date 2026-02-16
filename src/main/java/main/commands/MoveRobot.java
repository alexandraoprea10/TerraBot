package main.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CommandInput;
import main.Entity;
import main.Robot;
import main.helpers.MovingRobotHelper;

import java.util.LinkedHashMap;
import java.util.List;

public class MoveRobot implements Command {
    /**
     * Exec command.
     * @param okSimulation
     * @param node
     * @param opresteSimulare
     * @param contorComenzi
     * @param nextSimulare
     * @param i
     * @param robotel
     * @param mat
     * @param mapper
     * @param dimension
     * @param command
     * @param factsAndSubjects
     */
    @Override
    public void execute(final int okSimulation, final ObjectNode node,
                        final int opresteSimulare, final int contorComenzi,
                        final int nextSimulare, final int i,
                        final Robot robotel, final List<Entity>[][] mat,
                        final ObjectMapper mapper, final int dimension,
                        final CommandInput command,
                        final LinkedHashMap<String, List<String>> factsAndSubjects) {
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
            MovingRobotHelper.moveRobotel(robotel, mat, dimension);
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
    }
}
