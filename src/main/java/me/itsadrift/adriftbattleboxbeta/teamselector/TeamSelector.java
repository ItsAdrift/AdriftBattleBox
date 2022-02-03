package me.itsadrift.adriftbattleboxbeta.teamselector;

import me.itsadrift.adriftbattleboxbeta.game.GameManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TeamSelector {

    private List<UUID> redTeam = new ArrayList<>();
    private List<UUID> blueTeam = new ArrayList<>();

    private GameManager gameManager;

    public TeamSelector(GameManager gameManager) {
        this.gameManager = gameManager;

        TeamSelectorGUI teamSelectorGUI = gameManager.getMain().getTeamSelectorGUI();
        for (Player player : Bukkit.getOnlinePlayers()) {
            teamSelectorGUI.openGUI(player, false, false);
        }

    }

    public void selectTeam(UUID uuid, boolean hunterTeam) {
        redTeam.remove(uuid);
        blueTeam.remove(uuid);
        if (hunterTeam) {
            redTeam.add(uuid);
        } else {
            blueTeam.add(uuid);
        }

        if (redTeam.size() + blueTeam.size() == Bukkit.getOnlinePlayers().size()) {
            // Everyone has selected a team.
            gameManager.createBattleBox();
        }

    }

    public void clearSelection(UUID uuid) {
        redTeam.remove(uuid);
        blueTeam.remove(uuid);
    }

    public List<UUID> getRedTeam() {
        return redTeam;
    }

    public List<UUID> getBlueTeam() {
        return blueTeam;
    }

}
