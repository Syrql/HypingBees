package fr.syrql.hypingbees.utils.command;

import fr.syrql.hypingbees.HypingBees;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class ACommand implements CommandExecutor, TabCompleter {

    private final String commandName;
    private final String permission;
    private final boolean consoleCanExecute;
    private final HypingBees hypingBees;

    public ACommand(HypingBees hypingBees, String commandName, String permission, boolean consoleCanExecute) {
        this.permission = permission;
        this.commandName = commandName;
        this.consoleCanExecute = consoleCanExecute;
        this.hypingBees = hypingBees;
        hypingBees.getCommand(commandName).setExecutor(this);
        hypingBees.getCommand(commandName).setTabCompleter(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        MiniMessage miniMessage = MiniMessage.miniMessage();

        if (!command.getLabel().equalsIgnoreCase(commandName))
            return true;

        if (!consoleCanExecute && !(sender instanceof Player)) {
            sender.sendMessage(miniMessage.deserialize(hypingBees.getConfigManager().getString("not-player")));
            return true;
        }

        if (!sender.hasPermission(permission)) {
            sender.sendMessage(miniMessage.deserialize(hypingBees.getConfigManager().getString("no-permission")));
            return true;
        }

        return execute(sender, args);
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        return complete(sender, command, label, args);
    }

    public abstract boolean execute(CommandSender sender, String[] args);

    public abstract List<String> complete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args);
}
