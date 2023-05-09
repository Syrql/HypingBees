package fr.syrql.hypingbees.commands;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.bees.data.Bees;
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
import java.util.Collections;
import java.util.List;

public class HBeesGiveCommand extends ACommand implements TabCompleter {

    private final HypingBees hypingBees;

    public HBeesGiveCommand(HypingBees hypingBees) {
        super(hypingBees, "hbeesgive", "permission.beesgive", true);
        this.hypingBees = hypingBees;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length < 3) {
            sender.sendMessage("§cUtilisation: /beesgive <player> <type> <number>");
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);

        if (target == null) {
            sender.sendMessage("§cLe joueur n'est pas en ligne ou n'a pas été trouvé.");
            return true;
        }

        Bees bees = this.hypingBees.getBeesManager().getBeesByType(args[1]);

        if (bees == null) {
            sender.sendMessage("§cCette abeille n'existe pas.");
            return true;
        }

        try {
            int number = Integer.parseInt(args[2]);
            ItemStack itemStack = new ItemStack(bees.toItemStack());
            itemStack.setAmount(number);

            target.getInventory().addItem(itemStack);

            target.sendMessage("§aTu as reçu " + number + " abeilles.");
            sender.sendMessage("§aTu as envoyé " + number + " abeilles à " + target.getName() + ".");
            return true;
        } catch (NumberFormatException e) {
            sender.sendMessage("§cLe nombre renseigné est invalide.");
        }

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1)
            return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();

        if(args.length == 2) {
            List<String> bees = new ArrayList<>();

            this.hypingBees.getBeesManager().getBeesList().forEach(bee -> bees.add(bee.getType()));
            return bees;
        }

        return Collections.emptyList();
    }
}
