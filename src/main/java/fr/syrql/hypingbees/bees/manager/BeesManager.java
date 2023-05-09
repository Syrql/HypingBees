package fr.syrql.hypingbees.bees.manager;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BeesManager {

    private final HypingBees hypingBees;
    private final ArrayList<Bees> beesList;

    public BeesManager(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.beesList = new ArrayList<>();
        this.init();
    }

    private void init() {

        for (String key : this.hypingBees.getConfig().getConfigurationSection("bees").getKeys(false)) {
            String path = "bees." + key;

            ConfigManager config = this.hypingBees.getConfigManager();

            List<String> lore = new ArrayList<>();
            config.getStringList(path + ".lore").forEach(line -> lore.add(ChatColor.translateAlternateColorCodes('&', line)));

            Bees bees = new Bees(config.getString(path + ".type"),
                    config.getString(path + ".name"),
                    lore, config.getString(path + ".material"),
                    config.getBoolean(path + ".glow"), config.getInt(path + ".modeldata"),
                    config.getStringList(path + ".production.rewards"), config.getStringList(path + ".production.commands"));

            this.beesList.add(bees);

        }
    }

    public Bees getBeesByItemStack(ItemStack itemStack) {
        return this.beesList.stream().filter(bees -> itemStack.isSimilar(bees.toItemStack())).findFirst().orElse(null);
    }

    public Bees getBeesByType(String type) {
        return this.beesList.stream().filter(bees -> bees.getType().equalsIgnoreCase(type)).findFirst().orElse(null);
    }

    public Bees getBeesBySlot(Beehive beehive, int slot) {
        return beehive.getCurrentBees().get(slot);
    }

    public Boost getWaitBoostBySlot(Beehive beehive, int slot) {
        return beehive.getBoosts().get(slot);
    }

    public ArrayList<Bees> getBeesList() {
        return beesList;
    }
}
