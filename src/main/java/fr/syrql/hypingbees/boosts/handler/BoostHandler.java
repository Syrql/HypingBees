package fr.syrql.hypingbees.boosts.handler;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.boosts.factory.BoostFactory;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class BoostHandler {

    private final HypingBees hypingBees;
    private final LinkedList<Boost> boosts = new LinkedList<>();
    private final List<Integer> slotList;
    private final int currentBoostSlot;

    public BoostHandler(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.slotList = this.hypingBees.getConfigManager().getIntList("boosts.slots");
        this.currentBoostSlot = this.hypingBees.getConfigManager().getInt("boosts.currentboost-slot");
        this.init();
    }

    private void init() {
        for (String key : this.hypingBees.getConfig().getConfigurationSection("boosts.types").getKeys(false)) {
            String path = "boosts.types." + key;

            ConfigManager config = this.hypingBees.getConfigManager();

            this.boosts.add(new BoostFactory()
                    .create(key, config.getString(path + ".name"), config.getStringList(path + ".lore"),
                            config.getInt(path + ".speed-multiplier"), config.getInt(path + ".duration"),
                            config.getString(path + ".material"), config.getInt(path + ".customModelData")));
        }
    }

    public Boost getBoostByType(String type) {
        return this.boosts.stream().filter(boost -> boost.getType().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public Boost getBoostByItemStack(ItemStack itemStack) {
        return this.getBoosts().stream().filter(boost ->
                itemStack.isSimilar(boost.toItemStack())).findFirst().orElse(null);
    }

    public List<Integer> getSlotList() {
        return slotList;
    }

    public LinkedList<Boost> getBoosts() {
        return boosts;
    }

    public int getCurrentBoostSlot() {
        return currentBoostSlot;
    }
}
