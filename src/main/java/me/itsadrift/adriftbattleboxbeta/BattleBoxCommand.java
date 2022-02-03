package me.itsadrift.adriftbattleboxbeta;

import me.itsadrift.adriftbattleboxbeta.utils.HexUtils;
import me.itsadrift.adriftbattleboxbeta.utils.SetupItems;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class BattleBoxCommand implements CommandExecutor {

    private AdriftBattleBoxBETA main;
    public BattleBoxCommand(AdriftBattleBoxBETA main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 1) {

            if (args[0].equalsIgnoreCase("setup")) {
                player.getInventory().addItem(SetupItems.getItems());
            } else if (args[0].equalsIgnoreCase("start")) {
                main.getGameManager().createNewTeamSelector();
                sender.sendMessage(HexUtils.colour("&a&lAdriftBattleBox BETA &7» &fYou have setup a game!"));
            }
        }

        return false;
    }
}
