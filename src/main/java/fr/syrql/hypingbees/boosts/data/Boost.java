package fr.syrql.hypingbees.boosts.data;

import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class Boost {

    /**
     * Boost Field type
     *
     * @param type the boost id.
     * @return Id of the boost.
     */

    private String type;

    /**
     * Boost Field name
     *
     * @param name the boost name.
     * @return Name of the boost.
     */

    private String name;

    /**
     * Boost Field lore
     *
     * @param lore the boost lore.
     * @return Lore of the boost.
     */

    private List<String> lore;

    /**
     * Boost Field multiplicator
     *
     * @param multiplicator the boost multiplicator.
     * @return Multiplicator of the boost.
     */

    private int multiplicator;

    /**
     * Boost Field duration
     *
     * @param material the boost duration.
     * @return Duration of the boost.
     */

    private int duration;

    /**
     * Boost Field material
     *
     * @param material the boost material.
     * @return Material of the boost.
     */

    private String material;

    /**
     * Boost Field customModelData
     *
     * @param customModelData the boost customModelData.
     * @return ModelData of the boost.
     */

    private int customModelData;

    /**
     * Constructor for class Boost
     *
     * @param type
     * @param name
     * @param lore
     * @param material
     * @param multiplicator
     * @param duration
     * @param material
     * @param customModelData
     */

    public Boost(String type, String name, List<String> lore, int multiplicator, int duration, String material, int customModelData) {
        this.type = type;
        this.name = name;
        this.lore = lore;
        this.multiplicator = multiplicator;
        this.duration = duration;
        this.material = material;
        this.customModelData = customModelData;
    }

    /**
     * This is a getter which get the type
     *
     * @return type - the type to be get
     */

    public String getType() {
        return type;
    }

    /**
     * This is a setter which set the type
     *
     * @param type - the type to be set
     */

    public void setType(String type) {
        this.type = type;
    }

    /**
     * This is a getter which get the type
     *
     * @return type - the type to be get
     */

    public String getName() {
        return name;
    }

    /**
     * This is a setter which set the name
     *
     * @param name - the name to be set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is a getter which get the lore
     *
     * @return lore - the lore to be get
     */

    public List<String> getLore() {
        return lore;
    }

    /**
     * This is a setter which set the lore
     *
     * @param lore - the lore to be set
     */

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    /**
     * This is a getter which get the multiplicator
     *
     * @return multiplicator - the multiplicator to be get
     */

    public int getMultiplicator() {
        return multiplicator;
    }

    /**
     * This is a setter which set the multiplicator
     *
     * @param multiplicator - the multiplicator to be set
     */

    public void setMultiplicator(int multiplicator) {
        this.multiplicator = multiplicator;
    }

    /**
     * This is a getter which get the duration
     *
     * @return duration - the duration to be get
     */

    public int getDuration() {
        return duration;
    }

    /**
     * This is a setter which set the duration
     *
     * @param duration - the type to be set
     */

    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * This is a getter which get the type
     *
     * @return type - the type to be get
     */

    public String getMaterial() {
        return material;
    }

    /**
     * This is a setter which set the material
     *
     * @param material - the material to be set
     */

    public void setMaterial(String material) {
        this.material = material;
    }

    /**
     * This is a getter which get the customModelData
     *
     * @return customModelData - the customModelData to be get
     */

    public int getCustomModelData() {
        return customModelData;
    }

    /**
     * This is a setter which set the customModelData
     *
     * @param customModelData - the customModelData to be set
     */

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
