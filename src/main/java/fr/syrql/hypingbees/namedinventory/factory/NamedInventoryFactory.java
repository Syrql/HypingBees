package fr.syrql.hypingbees.namedinventory.factory;

import fr.syrql.hypingbees.namedinventory.data.NamedInventory;

public class NamedInventoryFactory {

    public NamedInventory create(int duration, String inventoryName) {
        return new NamedInventory(duration, inventoryName);
    }
}
