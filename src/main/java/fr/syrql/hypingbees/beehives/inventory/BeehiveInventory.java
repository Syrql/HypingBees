package fr.syrql.hypingbees.beehives.inventory;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.handler.BeehiveHandler;
import fr.syrql.hypingbees.beehives.manager.BeehiveManager;
import fr.syrql.hypingbees.boosts.handler.BoostHandler;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import fr.syrql.hypingbees.buyable.handler.BuyableSlotHandler;
import fr.syrql.hypingbees.configuration.Configuration;
import fr.syrql.hypingbees.namedinventory.data.NamedInventory;
import fr.syrql.hypingbees.namedinventory.handler.NamedInventoryHandler;
import fr.syrql.hypingbees.utils.bar.ProgressBar;
import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class BeehiveInventory {

    private final HypingBees hypingBees;
    private BuyableSlotHandler buyableSlotHandler;
    private final BeehiveManager beehiveManager;
    private final BoostHandler boostHandler;
    private final NamedInventoryHandler namedInventoryHandler;

    public BeehiveInventory(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.buyableSlotHandler = this.hypingBees.getBuyableHandler();
        this.beehiveManager = this.hypingBees.getBeehiveManager();
        this.boostHandler = this.hypingBees.getBoostHandler();
        this.namedInventoryHandler = this.hypingBees.getNamedInventoryHandler();
    }

    public void openBeehiveInventory(Configuration configuration, HypingBees hypingBees, Beehive beehive, Player player) {

        NamedInventory namedInventory = this.namedInventoryHandler.findClosestNamedInventory(this.namedInventoryHandler
                .getNamedInventoryList(), beehive.getTime());

        String inventoryName = "";

        if (beehive.getTime() <= hypingBees.getConfiguration().getCycleTime()) {
            if (namedInventory == null || beehive.getCurrentBees() == null)
                inventoryName = this.beehiveManager.getInventoryName();
            else inventoryName = namedInventory.getInventoryName();
        }

        Inventory inventory = Bukkit.createInventory(null, this.beehiveManager.getInventorySize(), inventoryName);

        this.addInventoryItem(configuration, beehive, inventory);

        player.openInventory(inventory);

        this.beehiveManager.putCurrentBeehive(player.getUniqueId(), beehive);

    }

    public void addInventoryItem(Configuration configuration, Beehive beehive, Inventory inventory) {

        inventory.clear();

        this.addExcludedSlots(configuration, inventory);
        this.addBeesOnInventory(beehive, inventory);

        for (BuyableSlot buyableSlot : beehive.getBuyableLines()) {
            inventory.setItem(buyableSlot.getSlot(), this.buyableSlotHandler.getBuyableItemStack());
        }

        inventory.setItem(configuration.getRewardSlot(), configuration.getRewardsItemStack());

        for (int i : configuration.getProgressSlots()) {
            inventory.setItem(i, new ItemBuilder(configuration.getProgressType())
                    .setName(ProgressBar.progressBar(configuration,
                            beehive.getTime(),
                            configuration.getCycleTime(),
                            configuration.getProgressState()))
                    .setLore(configuration.getProgressLore()).toItemStack());
        }

        if (beehive.getBoosts() != null) {
            beehive.getBoosts().forEach((integer, boost) -> inventory.setItem(integer, boost.toItemStack()));
        }

        inventory.setItem(this.boostHandler.getCurrentBoostSlot(),
                beehive.getCurrentBoost() == null ? new ItemStack(Material.AIR) : beehive.getCurrentBoost().toItemStack());

    }
    public void addExcludedSlots(Configuration configuration, Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (configuration.getExcludedInvisSlots().contains(i)) continue;

            inventory.setItem(i, new ItemBuilder(configuration.getInvisMaterial())
                    .setName(configuration.getInvisName())
                    .setCustomModelData(configuration.getInvisData())
                    .toItemStack());
        }
    }

    public void addBeesOnInventory(Beehive beehive, Inventory inventory) {

        beehive.getCurrentBees().forEach((slot, bees) -> {
            ItemStack itemstack = bees.toItemStack();
            inventory.setItem(slot, itemstack);
        });
    }
}
