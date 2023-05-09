package fr.syrql.hypingbees;

import fr.syrql.hypingbees.beehives.data.Beehive;
import fr.syrql.hypingbees.beehives.manager.BeehiveManager;
import fr.syrql.hypingbees.beehives.provider.BeehiveProvider;
import fr.syrql.hypingbees.bees.manager.BeesManager;
import fr.syrql.hypingbees.boosts.manager.BoostManager;
import fr.syrql.hypingbees.buyable.manager.BuyableManager;
import fr.syrql.hypingbees.commands.HBeesGiveCommand;
import fr.syrql.hypingbees.commands.HBoostGiveCommand;
import fr.syrql.hypingbees.configuration.Configuration;
import fr.syrql.hypingbees.listeners.*;
import fr.syrql.hypingbees.task.BeehiveTask;
import fr.syrql.hypingbees.utils.config.ConfigManager;
import fr.syrql.hypingbees.utils.files.IOUtil;
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
        super.onEnable();
        this.saveDefaultConfig();

        this.registerProviders();
        this.registerManagers();
        this.registerListeners();
        this.registerCommands();

        this.getServer().getScheduler().runTaskTimerAsynchronously(this, new BeehiveTask(this), 1L, 20L);

        this.getServer().getScheduler().scheduleSyncDelayedTask(this,
                () -> this.provider.getBeehives()
                        .forEach(beehive -> beehive.createHologram(this.getConfiguration().getHologramsLine(beehive))), 40L);
    }

    @Override
    public void onDisable() {
        super.onDisable();

        this.provider.getBeehives().forEach(Beehive::destroyHologram);

        this.provider.write();
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
        new HBeesGiveCommand(this);
        new HBoostGiveCommand(this);
        this.getCommand("hbeesgive").setTabCompleter(new HBeesGiveCommand(this));
        this.getCommand("hboostgive").setTabCompleter(new HBoostGiveCommand(this));
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
