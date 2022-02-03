package me.itsadrift.adriftbattleboxbeta;

import me.itsadrift.adriftbattleboxbeta.utils.HexUtils;
import me.itsadrift.adriftbattleboxbeta.utils.ItemBuilder;
import me.itsadrift.adriftbattleboxbeta.utils.LocUtils;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.ArrayList;
import java.util.List;

public class BattleBoxSetupListener implements Listener {

    private String prefix = "&a&lAdriftBattleBox BETA &7» &f";

    private AdriftBattleBoxBETA main;
    public BattleBoxSetupListener(AdriftBattleBoxBETA main) {
        this.main = main;
    }

    public void onInteract(PlayerInteractEvent e) {
        if (e.getPlayer().getInventory().getItemInMainHand() != null && e.getPlayer().getInventory().getItemInMainHand().getType() != Material.AIR) {
            String itemID = new ItemBuilder(e.getPlayer().getInventory().getItemInMainHand()).getGUITag();

            if (itemID.equalsIgnoreCase("battlebox_setcenterblocks")) {
                if (e.getClickedBlock() != null && e.getClickedBlock().getType() != Material.AIR) {
                    List<String> l = new ArrayList<>();
                    if (main.getConfig().contains("locations.centerblocks")) {
                        l = main.getConfig().getStringList("locations.centerblocks");
                    }
                    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        l.add(LocUtils.encode(e.getClickedBlock().getLocation()));
                        main.getConfig().set("locations.centerblocks", l);
                        e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have added a &aCenter Block &flocation"));
                    } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                        l.remove(LocUtils.encode(e.getClickedBlock().getLocation()));
                        main.getConfig().set("locations.centerblocks", l);
                        e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have removed a &aCenter Block &flocation"));
                    }
                    main.saveConfig();

                }
            } else if (itemID.equalsIgnoreCase("battlebox_redwall")) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    main.getConfig().set("locations.redwall.pos1", LocUtils.encode(e.getClickedBlock().getLocation()));
                    e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aRed Wall Pos 1 &flocation"));
                } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    main.getConfig().set("locations.redwall.pos2", LocUtils.encode(e.getClickedBlock().getLocation()));
                    e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aRed Wall Pos 2 &flocation"));
                }
                main.saveConfig();
            } else if (itemID.equalsIgnoreCase("battlebox_bluewall")) {
                if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                    main.getConfig().set("locations.bluewall.pos1", LocUtils.encode(e.getClickedBlock().getLocation()));
                    e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aBlue Wall Pos 1 &flocation"));
                } else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                    main.getConfig().set("locations.bluewall.pos2", LocUtils.encode(e.getClickedBlock().getLocation()));
                    e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aBlue Wall Pos 2 &flocation"));
                }
                main.saveConfig();
            } else if (itemID.equalsIgnoreCase("battlebox_redlobby")) {
                main.getConfig().set("locations.redlobby", LocUtils.encode(e.getPlayer().getLocation()));
                main.saveConfig();
                e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aRed Lobby &flocation"));
            } else if (itemID.equalsIgnoreCase("battlebox_bluelobby")) {
                main.getConfig().set("locations.bluelobby", LocUtils.encode(e.getPlayer().getLocation()));
                main.saveConfig();
                e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aBlue Lobby &flocation"));
            } else if (itemID.equalsIgnoreCase("battlebox_redcage")) {
                main.getConfig().set("locations.redcage", LocUtils.encode(e.getPlayer().getLocation()));
                main.saveConfig();
                e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aRed Cage &flocation"));
            } else if (itemID.equalsIgnoreCase("battlebox_bluecage")) {
                main.getConfig().set("locations.bluecage", LocUtils.encode(e.getPlayer().getLocation()));
                main.saveConfig();
                e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aBlue Cage &flocation"));
            } else if (itemID.equalsIgnoreCase("battlebox_lobby")) {
                main.getConfig().set("locations.lobby", LocUtils.encode(e.getPlayer().getLocation()));
                main.saveConfig();
                e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have set the &aLobby &flocation"));
            } else if (itemID.equalsIgnoreCase("battlebox_harmingpotion")) {
                List<String> l = new ArrayList<>();
                if (main.getConfig().contains("locations.harmingpotion")) {
                    l = main.getConfig().getStringList("locations.harmingpotion");
                }
                if (l.size() == 2) {
                    l.remove(0);
                }
                l.add(LocUtils.encode(e.getPlayer().getLocation()));
                main.getConfig().set("locations.harmingpotion", l);
                main.saveConfig();
                e.getPlayer().sendMessage(HexUtils.colour(prefix + "&fYou have added a &aHarming Potion &flocation"));
            }

            if (itemID.contains("battlebox_")) {
                main.getGameManager().loadLocations();
            }

        }
    }

}
