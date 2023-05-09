package fr.syrql.hypingbees.bees.data;

import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.List;

public class Bees implements Serializable {

    private String type;
    private String name;
    private List<String> lore;
    private String material;
    private boolean glow;
    private int modelData;
    private List<String> rewards;
    private List<String> rewardsCommands;

    public Bees(String type, String name, List<String> lore, String material, boolean glow, int modelData, List<String> rewards, List<String> rewardsCommands) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.material = material;
        this.glow = glow;
        this.modelData = modelData;
        this.rewards = rewards;
        this.rewardsCommands = rewardsCommands;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public boolean isGlow() {
        return glow;
    }

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    public int getModelData() {
        return modelData;
    }

    public void setModelData(int modelData) {
        this.modelData = modelData;
    }

    public List<String> getRewards() {
        return rewards;
    }

    public void setRewards(List<String> rewards) {
        this.rewards = rewards;
    }

    public List<String> getRewardsCommands() {
        return rewardsCommands;
    }

    public void setRewardsCommands(List<String> rewardsCommands) {
        this.rewardsCommands = rewardsCommands;
    }

    public ItemStack toItemStack() {
        return new ItemBuilder(Material.getMaterial(this.material))
                .setName(this.name)
                .setLore(this.lore)
                .setCustomModelData(this.modelData)
                .toItemStack();
    }
}
