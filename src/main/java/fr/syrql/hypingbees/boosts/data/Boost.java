package fr.syrql.hypingbees.boosts.data;

import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Boost {

    private String type;
    private String name;
    private List<String> lore;
    private int multiplicator;
    private int duration;
    private String material;
    private int customModelData;

    public Boost(String type, String name, List<String> lore, int multiplicator, int duration, String material, int customModelData) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.multiplicator = multiplicator;
        this.duration = duration;
        this.material = material;
        this.customModelData = customModelData;
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

    public int getMultiplicator() {
        return multiplicator;
    }

    public void setMultiplicator(int multiplicator) {
        this.multiplicator = multiplicator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getCustomModelData() {
        return customModelData;
    }

    public void setCustomModelData(int customModelData) {
        this.customModelData = customModelData;
    }

    public ItemStack toItemStack() {
        return new ItemBuilder(Material.getMaterial(this.material))
                .setName(this.name)
                .setLore(this.lore)
                .setCustomModelData(this.customModelData)
                .toItemStack();
    }
}
