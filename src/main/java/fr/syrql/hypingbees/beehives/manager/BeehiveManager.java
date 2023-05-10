package fr.syrql.hypingbees.beehives.manager;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.namedinventory.data.NamedInventory;
import fr.syrql.hypingbees.rewards.data.Rewards;
import fr.syrql.hypingbees.beehives.factory.BeehiveFactory;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import org.bukkit.Location;
import org.bukkit.block.Block;
import world.bentobox.bentobox.database.objects.Island;

import java.util.*;

public class BeehiveManager {

    private final HypingBees hypingBees;
    private final HashMap<UUID, Beehive> currentBeehive = new HashMap<>();
    private final int inventorySize;
    private final String inventoryName;

    public BeehiveManager(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.inventorySize = this.hypingBees.getConfigManager().getInt("beehives.inventory.size");
        this.inventoryName = this.hypingBees.getConfigManager().getString("beehives.inventory.default-name");
    }

    public void putCurrentBeehive(UUID uuid, Beehive beehive) {
        this.currentBeehive.put(uuid, beehive);
    }

    public boolean canPlaceBees(int slot) {
        return this.hypingBees.getConfiguration().getSlots().contains(slot);
    }

    public void removeCurrentBeehive(UUID uuid) {
        this.currentBeehive.remove(uuid);
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public String getInventoryName() {
        return inventoryName;
    }

    public HashMap<UUID, Beehive> getCurrentBeehive() {
        return currentBeehive;
    }
}
