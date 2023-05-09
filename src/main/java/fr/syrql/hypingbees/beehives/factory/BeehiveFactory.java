package fr.syrql.hypingbees.beehives.factory;

import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.data.Rewards;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.buyable.data.BuyableLine;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class BeehiveFactory {

    public Beehive create(String id, String islandUUID, int time, HashMap<Integer, Bees> bees, LinkedList<BuyableLine> buyableLines, Location location, Rewards rewards, LinkedHashMap<Integer, Boost> boosts) {
        return new Beehive(id, islandUUID, time, bees, buyableLines, location.getWorld().getName(),
                location.getBlockX(), location.getBlockY(), location.getBlockZ(), rewards, boosts);
    }
}
