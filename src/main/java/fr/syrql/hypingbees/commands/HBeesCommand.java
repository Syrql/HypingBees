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

        if (args.length == 0) {
            sender.sendMessage("§c/hbees reload");
            sender.sendMessage("§c/hbees give <player> bee <type> <number>");
            sender.sendMessage("§c/hbees give <player> boost <type> <number>");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("reload")) {
                this.hypingBees.reload();
                sender.sendMessage("§cTu viens de reload le plugin !");
                return true;
            }
        }

        if (args.length == 5) {
            if (args[0].equalsIgnoreCase("give")) {
                Player player = Bukkit.getPlayer(args[1]);

                if (player == null) {
                    sender.sendMessage(configuration.getPlayerNotOnline());
                    return true;
                }

                switch (args[2]) {
                    case "bee" -> {

                        Bees bees = this.hypingBees.getBeesManager().getBeesByType(args[3]);

                        if (bees == null) {
                            sender.sendMessage("§cCette abeille n'existe pas.");
                            return true;
                        }

                        try {
                            int number = Integer.parseInt(args[4]);
                            ItemStack itemStack = new ItemStack(bees.toItemStack());
                            itemStack.setAmount(number);

                            player.getInventory().addItem(itemStack);

                            player.sendMessage("§aTu as reçu " + number + " abeilles.");
                            sender.sendMessage("§aTu as envoyé " + number + " abeilles à " + player.getName() + ".");
                            return true;
                        } catch (NumberFormatException e) {
                            sender.sendMessage("§cLe nombre renseigné est invalide.");
                        }

                        return true;
                    }

                    case "boost" -> {

                        Boost boost = this.hypingBees.getBoostManager().getBoostByType(args[3]);

                        if (boost == null) {
                            sender.sendMessage("§cCette abeille n'existe pas.");
                            return true;
                        }

                        try {
                            int number = Integer.parseInt(args[4]);
                            ItemStack itemStack = new ItemStack(boost.toItemStack());
                            itemStack.setAmount(number);

                            player.getInventory().addItem(itemStack);

                            player.sendMessage("§aTu as reçu " + number + " boost.");
                            sender.sendMessage("§aTu as envoyé " + number + " boosts à " + player.getName() + ".");
                            return true;
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
        if (args.length == 1)
            return Arrays.asList("give", "reload");

        if (args.length == 2)
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();

        if (args.length == 3)
            return Arrays.asList("bee", "boost");

        if (args.length == 4) {
            if (args[2].equalsIgnoreCase("bee")) {
                List<String> bees = new ArrayList<>();

                this.hypingBees.getBeesManager().getBeesList().forEach(bee -> bees.add(bee.getType()));
                return bees;

            } else if (args[2].equalsIgnoreCase("boost")) {
                List<String> boosts = new ArrayList<>();

                this.hypingBees.getBoostManager().getBoosts().forEach(boost -> boosts.add(boost.getType()));
                return boosts;
            } else return Collections.emptyList();
        }

        return Collections.emptyList();
    }
}
