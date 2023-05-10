package fr.syrql.hypingbees.task;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.configuration.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.database.objects.Island;

public class BeehiveTask implements Runnable {

    private final HypingBees hypingBees;
    private final Configuration configuration;
    public BeehiveTask(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.configuration = hypingBees.getConfiguration();
    }

    @Override
    public void run() {

        // Loop every beehives
        this.hypingBees.getProvider().getBeehives().forEach(beehive -> {
            // get beehive time
            int time = beehive.getTime();
            // get island at location
            Island island = BentoBox.getInstance().getIslands().getIslandAt(beehive.toLocation()).orElse(null);
            // check if island is null or not
            if (island == null) return;
            // check if players is in
            if (!island.hasPlayersOnIsland()) return;
            // check if bees is empty or not
            if (beehive.getCurrentBees().isEmpty()) {
                beehive.updateHologram(this.configuration.getHologramsLine(beehive));
                return;
            }
            // check if boost non null
            if (beehive.getCurrentBoost() != null) {
                // get current boost
                Boost boost = beehive.getCurrentBoost();
                // check boost duration
                if (boost.getDuration() < 0) {
                    beehive.setCurrentBoost(null);
                } else {
                    boost.setDuration(boost.getDuration() - 1);
                }
            } else {
                // check if boost list is not empty
                if (!beehive.getBoosts().isEmpty()) {
                    Boost newBoost = beehive.getBoosts().values().stream().findFirst().orElse(null);
                    // get first boost in list and put currentBoost
                    beehive.setCurrentBoost(newBoost);

                }
            }
            // check if time is < to 0
            if (time >= configuration.getCycleTime()) {
                // get alls bees
                beehive.getCurrentBees().forEach((integer, bees) -> {
                    // add to rewards bees rewards
                    beehive.getRewards().getCommands().addAll(bees.getRewardsCommands());
                    beehive.getRewards().getMaterials().addAll(bees.getRewards());
                });
                // loop every players in island
                for (Player player : island.getPlayersOnIsland()) {
                    if (island.getMemberSet().contains(player.getUniqueId()))
                        player.sendMessage(this.configuration.getEndTime());
                }
                // reset cycle time
                beehive.setTime(0);
            } else {
                // update time
                beehive.setTime(time + (beehive.getCurrentBoost() == null ? 1 : beehive.getCurrentBoost().getMultiplicator()));
                // update holo
                beehive.updateHologram(this.configuration.getHologramsLine(beehive));
            }
        });
    }
}
