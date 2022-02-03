package me.itsadrift.adriftbattleboxbeta.teamselector;

import me.itsadrift.adriftbattleboxbeta.AdriftBattleBoxBETA;
import me.itsadrift.adriftbattleboxbeta.game.GameManager;
import me.itsadrift.adriftbattleboxbeta.utils.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class TeamSelectorGUI implements Listener {

    ItemBuilder hunterTeam = new ItemBuilder(Material.RED_STAINED_GLASS_PANE).setDisplayName("&c&lHunter").setLore("&7Click to join the &cHUNTER &7team");
    ItemBuilder runnerTeam = new ItemBuilder(Material.LIGHT_BLUE_STAINED_GLASS_PANE).setDisplayName("&b&lRunner").setLore("&7Click to join the &bRUNNER &7team");
    ItemBuilder decline = new ItemBuilder(Material.BARRIER).setDisplayName("&c&lDecline").setLore("&7Decline the invitation to join", "&7this &5EPIC &7manhunt game.");

    private AdriftBattleBoxBETA main;
    private GameManager gameManager;
    public TeamSelectorGUI(AdriftBattleBoxBETA main) {
        this.main = main;
        this.gameManager = main.getGameManager();
    }

    public void openGUI(Player player, boolean shiny, boolean shinyHunter) {
        Inventory inv = Bukkit.createInventory(null, 9, colour("&8Select Team"));

        ItemStack background = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE).setDisplayName("&7").build();
        for (int i = 0; i < inv.getSize(); i++) {
            inv.setItem(i, background);
        }

        if (shiny) {
            inv.setItem(3, shinyHunter ? hunterTeam.clone().setGlowing(true).build() : hunterTeam.build());
            inv.setItem(5, shinyHunter ? runnerTeam.build() : runnerTeam.clone().setGlowing(true).build());
            inv.setItem(8, decline.build());
        } else {
            inv.setItem(3, hunterTeam.build());
            inv.setItem(5, runnerTeam.build());
            inv.setItem(8, decline.build());
        }


        player.openInventory(inv);
    }

    @EventHandler
    public void inventoryClick(InventoryClickEvent e) {
        if (e.getClickedInventory() != null && e.getCurrentItem() != null) {
            if (e.getView().getTitle().equals(colour("&8Select Team"))) {
                e.setCancelled(true);

                String itemName = e.getCurrentItem().getItemMeta().getDisplayName();
                UUID uuid = e.getWhoClicked().getUniqueId();
                if (itemName.equals(colour("&c&lHunter"))) {
                    openGUI((Player) e.getWhoClicked(), true, true);
                    gameManager.getTeamSelector().selectTeam(uuid, true);
                } else if (itemName.equals(colour("&b&lRunner"))) {
                    openGUI((Player) e.getWhoClicked(), true, false);
                    gameManager.getTeamSelector().selectTeam(uuid, false);
                } else if (itemName.equals(colour("&c&lDecline"))) {
                    e.getWhoClicked().closeInventory();
                    gameManager.getTeamSelector().clearSelection(uuid);
                }
            }
        }
    }

    private static String colour(String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
