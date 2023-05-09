package fr.syrql.hypingbees.beehives.data;

import com.google.common.base.Strings;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.buyable.data.BuyableLine;
import fr.syrql.hypingbees.configuration.Configuration;
import fr.syrql.hypingbees.utils.item.ItemBuilder;
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

    private String id;
    private String islandUUID;
    private int time;
    private HashMap<Integer, Bees> currentBees;
    private LinkedList<BuyableLine> buyableLines;
    private String world;
    private int x, y, z;
    private Rewards rewards;
    private LinkedHashMap<Integer, Boost> boosts;
    private Boost currentBoost;
    private transient Hologram hologram;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIslandUUID() {
        return islandUUID;
    }

    public void setIslandUUID(String islandUUID) {
        this.islandUUID = islandUUID;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public HashMap<Integer, Bees> getCurrentBees() {
        return currentBees;
    }

    public void setCurrentBees(HashMap<Integer, Bees> currentBees) {
        this.currentBees = currentBees;
    }

    public LinkedList<BuyableLine> getBuyableLines() {
        return buyableLines;
    }

    public void setBuyableLines(LinkedList<BuyableLine> buyableLines) {
        this.buyableLines = buyableLines;
    }

    public Rewards getRewards() {
        return rewards;
    }

    public void setRewards(Rewards rewards) {
        this.rewards = rewards;
    }

    public LinkedHashMap<Integer, Boost> getBoosts() {
        return boosts;
    }

    public void setBoosts(LinkedHashMap<Integer, Boost> boosts) {
        this.boosts = boosts;
    }

    public Boost getCurrentBoost() {
        return currentBoost;
    }

    public void setCurrentBoost(Boost currentBoost) {
        this.currentBoost = currentBoost;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Hologram getHologram() {
        return hologram;
    }

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


        NamedInventory namedInventory = hypingBees.getBeehiveManager().getNamedInventoryList()
                .stream().filter(inventory -> inventory.getDuration() < this.time)
                .max(Comparator.comparingInt(NamedInventory::getDuration))
                .orElse(null);
        String inventoryName;

        if (namedInventory == null || this.time >= hypingBees.getConfiguration().getCycleTime())
            inventoryName = hypingBees.getBeehiveManager().getInventoryName();

        else inventoryName = namedInventory.getInventoryName();

        Inventory inventory = Bukkit.createInventory(null,
                hypingBees.getBeehiveManager().getInventorySize(),
                inventoryName);

        Bukkit.getScheduler().runTaskTimerAsynchronously(hypingBees, () -> {

            for (int i = 0; i < inventory.getSize(); i++) {
                if (configuration.getExcludedInvisSlots().contains(i)) continue;

                inventory.setItem(i, new ItemBuilder(configuration.getInvisMaterial())
                        .setName(configuration.getInvisName())
                        .setCustomModelData(configuration.getInvisData())
                        .toItemStack());
            }

            for (BuyableLine buyableLine : this.buyableLines) {
                inventory.setItem(buyableLine.getSlot(), hypingBees.getBuyableManager().getBuyableItemStack());
            }

            inventory.setItem(configuration.getRewardSlot(), configuration.getRewardsItemStack());

            this.currentBees.forEach((slot, bees) -> {

                ItemStack itemstack = new ItemBuilder(Material.getMaterial(bees.getMaterial()))
                        .setName(bees.getName())
                        .setLore(bees.getLore())
                        .toItemStack();

                if (bees.isGlow()) {
                    itemstack.addUnsafeEnchantment(Enchantment.ARROW_DAMAGE, 5);
                    ItemMeta itemMeta = itemstack.getItemMeta();
                    itemMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
                    itemstack.setItemMeta(itemMeta);
                }

                inventory.setItem(slot, new ItemBuilder(Material.getMaterial(bees.getMaterial()))
                        .setName(bees.getName())
                        .setLore(bees.getLore())
                        .toItemStack());
            });

            for (int i : configuration.getProgressSlots()) {
                inventory.setItem(i, new ItemBuilder(configuration.getProgressType())
                        //.setName(this.progressBar(configuration, this.time, configuration.getCycleTime(), configuration.getProgressState()))
                        .setName(this.progressBar(this.time, configuration.getCycleTime(), configuration.getProgressState(), '|', ChatColor.GREEN, ChatColor.GRAY))
                        .setLore(configuration.getProgressLore())
                        .toItemStack());
            }

            if (this.getBoosts() != null) {
                this.getBoosts().forEach((integer, boost) -> inventory.setItem(integer, boost.toItemStack()));
            }

            inventory.setItem(hypingBees.getBoostManager().getCurrentBoostSlot(),
                    this.currentBoost == null ? new ItemStack(Material.AIR) : this.currentBoost.toItemStack());

        }, 1L, 20L);

        player.openInventory(inventory);

        hypingBees.getBeehiveManager().putCurrentBeehive(player.getUniqueId(), this);

    }

    public String progressBar(int current, int max, int totalBars, char symbol, ChatColor completedColor,
                              ChatColor notCompletedColor) {
        float percent = (float) current / max;
        int progressBars = (int) (totalBars * percent);

        return Strings.repeat("" + completedColor + symbol, progressBars)
                + Strings.repeat("" + notCompletedColor + symbol, totalBars - progressBars);
    }

    public Location toLocation() {
        return new Location(Bukkit.getWorld(this.world), this.x, this.y, this.z);
    }
}
