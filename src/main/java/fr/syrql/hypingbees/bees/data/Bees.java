package fr.syrql.hypingbees.bees.data;

import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.io.Serializable;
import java.util.List;

public class Bees implements Serializable {

    /**
     * Bees Field type
     *
     * @param type the  bees id.
     * @return Id of the bees.
     */

    private String type;

    /**
     * Bees Field name
     *
     * @param name the  bees name.
     * @return Name of the bees.
     */

    private String name;

    /**
     * Bees Field lore
     *
     * @param lore the bees lore.
     * @return Lore of the bees.
     */

    private List<String> lore;

    /**
     * Bees Field material
     *
     * @param material the  bees material.
     * @return Material of the bees.
     */

    private String material;

    /**
     * Bees Field glow
     *
     * @param glow the  bees glow.
     * @return Glow enchant of the bees.
     */

    private boolean glow;

    /**
     * Bees Field modelData
     *
     * @param modelData the  bees modelData.
     * @return ModelData of the bees.
     */

    private int modelData;

    /**
     * Bees Field rewards
     *
     * @param rewards the  bees material's rewards.
     * @return Rewards of the bees.
     */

    private List<String> rewards;

    /**
     * Bees Field rewardsCommands
     *
     * @param rewardsCommands the  bees rewardsCommands.
     * @return RewardsCommands of the bees.
     */

    private List<String> rewardsCommands;

    /**
     * Constructor for class Bees
     *
     * @param type
     * @param name
     * @param lore
     * @param material
     * @param glow
     * @param modelData
     * @param rewards
     * @param rewardsCommands
     */

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
     * This is a getter which get the name
     *
     * @return name - the name to be get
     */

    public String getName() {
        return name;
    }

    /**
     * This is a setter which set the name
     *
     * @return name - the name to be set
     */

    public void setName(String name) {
        this.name = name;
    }

    /**
     * This is a getter which get the lore
     *
     * @return id - the lore to be get
     */

    public List<String> getLore() {
        return lore;
    }

    /**
     * This is a getter which set the lore
     *
     * @param lore - the lore to be set
     */

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    /**
     * This is a getter which get the material
     *
     * @return material - the material to be get
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
     * This glow a getter which get the glow
     *
     * @return glow - the glow to be get
     */

    public boolean isGlow() {
        return glow;
    }

    /**
     * This is a setter which set the glow
     *
     * @param glow - the id to be set
     */

    public void setGlow(boolean glow) {
        this.glow = glow;
    }

    /**
     * This is a getter which get the modelData
     *
     * @return modelData - the modelData to be get
     */

    public int getModelData() {
        return modelData;
    }

    /**
     * This is a setter which set the modelData
     *
     * @param modelData - the modelData to be set
     */

    public void setModelData(int modelData) {
        this.modelData = modelData;
    }

    /**
     * This is a getter which get the rewards
     *
     * @return rewards - the rewards to be get
     */

    public List<String> getRewards() {
        return rewards;
    }

    /**
     * This is a setter which set the rewards
     *
     * @param rewards - the rewards to be get
     */

    public void setRewards(List<String> rewards) {
        this.rewards = rewards;
    }

    /**
     * This is a getter which get the rewardsCommands
     *
     * @return rewardsCommands - the rewardsCommands to be get
     */

    public List<String> getRewardsCommands() {
        return rewardsCommands;
    }

    /**
     * This is a setter which get the rewardsCommands
     *
     * @param rewardsCommands - the rewardsCommands to be set
     */

    public void setRewardsCommands(List<String> rewardsCommands) {
        this.rewardsCommands = rewardsCommands;
    }

    public ItemStack toItemStack() {
        return new ItemBuilder(Material.getMaterial(this.material))
                .setName(this.name)
                .setLore(this.lore)
                .setCustomModelData(this.modelData).toItemStack();
    }
}
