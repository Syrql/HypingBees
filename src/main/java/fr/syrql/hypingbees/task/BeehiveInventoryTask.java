package fr.syrql.hypingbees.task;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.handler.BeehiveHandler;
import fr.syrql.hypingbees.beehives.inventory.BeehiveInventory;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class BeehiveInventoryTask implements Runnable {

    private final HypingBees hypingBees;
    private final BeehiveInventory beehiveInventory;

    public BeehiveInventoryTask(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.beehiveInventory = this.hypingBees.getBeehiveInventory();
    }

    @Override
    public void run() {
        // Loop every player that are opening beehive
        this.hypingBees.getBeehiveManager().getCurrentBeehive().forEach((uuid, beehive) -> {
            // Create player field
            Player player = Bukkit.getPlayer(uuid);
            // Check player not-null
            if (player == null) return;
            // Update inventory
            this.beehiveInventory.addInventoryItem(this.hypingBees, this.hypingBees.getConfiguration(), beehive, player.getOpenInventory().getTopInventory());

        });
    }
}
