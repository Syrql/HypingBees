package fr.syrql.hypingbees.listeners;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
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

    public InventoryBeehiveListener(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.configuration = hypingBees.getConfiguration();
    }

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;
        // Get current beehive
        Beehive beehive = this.hypingBees.getBeehiveManager().getCurrentPlayerBeehive(player.getUniqueId());
        // check non-null beehive
        if (beehive == null) return;
        // cancel event
        event.setCancelled(true);
        // check if clickedinventory is beehive or player inventory
        if (event.getClickedInventory() != null && event.getClickedInventory().getType() == InventoryType.CHEST) {
            // get placed bees at slot
            Bees placedBees = this.hypingBees.getBeesManager().getBeesBySlot(beehive, event.getSlot());
            // check if non-null
            if (placedBees == null) return;
            // remove current bees
            beehive.getCurrentBees().remove(event.getSlot(), placedBees);
            // check if inventory is full or not
            if (player.getInventory().firstEmpty() == -1)
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), placedBees.toItemStack());
            else
                player.getInventory().addItem(placedBees.toItemStack());
            // close and send instructions
            player.closeInventory();
            player.sendMessage(configuration.getRemoveBees());
        } else {
            // get bees by itemstack
            Bees bees = this.hypingBees.getBeesManager().getBeesByItemStack(event.getCurrentItem());
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
                beehive.setTime(this.configuration.getCycleTime());
                // send instructions
                player.sendMessage(this.configuration.getAddBees());
                player.closeInventory();
                player.getInventory().removeItem(itemBeesCopy);
                break;

            }
        }
    }
}
