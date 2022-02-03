package me.itsadrift.adriftbattleboxbeta.game;

import com.sk89q.worldedit.EditSession;
import com.sk89q.worldedit.MaxChangedBlocksException;
import com.sk89q.worldedit.WorldEdit;
import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.extension.factory.PatternFactory;
import com.sk89q.worldedit.extension.input.ParserContext;
import com.sk89q.worldedit.function.pattern.Pattern;
import com.sk89q.worldedit.function.pattern.RandomPattern;
import com.sk89q.worldedit.math.BlockVector3;
import com.sk89q.worldedit.regions.CuboidRegion;
import com.sk89q.worldedit.world.World;
import me.itsadrift.adriftbattleboxbeta.AdriftBattleBoxBETA;
import me.itsadrift.adriftbattleboxbeta.powerups.Powerup;
import me.itsadrift.adriftbattleboxbeta.teamselector.TeamSelector;
import me.itsadrift.adriftbattleboxbeta.utils.HexUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.BlockState;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BattleBox {

    private List<BlockState> states = new ArrayList<>();
    private List<UUID> players = new ArrayList<>();

    private List<UUID> redTeam = new ArrayList<>();
    private List<UUID> blueTeam = new ArrayList<>();

    private List<UUID> playersPowerupsSelected = new ArrayList<>();

    private AdriftBattleBoxBETA main;
    private TeamSelector selector;
    private GameManager gameManager;
    public BattleBox(AdriftBattleBoxBETA main, TeamSelector selector, GameManager gameManager) {
        this.main = main;
        this.selector = selector;
        this.gameManager = gameManager;

        this.redTeam = selector.getRedTeam();
        this.blueTeam = selector.getBlueTeam();
        this.players = Stream.concat(redTeam.stream(), blueTeam.stream())
                .collect(Collectors.toList());

        for (UUID uuid : getPlayers()) {
            Bukkit.getPlayer(uuid).closeInventory();
            Bukkit.getPlayer(uuid).getInventory().clear();
        }

        toTeamLobby();

    }

    public void toTeamLobby() {
        for (UUID uuid : redTeam) {
            Bukkit.getPlayer(uuid).teleport(gameManager.getBattleBoxLocation("redlobby").getLocation());
        }
        for (UUID uuid : blueTeam) {
            Bukkit.getPlayer(uuid).teleport(gameManager.getBattleBoxLocation("bluelobby").getLocation());
        }


    }

    public void selectPowerup(UUID uuid, Powerup powerup) {
        powerup.applyPowerup(Bukkit.getPlayer(uuid));
        if (!playersPowerupsSelected.contains(uuid)) {
            playersPowerupsSelected.add(uuid);
        }

        if (playersPowerupsSelected.size() >= players.size()) {
            toCage();
        }

    }

    public void toCage() {
        for (UUID uuid : redTeam) {
            Bukkit.getPlayer(uuid).teleport(gameManager.getBattleBoxLocation("redcage").getLocation());
        }
        for (UUID uuid : blueTeam) {
            Bukkit.getPlayer(uuid).teleport(gameManager.getBattleBoxLocation("bluecage").getLocation());
        }

        new BukkitRunnable() {
            int i = 5;
            @Override
            public void run() {

                for (UUID uuid : players) {
                    Bukkit.getPlayer(uuid).sendTitle(HexUtils.colour("&a&lStarting In"), HexUtils.colour("&f&l" + i));
                }

                i--;

                if (i == 0) {
                    cancel();
                    start();
                }

            }
        }.runTaskTimer(main, 0, 20);
    }

    public void start() {
        World world = BukkitAdapter.adapt(Bukkit.getPlayer(players.get(0)).getWorld()); // Get the WorldEdit world from the spigot world by using BukkitAdapter
        CuboidRegion selection = new CuboidRegion(world, BlockVector3.at(0, 80, 0), BlockVector3.at(10, 80, 10)); // make a selection with two points
        EditSession editSession = WorldEdit.getInstance().newEditSession(world);

        Bukkit.getScheduler().runTaskLater(main, new Runnable() {
            @Override
            public void run() {
                stop(null);
            }
        }, 60 * 20);
    }

    public void stop(BattleBoxTeam winner) {

    }

    public List<UUID> getPlayers() {
        return players;
    }
}
