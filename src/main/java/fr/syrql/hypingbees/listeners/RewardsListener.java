package fr.syrql.hypingbees.listeners;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.handler.BeehiveHandler;
import fr.syrql.hypingbees.beehives.inventory.BeehiveInventory;
import fr.syrql.hypingbees.configuration.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class RewardsListener implements Listener {

    private final HypingBees hypingBees;
    private final Configuration configuration;
    private final BeehiveHandler beehiveHandler;

    public RewardsListener(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.configuration = hypingBees.getConfiguration();
        this.beehiveHandler = this.hypingBees.getBeehiveHandler();
    }

    @EventHandler
    public void onAcceptRewards(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (event.getCurrentItem() == null) return;

        // get current beehive
        Beehive beehive = this.beehiveHandler.getCurrentPlayerBeehive(player.getUniqueId());
        // check beehive non-null
        if (beehive == null) return;
        // check if clicked inventory is similar
        if (!this.hypingBees.getConfiguration().getRewardsItemStack().isSimilar(event.getCurrentItem())) return;
        // cancel event
        event.setCancelled(true);
        // check if beehive contains rewards
        if (beehive.getRewards().getMaterials() == null && beehive.getRewards().getCommands() == null) {
            player.sendMessage(configuration.getEmptyRewards());
            player.closeInventory();
            return;
        }
        // give rewards to player
        beehive.getRewards().getMaterials().forEach(line -> {
            Material material = Material.getMaterial(line);

            if (material == null) return;
            // check if inventory is null or not
            if (player.getInventory().firstEmpty() == -1)
                player.getLocation().getWorld().dropItemNaturally(player.getLocation(), new ItemStack(material));
            else
                player.getInventory().addItem(new ItemStack(material));
        });
        // apply commands
        if (beehive.getRewards().getCommands() != null)
            beehive.getRewards().getCommands().forEach(line ->
                    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), line.replace("%player%", player.getName())));
        // clear commands and materials
        beehive.getRewards().getCommands().clear();
        beehive.getRewards().getMaterials().clear();
        // close inventory
        player.closeInventory();
    }
}
