# Ecosystem Simulation & Robot Exploration Project

## 📖 How I Read Data
Input reading is handled via the skeleton classes located in `target -> classes -> fileio`. These classes manage the raw input and provide the necessary data structures to initialize entities, the map, and ecosystem commands.


## 📦 Project Architecture & Package Structure

### 🗺️ Core Architecture
*   **`Entity`**: The base class containing general information about every object in the ecosystem: name, type, a `scanned` flag (defaulting to `false`), and a list of associated facts.
*   **`Robot`**: Represents the exploration robot. Keeps track of `battery`, current coordinates (`position`), charging state, and `previousBattery` (used for rollback/validation checks).
*   **`Main`**: The entry point of the application. Handles matrix creation, parses entities/commands, and orchestrates the core simulation loop.


### 🌿 Ecosystem Entities

#### Package: `air`
*   **`Air` `[Abstract]`**: Defines general atmospheric methods. Behavior varies based on the concrete environment.
*   **`DesertAir`, `MountainAir`, `PolarAir`, `TemperateAir`, `TropicalAir`**: Subclasses implementing specific air quality formulas. 
*   *Note:* For weather-changing commands, extra fields (e.g., `rainfall`) were introduced, dynamically modifying the air quality calculations.

#### Package: `animal`
*   **`Animal` `[Abstract]`**: Outlines core animal behaviors and lifecycle states.
*   **`Carnivores`, `Detritivores`, `Herbivores`, `Omnivores`, `Parasites`**: Subclasses implementing specific dietary and movement logic. 
*   *Interaction Mechanics:* Upon interaction, animals actively search for a better neighboring tile. Depending on their type (e.g., predator vs. parasite), they execute specific movements and survival behaviors.

#### Package: `plant`
*   **`Plant` `[Abstract]`**: Defines growth and environmental baseline methods.
*   **`Algae`, `Ferns`, `FloweringPlants`, `GymnospermsPlants`, `Mosses` `[Final]`**: Final classes since they hold sufficient self-contained logic and do not require further extension.
*   *Observation:* When a plant dies (`dead`), it is not removed from the ecosystem list; instead, its maturity state is set to `"out"`.

#### Package: `soil`
*   **`Soil` `[Abstract]`**: Defines baseline ground and nutrient behaviors.
*   **`DesertSoil`, `ForestSoil`, `GrasslandSoil`, `SwampSoil`, `TundraSoil`**: Subclasses implementing climate-specific soil quality formulas.


### 🛠️ Actions and Commands

#### Package: `commands` (Robot Actions)
Contains specific modular simulation commands:
*   **`PRINT ENV CONDITIONS`**: Displays details of the tile currently occupied by the robot. It iterates through the entity list following the exact order specified in the requirements. Clean code practice: modularized into separate print methods to avoid redundancy.
*   **`PRINT MAP`**: Iterates through the entire matrix grid. Counts and prints the number of dynamic objects (filtering only `water`, `plant`, and `animal`). Computes air/soil quality on the fly using abstract methods from subclasses.
*   **`MOVE ROBOT`**: Validates neighbor coordinates first to prevent `IndexOutOfBoundsException`. Evaluates a score for each neighbor, sets the attack probability for all entities in the list (storing it in `entities(0)` for later use), and picks the optimal tile (out of max 4 neighbors). Moves the robot and consumes battery. If the energy required exceeds the `previousBattery` state, it throws an error.
*   **`PRINT KNOWLEDGE BASE`**: Iterates through the knowledge matrix, printing valid keys and values. Data is sorted alphabetically to ensure consistency across tests.
*   **`GET ENERGY STATUS`**: Prints the current battery status using the `Robot` getter.

#### Core System Commands
These global commands orchestrate the simulation state directly within the execution loop:
*   **`START SIMULATION`**: Toggles the active simulation state variable. Used for strict error handling (e.g., blocking map printing prior to initialization).
*   **`END SIMULATION`**: Terminates the loop and resets the state flag to prevent subsequent operations.
*   **`SCAN OBJECT`**: Standardizes the `scanned` flag (default `false`) inside `Entity`. Identifies if the target is a plant, animal, or water. Once scanned, the entity begins actively interacting with the surrounding environment.
*   **`LEARN FACT`**: Checks scan status and matches entity names. Uses a `LinkedHashMap` to preserve insertion order. Special fix for Test 20: scanning an object clears prior fact lists to prevent state pollution.
*   **`IMPROVE ENVIRONMENT`**: Validates requirements and executes one of 4 environment upgrades (e.g., `plantvegetation`, `fertilizesoil`). Handled via decoupled helper methods.
*   **`RECHARGE BATTERY`**: Replenishes the robot's energy based on input ticks.
*   **`CHANGE WEATHER CONDITIONS`**: Injects new parameters (like `rainfall`) into the air subclasses, modifying live calculations.
*   **`MULTIPLE SIMULATIONS`**: Loops through independent test simulations using precise counters to track boundaries.


### 🌐 Map & Map Construction

#### Package: `createTerritory`
These classes are responsible for building the initial simulation grid. The matrix consists of cells (tiles), where each tile contains a list of entities (plants, animals, soil, water, air).
*   **`AddAir`** – Instantiates and assigns air properties to a tile.
*   **`AddAnimals`** – Spawns and adds animals to a tile's entity list.
*   **`AddPlants`** – Spawns and adds plants to a tile's entity list.
*   **`AddSoil`** – Assigns soil composition to a tile.
*   **`AddWater`** – Adds water bodies to a tile.


### ⚡ Helpers & Constants

#### Package: `helpers`
Decoupled utility classes designed to keep core logic clean and maintainable:
*   **`CalculateHelper`** – Mathematical and formula calculations.
*   **`ExistingHelper`** – Entity presence validation on specific tiles.
*   **`InteractionsHelper`** – Cross-entity logic and environmental events.
*   **`MovingAnimalHelper` & `MovingRobotHelper`** – Pathfinding, scoring, and position updates.
*   **`PrintHelper`** – Console output formatting.
*   **`ReturnHelper` & `SetHelper`** – Getters, filters, and field setters.
*   **`UpdateHelper` & `WeatherUpdateHelper`** – Live state updates and environmental tick updates.

#### Package: `magicNumbers`
*   **`MagicNumbersDouble`** – Stores constant `Double` values used in simulation formulas.
*   **`MagicNumbersInt`** – Stores configuration `Integer` values.
