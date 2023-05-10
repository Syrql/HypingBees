package fr.syrql.hypingbees.buyable.handler;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import fr.syrql.hypingbees.buyable.factory.BuyableSlotFactory;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class BuyableSlotHandler {

    private final HypingBees hypingBees;
    private final LinkedList<BuyableSlot> buyableSlots = new LinkedList<>();
    private final String lockBuyableName;
    private final Material lockBuyableType;
    private final List<String> lockBuyableLore;

    public BuyableSlotHandler(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.lockBuyableName = this.hypingBees.getConfigManager().getString("beehives.inventory.locked-buyables.name");
        this.lockBuyableType = Material.getMaterial(this.hypingBees.getConfigManager().getString("beehives.inventory.locked-buyables.material"));
        this.lockBuyableLore = this.hypingBees.getConfigManager().getStringList("beehives.inventory.locked-buyables.lore");
        this.init();
    }

    private void init() {
        for (String key : this.hypingBees.getConfig().getConfigurationSection("beehives.inventory.buyables").getKeys(false)) {
            String path = "beehives.inventory.buyables." + key;

            ConfigManager config = this.hypingBees.getConfigManager();

            this.buyableSlots.add(new BuyableSlotFactory().create(config.getInt(path + ".slot"), config.getInt(path + ".price"),
                    config.getString(path + ".placeholder"),
                    config.getStringList(path + ".commands")));
        }
    }

    public ItemStack getBuyableItemStack() {
        return new ItemBuilder(this.lockBuyableType)
                .setName(this.lockBuyableName)
                .setLore(this.lockBuyableLore)
                .toItemStack();
    }

    public LinkedList<BuyableSlot> getBuyableSlots() {
        return buyableSlots;
    }
}
