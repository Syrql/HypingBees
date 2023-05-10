package fr.syrql.hypingbees.commands;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.bees.data.Bees;
import fr.syrql.hypingbees.boosts.data.Boost;
import fr.syrql.hypingbees.configuration.Configuration;
import fr.syrql.hypingbees.utils.command.ACommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class HBeesCommand extends ACommand implements TabCompleter {

    private final HypingBees hypingBees;
    private final Configuration configuration;

    public HBeesCommand(HypingBees hypingBees) {
        super(hypingBees, "hbees", "permission.hbees", true);
        this.hypingBees = hypingBees;
        this.configuration = this.hypingBees.getConfiguration();
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        // Send help message if its not length to 1 or 5
        if (args.length != 1 && args.length != 5) {
            sender.sendMessage("§c/hbees reload");
            sender.sendMessage("§c/hbees give <player> bee <type> <number>");
            sender.sendMessage("§c/hbees give <player> boost <type> <number>");
            return true;
        }

        // Check if args 1 equals to reload
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                this.hypingBees.reload();
                sender.sendMessage("§aTu viens de reload le plugin !");
                return true;
            }
        }

        // Check if length = 5
        if (args.length == 5) {
            // check if it's give args
            if (args[0].equalsIgnoreCase("give")) {
                Player player = Bukkit.getPlayer(args[1]);

                // Check player not-null
                if (player == null) {
                    sender.sendMessage(configuration.getPlayerNotOnline());
                    return true;
                }

                // try with switch to get bee or boost args
                switch (args[2]) {
                    // Case bee
                    case "bee" -> {

                        // Find bee
                        Bees bees = this.hypingBees.getBeesManager().getBeesByType(args[3]);

                        // Check non-null
                        if (bees == null) {
                            sender.sendMessage("§cCette abeille n'existe pas.");
                            return true;
                        }

                        // try if args 5 is number
                        try {

                            // from string to number
                            int number = Integer.parseInt(args[4]);
                            // Clone itemstack
                            ItemStack itemStack = bees.toItemStack().clone();
                            // Change amount of the item
                            itemStack.setAmount(number);
                            // Add item to the target inventory
                            player.getInventory().addItem(itemStack);
                            // You receive number of bees
                            player.sendMessage("§aTu as reçu " + number + " abeilles.");
                            // Send message to sender to prevent send
                            sender.sendMessage("§aTu as envoyé " + number + " abeilles à " + player.getName() + ".");
                            return true;
                            // Catch if its not a number
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cLe nombre renseigné est invalide.");
                        }

                        return true;
                    }
                    // Case boost
                    case "boost" -> {
                        // Find boost
                        Boost boost = this.hypingBees.getBoostManager().getBoostByType(args[3]);

                        // Check not-null
                        if (boost == null) {
                            sender.sendMessage("§cCette abeille n'existe pas.");
                            return true;
                        }

                        // try if args 5 is number
                        try {

                            // from string to int
                            int number = Integer.parseInt(args[4]);
                            // Clone itemstack
                            ItemStack itemStack = boost.toItemStack().clone();
                            // Set amount of the itemstack
                            itemStack.setAmount(number);
                            // Add to target inventory
                            player.getInventory().addItem(itemStack);
                            // Receive number of boost
                            player.sendMessage("§aTu as reçu " + number + " boost.");
                            // Send message to sender to prevent send
                            sender.sendMessage("§aTu as envoyé " + number + " boosts à " + player.getName() + ".");
                            return true;
                            // Catch if its not a number
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cLe nombre renseigné est invalide.");
                        }

                        return true;
                    }
                }
            }
        }
        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        // Send list of string for the first args
        if (args.length == 1)
            return Arrays.asList("give", "reload");
        // Send list of player name for the second args
        if (args.length == 2)
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        // Send list of string for the third args
        if (args.length == 3)
            return Arrays.asList("bee", "boost");
        // Send list of bee/boost string type for the first args
        if (args.length == 4) {
            // Send list of string bee for the first args
            if (args[2].equalsIgnoreCase("bee")) {
                List<String> bees = new ArrayList<>();

                this.hypingBees.getBeesManager().getBeesList().forEach(bee -> bees.add(bee.getType()));
                return bees;
                // Send list of string boost type for the first args
            } else if (args[2].equalsIgnoreCase("boost")) {
                List<String> boosts = new ArrayList<>();

                this.hypingBees.getBoostManager().getBoosts().forEach(boost -> boosts.add(boost.getType()));
                return boosts;
            } else return Collections.emptyList();
        }
        // Send empty list for others args

        return Collections.emptyList();
    }
}
