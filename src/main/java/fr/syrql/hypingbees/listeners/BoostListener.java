package fr.syrql.hypingbees.listeners;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.configuration.Configuration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;

public class BoostListener implements Listener {

    private final HypingBees hypingBees;
    private final Configuration configuration;
    public BoostListener(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.configuration = hypingBees.getConfiguration();
    }

    @EventHandler
    public void onClickAdd(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;

        // Get current Beehive
        Beehive beehive = this.hypingBees.getBeehiveManager().getCurrentPlayerBeehive(player.getUniqueId());
        // Check beehive non-null
        if (beehive == null) return;

        // cancel event
        event.setCancelled(true);

        // check if clickedslot is in beehive
        if (event.getClickedInventory() != null && event.getClickedInventory().getType() == InventoryType.CHEST) {
            // get current boost
            Boost waitBoostBySlot = this.hypingBees.getBeesManager().getWaitBoostBySlot(beehive, event.getSlot());
            // check boost non-null
            if (waitBoostBySlot == null) return;
            // Remove boost from beehive
            beehive.getBoosts().remove(event.getSlot(), waitBoostBySlot);
            // check if inventory is full or not
            if (player.getInventory().firstEmpty() == -1)
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), waitBoostBySlot.toItemStack());
            else
                player.getInventory().addItem(waitBoostBySlot.toItemStack());
            // Update inventory and send player message
            beehive.addInventoryItem(this.hypingBees, this.configuration, player.getOpenInventory().getTopInventory());
            player.sendMessage(this.configuration.getRemoveBoost());

        } else {

            // Section to add boost in beehive
            Boost boost = this.hypingBees.getBoostManager().getBoostByItemStack(event.getCurrentItem());

            if (boost == null) return;

            // create boost copy
            ItemStack itemBeesCopy = boost.toItemStack().clone();
            // set amount to 1
            itemBeesCopy.setAmount(1);

            // check if current boost is null
            if (beehive.getCurrentBoost() == null) {

                // set item to current boost
                event.getInventory().setItem(this.hypingBees.getBoostManager().getCurrentBoostSlot(), itemBeesCopy);
                // add current boost
                beehive.setCurrentBoost(boost);

                // send instructions to player
                player.sendMessage(this.configuration.getAddBoost());

                // Update inventory
                beehive.addInventoryItem(this.hypingBees, this.configuration, player.getOpenInventory().getTopInventory());

                player.getInventory().removeItem(itemBeesCopy);

            } else {

                // There is already current boost, add to waiting boost list
                for (Integer slot : this.hypingBees.getBoostManager().getSlotList()) {
                    ItemStack itemStack = event.getInventory().getItem(slot);

                    LinkedHashMap<Integer, Boost> currentBoosts = beehive.getBoosts();

                    // check if waiting boost is full or not
                    if (itemStack != null) continue;

                    // add waiting boost
                    currentBoosts.put(slot, boost);
                    beehive.setBoosts(currentBoosts);

                    event.getInventory().setItem(slot, itemBeesCopy);

                    // Send message to player
                    player.sendMessage(this.configuration.getAddBoost());
                    // Update inventory
                    beehive.addInventoryItem(this.hypingBees, this.configuration, player.getOpenInventory().getTopInventory());
                    // remove boost from player inventory
                    player.getInventory().removeItem(itemBeesCopy);

                    break;
                }
            }
        }
    }
}
