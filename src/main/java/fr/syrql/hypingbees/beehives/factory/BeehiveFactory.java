package fr.syrql.hypingbees.beehives.factory;

import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.data.Rewards;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class BeehiveFactory {

    /**
     * This function will create a Beehive
     *
     * @param id
     * @param islandUUID
     * @param time
     * @param bees
     * @param buyableSlots
     * @param location
     * @param rewards
     * @param boosts
     */

    public Beehive create(String id, String islandUUID, int time, HashMap<Integer, Bees> bees, LinkedList<BuyableSlot> buyableSlots, Location location, Rewards rewards, LinkedHashMap<Integer, Boost> boosts) {
        return new Beehive(id, islandUUID, time, bees, buyableSlots, location.getWorld().getName(),
                location.getBlockX(), location.getBlockY(), location.getBlockZ(), rewards, boosts);
    }
}
