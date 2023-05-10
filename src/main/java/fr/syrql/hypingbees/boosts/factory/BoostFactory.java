package fr.syrql.hypingbees.boosts.factory;

import fr.syrql.hypingbees.boosts.data.Boost;

import java.util.List;

public class BoostFactory {

    public Boost create(String type, String name, List<String> lore, int multiplicator, int duration, String material, int customModelData) {
        return new Boost(type, name, lore, multiplicator, duration, material, customModelData);
    }
}
