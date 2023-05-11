package fr.syrql.hypingbees.bees.handler;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.bees.factory.BeesFactory;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class BeesHandler {

    private final HypingBees hypingBees;
    private final ArrayList<Bees> beesList;

    public BeesHandler(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.beesList = new ArrayList<>();
        this.init();
    }

    private void init() {

        ConfigManager config = this.hypingBees.getConfigManager();

        for (String key : this.hypingBees.getConfig().getConfigurationSection("bees").getKeys(false)) {
            String path = "bees." + key;

            List<String> lore = new ArrayList<>();
            config.getStringList(path + ".lore").forEach(line -> lore.add(ChatColor.translateAlternateColorCodes('&', line)));

            this.beesList.add(new BeesFactory().create(config.getString(path + ".type"),
                    config.getString(path + ".name"),
                    lore, config.getString(path + ".material"),
                    config.getBoolean(path + ".glow"), config.getInt(path + ".modeldata"),
                    config.getStringList(path + ".production.rewards"), config.getStringList(path + ".production.commands")));

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
