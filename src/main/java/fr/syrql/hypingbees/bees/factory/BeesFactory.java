package fr.syrql.hypingbees.bees.factory;

import fr.syrql.hypingbees.bees.data.Bees;

import java.util.List;

public class BeesFactory {

    public Bees create(String type, String name, List<String> lore, String material, boolean glow, int modelData, List<String> rewards, List<String> rewardsCommands) {
        return new Bees(type, name, lore, material, glow, modelData, rewards, rewardsCommands);
    }
}
