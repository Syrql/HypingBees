package fr.syrql.hypingbees.beehives.manager;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.data.NamedInventory;
import fr.syrql.hypingbees.beehives.data.Rewards;
import fr.syrql.hypingbees.beehives.factory.BeehiveFactory;
import fr.syrql.hypingbees.buyable.data.BuyableLine;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import org.bukkit.Location;
import org.bukkit.block.Block;
import world.bentobox.bentobox.database.objects.Island;

import java.util.*;

public class BeehiveManager {

    private final HypingBees hypingBees;
    private final HashMap<UUID, Beehive> currentBeehive;
    private final List<NamedInventory> namedInventoryList;
    private final int inventorySize;
    private final String inventoryName;

    public BeehiveManager(HypingBees hypingBees) {
        this.hypingBees = hypingBees;
        this.currentBeehive = new HashMap<>();
        this.namedInventoryList = new ArrayList<>();
        this.inventorySize = this.hypingBees.getConfigManager().getInt("beehives.inventory.size");
        this.inventoryName = this.hypingBees.getConfigManager().getString("beehives.inventory.default-name");
        this.setupNamedInventory();
    }

    private void setupNamedInventory() {
        for (String key : this.hypingBees.getConfig().getConfigurationSection("beehives.inventory.name").getKeys(false)) {
            String path = "beehives.inventory.name." + key;

            ConfigManager config = this.hypingBees.getConfigManager();

            NamedInventory namedInventory = new NamedInventory(config.getInt(path + ".time"), config.getString(path + ".name"));

            this.namedInventoryList.add(namedInventory);
        }
    }

    public Beehive getBeehiveByLocation(Location location) {
        return hypingBees.getProvider().getBeehives().stream()
                .filter(beehive -> beehive.toLocation().equals(location))
                .findFirst().orElse(null);
    }

    public Beehive getCurrentPlayerBeehive(UUID uuid) {
        if (!this.currentBeehive.containsKey(uuid) || this.currentBeehive.get(uuid) == null) return null;

        return this.currentBeehive.get(uuid);
    }

    public void createBeehive(Block block, Island island, LinkedList<BuyableLine> buyableLines) {

        String id = UUID.randomUUID().toString().substring(1, 6);

        Rewards rewards = new Rewards(new ArrayList<>(), new ArrayList<>());

        this.hypingBees.getProvider().provide(island.getUniqueId() + "-" + id,
                new BeehiveFactory().create(id,
                        island.getUniqueId(),
                        this.hypingBees.getConfiguration().getCycleTime(),
                        new HashMap<>(),
                        buyableLines,
                        block.getLocation(), rewards, new LinkedHashMap<>()));

        Beehive beehive = this.hypingBees.getProvider().get(island.getUniqueId() + "-" + id);

        beehive.createHologram(this.hypingBees.getConfiguration().getHologramsLine(beehive));
    }

    public void putCurrentBeehive(UUID uuid, Beehive beehive) {
        this.currentBeehive.put(uuid, beehive);
    }

    public boolean canPlaceBees(int slot) {
        return this.hypingBees.getConfiguration().getSlots().contains(slot);
    }

    public void removeCurrentBeehive(UUID uuid) {
        this.currentBeehive.remove(uuid);
    }

    public HashMap<UUID, Beehive> getCurrentBeehive() {
        return currentBeehive;
    }

    public List<NamedInventory> getNamedInventoryList() {
        return namedInventoryList;
    }

    public int getInventorySize() {
        return inventorySize;
    }

    public String getInventoryName() {
        return inventoryName;
    }
}
