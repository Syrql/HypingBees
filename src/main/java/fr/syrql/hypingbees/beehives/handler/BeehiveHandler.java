package fr.syrql.hypingbees.beehives.handler;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.factory.BeehiveFactory;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import fr.syrql.hypingbees.rewards.data.Rewards;
import fr.syrql.hypingbees.rewards.factory.RewardsFactory;

import org.bukkit.Location;
import org.bukkit.block.Block;
import world.bentobox.bentobox.database.objects.Island;

import java.util.*;

public class BeehiveHandler {

    private final HypingBees hypingBees;

    public BeehiveHandler(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
    }

    public void createBeehive(Block block, Island island, LinkedList<BuyableSlot> buyableSlots) {

        String id = UUID.randomUUID().toString().substring(1, 6);

        Rewards rewards = new RewardsFactory().create();

        this.hypingBees.getProvider().provide(island.getUniqueId() + "-" + id,
                new BeehiveFactory().create(id, island.getUniqueId(), buyableSlots, block.getLocation(), rewards));

        Beehive beehive = this.hypingBees.getProvider().get(island.getUniqueId() + "-" + id);

        beehive.createHologram(this.hypingBees.getConfiguration().getHologramsLine(beehive));
    }

    public Beehive getBeehiveByLocation(Location location) {
        return hypingBees.getProvider().getBeehives().stream()
                .filter(beehive -> beehive.toLocation().equals(location))
                .findFirst().orElse(null);
    }

    public Beehive getCurrentPlayerBeehive(UUID uuid) {
        HashMap<UUID, Beehive> currentBeehives = this.hypingBees.getBeehiveManager().getCurrentBeehive();

        if (!currentBeehives.containsKey(uuid) || currentBeehives.get(uuid) == null) return null;

        return currentBeehives.get(uuid);
    }
}
