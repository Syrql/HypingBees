package fr.syrql.hypingbees.beehives.data;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import fr.syrql.hypingbees.rewards.data.Rewards;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.io.Serializable;
import java.util.*;

public class Beehive implements Serializable {

    /**
     * Beehive Field id
     *
     * @param id the  beehive id.
     * @return Id of the Beehive.
     */

    private String id;

    /**
     * Beehive Field islandUUID
     *
     * @param islandUUID the beehive island id
     * @return Id of the island.
     */
    private String islandUUID;

    /**
     * Beehive Field time
     *
     * @param time the beehive cycle
     * @return timleft before cycle time
     */

    private int time;

    /**
     * Beehive Field currentBees
     *
     * @param currentBees the bees in beehive
     * @return hashmap of bees
     */


    private HashMap<Integer, Bees> currentBees;

    /**
     * Beehive Field buyableSlots
     *
     * @param buyableSlots the buyable lines
     * @return linkedlist of lines
     */

    private LinkedList<BuyableSlot> buyableSlots;

    /**
     * Beehive Field world
     *
     * @param world the world of the beehive
     * @return name of the world
     */

    private String world;

    /**
     * Beehive Field buyableSlots
     *
     * @param xyz the current location points
     * @return location points
     */


    private int x, y, z;

    /**
     * Beehive Field rewards
     *
     * @param rewards the waiting rewards
     * @return return rewards
     */

    private Rewards rewards;

    /**
     * Beehive Field boosts
     *
     * @param boost the linkedmap of waiting boosts of the beehive
     * @return linkedmap of boosts
     */

    private LinkedHashMap<Integer, Boost> boosts;

    /**
     * Beehive Field currentBoost
     *
     * @param currentBoost current boost
     * @return current boost on beehive
     */

    private Boost currentBoost;

    /**
     * Beehive Field hologram
     *
     * @param hologram the current hologram
     * @return Hologram
     */

    private transient Hologram hologram;

    /**
     * Constructor for class Rewards
     *
     * @param id
     * @param islandUUID
     * @param time
     * @param currentBees
     * @param buyableSlots
     * @param id
     * @param world
     * @param x
     * @param y
     * @param z
     * @param rewards
     * @param boosts
     */


    public Beehive(String id, String islandUUID, int time, HashMap<Integer, Bees> currentBees, LinkedList<BuyableSlot> buyableSlots, String world, int x, int y, int z, Rewards rewards, LinkedHashMap<Integer, Boost> boosts) {
        this.id = id;
        this.islandUUID = islandUUID;
        this.time = time;
        this.currentBees = currentBees;
        this.buyableSlots = buyableSlots;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.rewards = rewards;
        this.boosts = boosts;
    }

    /**
     * This is a getter which get the id
     *
     * @return id - the id to be get
     */

    public String getId() {
        return id;
    }

    /**
     * This is a setter which set the id
     *
     * @param id - the id to be set
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * This is a getter which get the islandUUID
     *
     * @return islandUUID - the islandUUID to be get
     */

    public String getIslandUUID() {
        return islandUUID;
    }

    /**
     * This is a setter which set the islandUUID
     *
     * @param islandUUID - the islandUUID to be set
     */

    public void setIslandUUID(String islandUUID) {
        this.islandUUID = islandUUID;
    }

    /**
     * This is a getter which get the time
     *
     * @return time - the time to be get
     */

    public int getTime() {
        return time;
    }

    /**
     * This is a setter which set the time
     *
     * @param time - the time to be set
     */

    public void setTime(int time) {
        this.time = time;
    }

    /**
     * This is a getter which get the currentBees
     *
     * @return currentBees - the currentBees to be get
     */

    public HashMap<Integer, Bees> getCurrentBees() {
        return currentBees;
    }

    /**
     * This is a setter which set the currentBees
     *
     * @param currentBees - the currentBees to be set
     */

    public void setCurrentBees(HashMap<Integer, Bees> currentBees) {
        this.currentBees = currentBees;
    }

    /**
     * This is a getter which get the buyableSlots
     *
     * @return buyableSlots - the buyableSlots to be get
     */

    public LinkedList<BuyableSlot> getBuyableLines() {
        return buyableSlots;
    }

    /**
     * This is a setter which set the buyableSlots
     *
     * @param buyableSlots - the buyableSlots to be set
     */

    public void setBuyableLines(LinkedList<BuyableSlot> buyableSlots) {
        this.buyableSlots = buyableSlots;
    }

    /**
     * This is a getter which get the rewards
     *
     * @return rewards - the rewards to be get
     */

    public Rewards getRewards() {
        return rewards;
    }

    /**
     * This is a setter which set the rewards
     *
     * @param rewards - the rewards to be set
     */

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }

    /**
     * This is a getter which get the boosts
     *
     * @return boosts - the boosts to be get
     */

    public LinkedHashMap<Integer, Boost> getBoosts() {
        return boosts;
    }

    /**
     * This is a setter which set the boosts
     *
     * @param boosts - the boosts to be set
     */

    public void setBoosts(LinkedHashMap<Integer, Boost> boosts) {
        this.boosts = boosts;
    }

    /**
     * This is a getter which get the currentBoost
     *
     * @return currentBoost - the currentBoost to be get
     */

    public Boost getCurrentBoost() {
        return currentBoost;
    }

    /**
     * This is a setter which setter the islandUUID
     *
     * @param currentBoost - the currentBoost to be set
     */

    public void setCurrentBoost(Boost currentBoost) {
        this.currentBoost = currentBoost;
    }

    /**
     * This is a getter which get the world
     *
     * @return world - the world to be get
     */

    public String getWorld() {
        return world;
    }

    /**
     * This is a setter which set the world
     *
     * @param world - the world to be set
     */

    public void setWorld(String world) {
        this.world = world;
    }

    /**
     * This is a getter which get the x
     *
     * @return x - the x to be get
     */

    public int getX() {
        return x;
    }

    /**
     * This is a setter which set the x
     *
     * @param x - the x to be set
     */

    public void setX(int x) {
        this.x = x;
    }

    /**
     * This is a getter which get the y
     *
     * @return y - the y to be get
     */

    public int getY() {
        return y;
    }

    /**
     * This is a setter which set the y
     *
     * @param y - the y to be set
     */

    public void setY(int y) {
        this.y = y;
    }

    /**
     * This is a getter which get the z
     *
     * @return z - the z to be get
     */

    public int getZ() {
        return z;
    }

    /**
     * This is a setter which get set z
     *
     * @param z - the z to be set
     */

    public void setZ(int z) {
        this.z = z;
    }

    /**
     * This is a getter which get the hologram
     *
     * @return hologram - the hologram to be get
     */

    public  Hologram getHologram() {
        return hologram;
    }

    /**
     * This is a setter which set the hologram
     *
     * @param hologram - the hologram to be set
     */

    public void setHologram(Hologram hologram) {
        this.hologram = hologram;
    }

    public void updateHologram(List<String> lines) {

        if (this.hologram == null) return;

        DHAPI.setHologramLines(hologram, lines);
        DHAPI.updateHologram(this.id);
    }

    public void createHologram(List<String> lines) {
        this.hologram = DHAPI.createHologram(this.id, this.toLocation().clone().add(.5, 3, .5));
        DHAPI.setHologramLines(hologram, lines);
    }

    public void destroyHologram() {
        this.hologram.destroy();
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
    }

}
