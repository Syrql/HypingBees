package fr.syrql.hypingbees.task;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.function.Consumer;

public class BeehiveInventoryTask implements Runnable {

    private final HypingBees hypingBees;

    public BeehiveInventoryTask(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
    }

    @Override
    public void run() {
        Bukkit.getOnlinePlayers().forEach(player -> {

            Beehive beehive = this.hypingBees.getBeehiveManager().getCurrentPlayerBeehive(player.getUniqueId());

            if (beehive == null) return;

            beehive.addInventoryItem(this.hypingBees, this.hypingBees.getConfiguration(), player.getOpenInventory().getTopInventory());
        });
    }
}
