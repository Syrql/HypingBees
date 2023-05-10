package fr.syrql.hypingbees.beehives.data;

import com.google.common.base.Strings;
import com.mojang.datafixers.types.templates.Named;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.buyable.data.BuyableLine;
import fr.syrql.hypingbees.configuration.Configuration;
import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
     * Beehive Field buyableLines
     *
     * @param buyableLines the buyable lines
     * @return linkedlist of lines
     */

    private LinkedList<BuyableLine> buyableLines;

    /**
     * Beehive Field world
     *
     * @param world the world of the beehive
     * @return name of the world
     */

    private String world;

    /**
     * Beehive Field buyableLines
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
     * Another constructor for class Time1
     *
     * @param id
     * @param islandUUID
     * @param time
     * @param currentBees
     * @param buyableLines
     * @param id
     * @param world
     * @param x
     * @param y
     * @param z
     * @param rewards
     * @param boosts
     */


    public Beehive(String id, String islandUUID, int time, HashMap<Integer, Bees> currentBees, LinkedList<BuyableLine> buyableLines, String world, int x, int y, int z, Rewards rewards, LinkedHashMap<Integer, Boost> boosts) {
        this.id = id;
        this.islandUUID = islandUUID;
        this.time = time;
        this.currentBees = currentBees;
        this.buyableLines = buyableLines;
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
     * This is a getter which get the buyableLines
     *
     * @return buyableLines - the buyableLines to be get
     */

    public LinkedList<BuyableLine> getBuyableLines() {
        return buyableLines;
    }

    /**
     * This is a setter which set the buyableLines
     *
     * @param buyableLines - the buyableLines to be set
     */

    public void setBuyableLines(LinkedList<BuyableLine> buyableLines) {
        this.buyableLines = buyableLines;
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

    public Hologram getHologram() {
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

    public void openBeehiveInventory(Configuration configuration, HypingBees hypingBees, Player player) {

        NamedInventory namedInventory = this.findClosestAboveStream(hypingBees.getBeehiveManager()
                .getNamedInventoryList(), this.time);

        String inventoryName = "";

        if (this.time <= hypingBees.getConfiguration().getCycleTime()) {
            if (namedInventory == null || this.currentBees == null)
                inventoryName = hypingBees.getBeehiveManager().getInventoryName();
            else inventoryName = namedInventory.getInventoryName();
        }

        Inventory inventory = Bukkit.createInventory(null, hypingBees.getBeehiveManager().getInventorySize(), inventoryName);

        this.addInventoryItem(hypingBees, configuration, inventory);

        player.openInventory(inventory);

        hypingBees.getBeehiveManager().putCurrentBeehive(player.getUniqueId(), this);

    }

    public void addInventoryItem(HypingBees hypingBees, Configuration configuration, Inventory inventory) {

        this.addExcludedSlots(configuration, inventory);
        this.addBeesOnInventory(inventory);

        for (BuyableLine buyableLine : this.buyableLines) {
            inventory.setItem(buyableLine.getSlot(), hypingBees.getBuyableManager().getBuyableItemStack());
        }

        inventory.setItem(configuration.getRewardSlot(), configuration.getRewardsItemStack());

        for (int i : configuration.getProgressSlots()) {
            inventory.setItem(i, new ItemBuilder(configuration.getProgressType()).setName(this.progressBar(configuration, this.time, configuration.getCycleTime(), configuration.getProgressState())).setLore(configuration.getProgressLore()).toItemStack());
        }

        if (this.getBoosts() != null) {
            this.getBoosts().forEach((integer, boost) -> inventory.setItem(integer, boost.toItemStack()));
        }

        inventory.setItem(hypingBees.getBoostManager().getCurrentBoostSlot(), this.currentBoost == null ? new ItemStack(Material.AIR) : this.currentBoost.toItemStack());

    }

    public void addExcludedSlots(Configuration configuration, Inventory inventory) {
        for (int i = 0; i < inventory.getSize(); i++) {
            if (configuration.getExcludedInvisSlots().contains(i)) continue;

            inventory.setItem(i, new ItemBuilder(configuration.getInvisMaterial()).setName(configuration.getInvisName()).setCustomModelData(configuration.getInvisData()).toItemStack());
        }
    }

    public void addBeesOnInventory(Inventory inventory) {

        this.currentBees.forEach((slot, bees) -> {
            ItemStack itemstack = bees.toItemStack();
            inventory.setItem(slot, itemstack);
        });

    }

    public String progressBar(Configuration configuration, int value, int maxvalue, int size) {
        float percentage = (float) value / maxvalue;
        int progress = (int) (size * percentage);
        int emptyProgress = size - progress;

        String progressText = StringUtils.repeat(configuration.getProgress(), progress);
        String emptyProgressText = StringUtils.repeat(configuration.getProgressEmpty(), emptyProgress);
        return progressText + emptyProgressText;
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
    }

    public NamedInventory findClosestAboveStream(List<NamedInventory> arr, int target) {
        return arr.stream()
                .filter(named -> named != null && named.getDuration() > target)
                .min(Comparator.comparingInt(NamedInventory::getDuration)) // Optional<Integer>
                .orElse(null); // or orElseGet(() -> null)
    }
}
