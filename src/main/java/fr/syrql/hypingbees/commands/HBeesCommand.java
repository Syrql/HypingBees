package fr.syrql.hypingbees.commands;

import fr.syrql.hypingbees.HypingBees;
import fr.syrql.hypingbees.utils.command.ACommand;
import org.bukkit.command.CommandSender;

public class HBeesCommand extends ACommand {

    private final HypingBees hypingBees;

    public HBeesCommand(HypingBees hypingBees) {
        super(hypingBees, "hbees", "permission.hbees", true);
        this.hypingBees = hypingBees;
    }

    @Override
    public boolean execute(CommandSender sender, String[] args) {

        if (args.length == 0) {
            sender.sendMessage("§c/hbees reload");
            return true;
        }

        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("reload")) {
                this.hypingBees.reload();
                sender.sendMessage("§cTu viens de reload le plugin !");
                return true;
            }
        }
        return true;
    }
}
