package fr.syrql.hypingbees.configuration;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Configuration {

    private final HypingBees hypingBees;
    private final int nearBlocks;
    private final int cycleTime;
    private final List<Integer> slots;
    private final Material invisMaterial;
    private final String invisName;
    private final int invisData;
    private final List<Integer> excludedInvisSlots;
    private final String rewardName;
    private final Material rewardType;
    private final int rewardData;
    private final List<String> rewardLore;
    private final int rewardSlot;
    private final String progress;
    private final int progressState;
    private final String progressEmpty;
    private final Material progressType;
    private final String progressName;
    private final List<String> progressLore;
    private final List<Integer> progressSlots;
    private final List<String> hologramsLine;
    private final String noMoney;
    private final String buyNewLine;
    private final String noIsland;
    private final String notYourBeehive;
    private final String emptyRewards;
    private final String endTime;
    private final String addBoost;
    private final String removeBoost;
    private final String addBees;
    private final String removeBees;
    public Configuration(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        ConfigManager configManager = hypingBees.getConfigManager();

        this.nearBlocks = configManager.getInt("beehives.near-block");
        this.cycleTime = configManager.getInt("beehives.cycle");
        this.slots = configManager.getIntList("beehives.inventory.bees-slots");
        this.invisMaterial = Material.getMaterial(configManager.getString("beehive-invis.material"));
        this.invisName = configManager.getString("beehive-invis.name");
        this.invisData = configManager.getInt("beehive-invis.data");
        this.excludedInvisSlots = configManager.getIntList("beehive-invis.excluded-slots");
        this.rewardName = configManager.getString("beehives.inventory.rewards.name");
        this.rewardType = Material.getMaterial(configManager.getString("beehives.inventory.rewards.type"));
        this.rewardData = configManager.getInt("beehives.inventory.rewards.data");
        this.rewardLore = configManager.getStringList("beehives.inventory.rewards.lore");
        this.rewardSlot = configManager.getInt("beehives.inventory.rewards.slot");
        this.progress = configManager.getString("beehives.progress.progress");
        this.progressState = configManager.getInt("beehives.progress.states");
        this.progressEmpty = configManager.getString("beehives.progress.empty");
        this.progressType = Material.getMaterial(configManager.getString("beehives.progress.material"));
        this.progressName = configManager.getString("beehives.progress.name");
        this.progressLore = configManager.getStringList("beehives.progress.lore");
        this.progressSlots = configManager.getIntList("beehives.progress.slots");
        this.hologramsLine = configManager.getStringList("beehives.holograms");
        this.noMoney = configManager.getString("no-money");
        this.buyNewLine = configManager.getString("buy-new-line");
        this.noIsland = configManager.getString("not-island");
        this.notYourBeehive = configManager.getString("not-your-beehive");
        this.emptyRewards = configManager.getString("empty-rewards");
        this.endTime = configManager.getString("end-time");
        this.addBoost = configManager.getString("add-boost");
        this.removeBoost = configManager.getString("remove-boost");
        this.addBees = configManager.getString("add-bees");
        this.removeBees = configManager.getString("remove-bees");

    }

    public int getNearBlocks() {
        return nearBlocks;
    }

    public int getCycleTime() {
        return cycleTime;
    }

    public List<Integer> getSlots() {
        return slots;
    }

    public Material getInvisMaterial() {
        return invisMaterial;
    }

    public String getInvisName() {
        return invisName;
    }

    public int getInvisData() {
        return invisData;
    }

    public List<Integer> getExcludedInvisSlots() {
        return excludedInvisSlots;
    }

    public String getRewardName() {
        return rewardName;
    }

    public Material getRewardType() {
        return rewardType;
    }

    public int getRewardData() {
        return rewardData;
    }

    public List<String> getRewardLore() {
        return rewardLore;
    }

    public int getRewardSlot() {
        return rewardSlot;
    }

    public ItemStack getRewardsItemStack() {
        return new ItemBuilder(this.rewardType)
                .setName(this.rewardName)
                .setLore(this.rewardLore)
                .setCustomModelData(this.rewardData)
                .toItemStack();
    }

    public String getProgress() {
        return progress;
    }

    public int getProgressState() {
        return progressState;
    }

    public String getProgressEmpty() {
        return progressEmpty;
    }

    public Material getProgressType() {
        return progressType;
    }

    public String getProgressName() {
        return progressName;
    }

    public List<String> getProgressLore() {
        return progressLore;
    }

    public List<Integer> getProgressSlots() {
        return progressSlots;
    }

    public List<String> getHologramsLine(Beehive beehive) {

        List<String> lines = new ArrayList<>();

        /*
        this.hologramsLine
                .forEach(s -> lines.add(s
                        .replace("%time%", beehive.progressBar(
                                this.hypingBees.getConfiguration(),
                                beehive.getTime(),
                                hypingBees.getConfiguration().getCycleTime(),
                                5))
                        .replace("%bees-size%", String.valueOf(beehive.getCurrentBees().size()))));
         */
        this.hologramsLine
                .forEach(s -> lines.add(s
                        .replace("%time%", beehive.progressBar(beehive.getTime(),
                                this.getCycleTime(),
                                this.getProgressState(),
                                '|', ChatColor.GREEN,
                                ChatColor.GRAY))
                        .replace("%bees-size%", String.valueOf(beehive.getCurrentBees().size()))));

        return lines;
    }

    public String getNoMoney() {
        return noMoney;
    }

    public String getBuyNewLine() {
        return buyNewLine;
    }

    public String getNoIsland() {
        return noIsland;
    }

    public String getNotYourBeehive() {
        return notYourBeehive;
    }

    public String getEmptyRewards() {
        return emptyRewards;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getAddBoost() {
        return addBoost;
    }

    public String getRemoveBoost() {
        return removeBoost;
    }

    public String getAddBees() {
        return addBees;
    }

    public String getRemoveBees() {
        return removeBees;
    }
}
