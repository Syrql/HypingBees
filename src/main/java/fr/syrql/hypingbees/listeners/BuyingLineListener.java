package fr.syrql.hypingbees.listeners;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.buyable.data.BuyableLine;
import fr.syrql.hypingbees.configuration.Configuration;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class BuyingLineListener implements Listener {

    private final HypingBees hypingBees;
    private final Configuration configuration;

    public BuyingLineListener(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.configuration = hypingBees.getConfiguration();
    }

    @EventHandler
    public void onClickBuyable(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        // Check current item
        if (event.getCurrentItem() == null) return;

        // Get current player beehive
        Beehive beehive = this.hypingBees.getBeehiveManager().getCurrentPlayerBeehive(player.getUniqueId());
        // Chekc if beehive is null or not
        if (beehive == null) return;
        // get current line
        ItemStack itemStack = event.getCurrentItem();
        ItemStack buyable = this.hypingBees.getBuyableManager().getBuyableItemStack();
        // Check if item is buyable line or not
        if (!itemStack.isSimilar(buyable)) return;
        // Cancel event
        event.setCancelled(true);
        // Get first buyable line
        BuyableLine buyableLine = beehive.getBuyableLines().get(0);
        // Get current player points money and buyable line price
        double current = Double.parseDouble(PlaceholderAPI.setPlaceholders(player, buyableLine.getPlaceholder()));
        double price = buyableLine.getPrice();
        // Check if player has points or not
        if (price > current) {
            player.sendMessage(this.configuration.getNoMoney());
            return;
        }
        // Remove line from beehive
        beehive.getBuyableLines().remove(beehive.getBuyableLines().get(0));

        // close inventory and send message
        player.closeInventory();
        player.sendMessage(this.configuration.getBuyNewLine());

        // execute config commands
        buyableLine.getCommands().forEach(command ->
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command
                .replace("%player%", player.getName())
                .replace("%prices%", String.valueOf(price))));
    }
}
