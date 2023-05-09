package fr.syrql.hypingbees.buyable.manager;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.buyable.data.BuyableLine;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import fr.syrql.hypingbees.utils.item.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedList;
import java.util.List;

public class BuyableManager {

    private final HypingBees hypingBees;
    private final LinkedList<BuyableLine> buyableLines;
    private String lockBuyableName;
    private Material lockBuyableType;
    private List<String> lockBuyableLore;
    private ItemStack itemStack;

    public BuyableManager(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.buyableLines = new LinkedList<>();
        this.init();
        this.lockBuyableName = this.hypingBees.getConfigManager().getString("beehives.inventory.locked-buyables.name");
        this.lockBuyableType = Material.getMaterial(this.hypingBees.getConfigManager().getString("beehives.inventory.locked-buyables.material"));
        this.lockBuyableLore = this.hypingBees.getConfigManager().getStringList("beehives.inventory.locked-buyables.lore");
    }

    private void init() {
        for (String key : this.hypingBees.getConfig().getConfigurationSection("beehives.inventory.buyables").getKeys(false)) {
            String path = "beehives.inventory.buyables." + key;

            ConfigManager config = this.hypingBees.getConfigManager();

            BuyableLine buyableLine = new BuyableLine(config.getInt(path + ".slot"), config.getInt(path + ".price"),
                    config.getString(path + ".placeholder"),
                    config.getStringList(path + ".commands"));
            this.buyableLines.add(buyableLine);
        }
    }

    public ItemStack getBuyableItemStack() {
        return new ItemBuilder(this.lockBuyableType)
                .setName(this.lockBuyableName)
                .setLore(this.lockBuyableLore)
                .toItemStack();
    }

    public LinkedList<BuyableLine> getBuyableLines() {
        return buyableLines;
    }
}
