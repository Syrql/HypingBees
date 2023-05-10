package fr.syrql.hypingbees.beehives.data;

import java.util.List;

public class Rewards {

    /**
     * NamedInventory type duration
     *
     * @param material current materials of the waiting rewards
     * @return materials of the waiting rewards
     */

    private List<String> materials;
    /**
     * NamedInventory type duration
     *
     * @param commands current commands of the waiting rewards
     * @return commands of the waiting rewards
     */

    private List<String> commands;

    /**
     * Constructor for class Rewards
     *
     * @param materials Materials list for waiting rewards
     * @param commands Commands list for waiting rewards
     */

    public Rewards(List<String> materials, List<String> commands) {
        this.materials = materials;
        this.commands = commands;
    }
    /**
     * This is a getter which get the materials
     *
     * @return materials - the materials to be get
     */

    public List<String> getMaterials() {
        return materials;
    }

    /**
     * This is a setter which set the materials
     *
     * @return materials - the materials to be set
     */

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    /**
     * This is a getter which get the commands
     *
     * @return commands - the commands to be get
     */

    public List<String> getCommands() {
        return commands;
    }

    /**
     * This is a setter which set the commands
     *
     * @return commands - the commands to be set
     */

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
