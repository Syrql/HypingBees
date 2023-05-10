package fr.syrql.hypingbees.listeners;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.buyable.data.BuyableSlot;
import fr.syrql.hypingbees.configuration.Configuration;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import world.bentobox.bentobox.BentoBox;
import world.bentobox.bentobox.api.user.User;
import world.bentobox.bentobox.database.objects.Island;

import java.util.LinkedList;

public class BeehiveListener implements Listener {

    private final HypingBees hypingBees;
    private final LinkedList<BuyableSlot> buyableSlots;
    private final Configuration configuration;
    public BeehiveListener(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.buyableSlots = this.hypingBees.getBuyableManager().getBuyableLines();
        this.configuration = hypingBees.getConfiguration();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onInteract(PlayerInteractEvent event) {

        // Declaring fields
        Player player = event.getPlayer();
        Block block = event.getClickedBlock();
        Action action = event.getAction();
        User user = BentoBox.getInstance().getPlayers().getUser(player.getName());

        // Checks for targeting beehive
        if (block == null) return;
        if (action != Action.RIGHT_CLICK_BLOCK) return;
        if (block.getType() != Material.BEEHIVE) return;
        if (user == null) return;

        // Get player island
        Island island = BentoBox.getInstance().getIslands().getIsland(player.getWorld(), user);

        // Check if island is null or not
        if (island == null) {
            player.sendMessage(this.configuration.getNoIsland());
            event.setCancelled(true);
            return;
        }

        // Check if beehive is null
        if (this.hypingBees.getBeehiveManager().getBeehiveByLocation(block.getLocation()) == null) {
            this.hypingBees.getBeehiveManager().createBeehive(block, island, this.buyableSlots);
        }

        // Get beehive
        Beehive beehive = this.hypingBees.getBeehiveManager().getBeehiveByLocation(block.getLocation());

        // open beehive inventory
        beehive.openBeehiveInventory(this.configuration, hypingBees, player);

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onPlace(BlockPlaceEvent event) {

        // Declaring fields
        Player player = event.getPlayer();
        Block block = event.getBlockPlaced();
        User user = BentoBox.getInstance().getPlayers().getUser(player.getName());
        // Checks for targeting beehive
        if (block.getType() != Material.BEEHIVE) return;
        if (user == null) return;

        // Get player island
        Island island = BentoBox.getInstance().getIslands().getIsland(player.getWorld(), user);

        if (island == null) {
            player.sendMessage(this.configuration.getNoIsland());
            event.setCancelled(true);
            return;
        }

        // Create Beehive
        this.hypingBees.getBeehiveManager().createBeehive(block, island, this.buyableSlots);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOW)
    public void onBreak(BlockBreakEvent event) {

        // Declaring fields
        Player player = event.getPlayer();
        Block block = event.getBlock();
        User user = BentoBox.getInstance().getPlayers().getUser(player.getName());

        // Checks for targeting beehive
        if (block.getType() != Material.BEEHIVE) return;
        if (user == null) return;

        // Get Player island
        Island island = BentoBox.getInstance().getIslands().getIsland(player.getWorld(), user);

        if (island == null) {
            player.sendMessage(configuration.getNoIsland());
            event.setCancelled(true);
            return;
        }

        // Get beehive by location
        Beehive beehive = hypingBees.getBeehiveManager().getBeehiveByLocation(block.getLocation());
        //check non-null beehive
        if (beehive == null) return;
        // Check island id
        if (!beehive.getIslandUUID().equalsIgnoreCase(island.getUniqueId())) {
            player.sendMessage(configuration.getNotYourBeehive());
            event.setCancelled(true);
        }

        // Remove beehive from world
        this.hypingBees.getProvider().remove(beehive.getIslandUUID() + "-" + beehive.getId());

        // destroy beehive hologram
        beehive.destroyHologram();

    }

    @EventHandler
    public void onClose(InventoryCloseEvent event) {
        Player player = (Player) event.getPlayer();

        // remove current openned beehive
        this.hypingBees.getBeehiveManager().removeCurrentBeehive(player.getUniqueId());
    }
}
