package fr.syrql.hypingbees.listeners;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.handler.BeehiveHandler;
import fr.syrql.hypingbees.beehives.inventory.BeehiveInventory;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

public class InventoryBeehiveListener implements Listener {

    private final HypingBees hypingBees;
    private final Configuration configuration;
    private final BeehiveHandler beehiveHandler;
    private final BeehiveInventory beehiveInventory;

    public InventoryBeehiveListener(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.configuration = hypingBees.getConfiguration();
        this.beehiveHandler = this.hypingBees.getBeehiveHandler();
        this.beehiveInventory = this.hypingBees.getBeehiveInventory();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;
        // Get current beehive
        Beehive beehive = this.beehiveHandler.getCurrentPlayerBeehive(player.getUniqueId());
        // check non-null beehive
        if (beehive == null) return;
        // cancel event
        event.setCancelled(true);
        // check if clickedinventory is beehive or player inventory
        if (event.getClickedInventory() != null && event.getClickedInventory().getType() == InventoryType.CHEST) {
            // get placed bees at slot
            Bees placedBees = this.hypingBees.getBeesHandler().getBeesBySlot(beehive, event.getSlot());
            // check if non-null
            if (placedBees == null) return;
            // remove current bees
            beehive.getCurrentBees().remove(event.getSlot(), placedBees);
            // check if inventory is full or not
            if (player.getInventory().firstEmpty() == -1)
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), placedBees.toItemStack());
            else
                player.getInventory().addItem(placedBees.toItemStack());
            // update current beehive inventory
            this.beehiveInventory.addInventoryItem(this.configuration, beehive, player.getOpenInventory().getTopInventory());
            // Send player message
            player.sendMessage(configuration.getRemoveBees());
        } else {
            // get bees by itemstack
            Bees bees = this.hypingBees.getBeesHandler().getBeesByItemStack(event.getCurrentItem());
            // check bees non-null
            if (bees == null) return;
            // create bees copy
            ItemStack itemBeesCopy = bees.toItemStack().clone();
            // set bees amount to 1
            itemBeesCopy.setAmount(1);
            // get bees slots
            for (Integer slot : this.configuration.getSlots()) {

                ItemStack itemStack = event.getInventory().getItem(slot);

                // check if slot is empty or not
                if (itemStack != null) continue;

                // add bees to beehive
                beehive.getCurrentBees().put(slot, bees);
                event.getInventory().setItem(slot, itemBeesCopy);
                // put time to default
                beehive.setTime(0);
                // send player message
                player.sendMessage(this.configuration.getAddBees());
                // Update opened player inventory
                this.beehiveInventory.addInventoryItem(this.configuration, beehive, player.getOpenInventory().getTopInventory());
                // remove item from player inventory
                player.getInventory().removeItem(itemBeesCopy);
                break;

            }
        }
    }
}
