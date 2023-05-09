package fr.syrql.hypingbees.beehives.data;

import java.util.List;

public class Rewards {

    private List<String> materials;
    private List<String> commands;

    public Rewards(List<String> materials, List<String> commands) {
        this.materials = materials;
        this.commands = commands;
    }

    public List<String> getMaterials() {
        return materials;
    }

    public void setMaterials(List<String> materials) {
        this.materials = materials;
    }

    public List<String> getCommands() {
        return commands;
    }

    public void setCommands(List<String> commands) {
        this.commands = commands;
    }
}
