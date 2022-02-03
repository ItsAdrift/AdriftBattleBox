package me.itsadrift.adriftbattleboxbeta.utils;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class SetupItems {

    public static ItemStack[] getItems() {
        List<ItemStack> l = new ArrayList<>();

        ItemBuilder centerLocations = new ItemBuilder(Material.BLAZE_ROD);
        centerLocations.setGUITag("battlebox_setcenterblocks");
        centerLocations.setDisplayName("&e&lCenter Location");
        centerLocations.setLore("&7Right-Click > Set", "&7Left-Click > Remove");
        l.add(centerLocations.build());

        ItemBuilder redwall = new ItemBuilder(Material.STICK);
        redwall.setGUITag("battlebox_redwall");
        redwall.setDisplayName("&c&lRed Wall");
        redwall.setLore("&7Right-Click > Pos 1", "&7Left-Click > Pos 2");
        l.add(redwall.build());

        ItemBuilder bluewall = new ItemBuilder(Material.STICK);
        bluewall.setGUITag("battlebox_bluewall");
        bluewall.setDisplayName("&9&lBlue Wall");
        bluewall.setLore("&7Right-Click > Pos 1", "&7Left-Click > Pos 2");
        l.add(bluewall.build());

        ItemBuilder redlobby = new ItemBuilder(Material.RED_DYE);
        redlobby.setGUITag("battlebox_redlobby");
        redlobby.setDisplayName("&c&lRed Lobby");
        redlobby.setLore("&7Click to set");
        l.add(redlobby.build());

        ItemBuilder bluelobby = new ItemBuilder(Material.BLUE_DYE);
        bluelobby.setGUITag("battlebox_bluelobby");
        bluelobby.setDisplayName("&9&lBlue Lobby");
        bluelobby.setLore("&7Click to set");
        l.add(bluelobby.build());

        ItemBuilder redcage = new ItemBuilder(Material.REDSTONE);
        redcage.setGUITag("battlebox_redcage");
        redcage.setDisplayName("&c&lRed Cage");
        redcage.setLore("&7Click to set");
        l.add(redcage.build());

        ItemBuilder blueCage = new ItemBuilder(Material.LAPIS_LAZULI);
        blueCage.setGUITag("battlebox_bluecage");
        blueCage.setDisplayName("&9&lBlue Cage");
        blueCage.setLore("&7Click to set");
        l.add(blueCage.build());

        ItemBuilder lobby = new ItemBuilder(Material.COMPASS);
        lobby.setGUITag("battlebox_lobby");
        lobby.setDisplayName("&e&lSet Lobby");
        lobby.setLore("&7Click to set");
        l.add(lobby.build());

        ItemBuilder harming = new ItemBuilder(Material.POTION);
        harming.setGUITag("battlebox_harmingpotion");
        harming.setDisplayName("&5&lAdd Harming Potion");
        harming.setLore("&7Click to add");
        l.add(harming.build());

        return l.toArray(new ItemStack[]{});
    }

}
