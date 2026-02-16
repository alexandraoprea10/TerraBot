package main.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CommandInput;
import main.Entity;
import main.Robot;

import java.util.LinkedHashMap;
import java.util.List;

public interface Command {
    /**
     * Execute command.
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
    void execute(int okSimulation, ObjectNode node,
                        int opresteSimulare, int contorComenzi,
                        int nextSimulare, int i,
                        Robot robotel, List<Entity>[][] mat,
                        ObjectMapper mapper, int dimension,
                        CommandInput command,
                        LinkedHashMap<String, List<String>> factsAndSubjects);
}
