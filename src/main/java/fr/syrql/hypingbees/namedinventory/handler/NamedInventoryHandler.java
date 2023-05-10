package fr.syrql.hypingbees.namedinventory.handler;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.namedinventory.data.NamedInventory;
import fr.syrql.hypingbees.namedinventory.factory.NamedInventoryFactory;
import fr.syrql.hypingbees.utils.config.ConfigManager;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class NamedInventoryHandler {

    private final HypingBees hypingBees;
    private final List<NamedInventory> namedInventoryList = new ArrayList<>();

    public NamedInventoryHandler(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.setupNamedInventory();
    }

    private void setupNamedInventory() {
        for (String key : this.hypingBees.getConfig().getConfigurationSection("beehives.inventory.name").getKeys(false)) {
            String path = "beehives.inventory.name." + key;

            ConfigManager config = this.hypingBees.getConfigManager();

            namedInventoryList.add(new NamedInventoryFactory().create(
                    config.getInt(path + ".time"),
                            config.getString(path + ".name")));

        }
    }

    public NamedInventory findClosestNamedInventory(List<NamedInventory> arr, int target) {
        return arr.stream()
                .filter(named -> named != null && named.getDuration() > target)
                .min(Comparator.comparingInt(NamedInventory::getDuration)) // Optional<Integer>
                .orElse(null); // or orElseGet(() -> null)
    }
    public List<NamedInventory> getNamedInventoryList() {
        return namedInventoryList;
    }

}
