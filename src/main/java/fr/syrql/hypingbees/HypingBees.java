package fr.syrql.hypingbees;

import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.manager.BeehiveManager;
import fr.syrql.hypingbees.beehives.provider.BeehiveProvider;
import fr.syrql.hypingbees.bees.manager.BeesManager;
import fr.syrql.hypingbees.boosts.manager.BoostManager;
import fr.syrql.hypingbees.buyable.manager.BuyableManager;
import fr.syrql.hypingbees.commands.HBeesCommand;
import fr.syrql.hypingbees.configuration.Configuration;
import fr.syrql.hypingbees.listeners.*;
import fr.syrql.hypingbees.task.BeehiveInventoryTask;
import fr.syrql.hypingbees.task.BeehiveTask;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import fr.syrql.hypingbees.utils.files.IOUtil;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HypingBees extends JavaPlugin {

    private IOUtil ioUtil;
    private BeehiveProvider provider;
    private BeehiveManager beehiveManager;
    private ConfigManager configManager;
    private Configuration configuration;
    private BuyableManager buyableManager;
    private BeesManager beesManager;
    private BoostManager boostManager;

    @Override
    public void onEnable() {
        // setup onEnable
        super.onEnable();
        // Save default config.yml file
        this.saveDefaultConfig();

        // register all providers
        this.registerProviders();
        // registerManagers
        this.registerManagers();
        // register listeners
        this.registerListeners();
        // registerCommands
        this.registerCommands();
        // Setup task's async
        this.getServer().getScheduler().runTaskTimerAsynchronously(this, new BeehiveTask(this), 1L, 20L);
        this.getServer().getScheduler().runTaskTimerAsynchronously(this, new BeehiveInventoryTask(this), 1L, 20L);
        // Setup every holo's
        this.getServer().getScheduler().scheduleSyncDelayedTask(this,
                () -> this.provider.getBeehives()
                        .forEach(beehive -> beehive.createHologram(this.getConfiguration().getHologramsLine(beehive))), 40L);
    }

    @Override
    public void onDisable() {
        // Disable methode
        super.onDisable();
        // destroy every holo's
        this.provider.getBeehives().forEach(Beehive::destroyHologram);
        // Write every keys
        this.provider.write();
    }

    public void reload() {
        // Async reload
        Bukkit.getScheduler().runTaskAsynchronously(this, () -> {
            // Reload bukkit config
            reloadConfig();
            // Reload configmanager
            this.configManager = new ConfigManager(this);
            // Reload Configuration
            configuration = new Configuration(this);
            // Reload ioutil
            this.ioUtil = new IOUtil();
            // Write beehive's
            this.provider.write();
            // Reload provider
            this.provider = new BeehiveProvider(this);
            // Read every beehive's json
            this.provider.read();
            // Register manager's
            this.beehiveManager = new BeehiveManager(this);
            this.buyableManager = new BuyableManager(this);
            this.beesManager = new BeesManager(this);
            this.boostManager = new BoostManager(this);
        });
    }

    private void registerManagers() {
        this.configManager = new ConfigManager(this);
        this.configuration = new Configuration(this);
        this.beehiveManager = new BeehiveManager(this);
        this.buyableManager = new BuyableManager(this);
        this.beesManager = new BeesManager(this);
        this.boostManager = new BoostManager(this);
    }

    private void registerProviders() {
        this.ioUtil = new IOUtil();
        this.provider = new BeehiveProvider(this);
        this.provider.read();

    }


    private void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new BeehiveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new InventoryBeehiveListener(this), this);
        this.getServer().getPluginManager().registerEvents(new BoostListener(this), this);
        this.getServer().getPluginManager().registerEvents(new RewardsListener(this), this);
        this.getServer().getPluginManager().registerEvents(new BuyingLineListener(this), this);
    }

    private void registerCommands() {
        new HBeesCommand(this);
        this.getCommand("hbees").setTabCompleter(new HBeesCommand(this));
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }

    public BeehiveProvider getProvider() {
        return provider;
    }

    public BeehiveManager getBeehiveManager() {
        return beehiveManager;
    }

    public IOUtil getIoUtil() {
        return ioUtil;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public BuyableManager getBuyableManager() {
        return buyableManager;
    }

    public BeesManager getBeesManager() {
        return beesManager;
    }

    public BoostManager getBoostManager() {
        return boostManager;
    }
}
