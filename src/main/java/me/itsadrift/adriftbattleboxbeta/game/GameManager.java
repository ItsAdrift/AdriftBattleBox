package me.itsadrift.adriftbattleboxbeta.game;

import me.itsadrift.adriftbattleboxbeta.AdriftBattleBoxBETA;
import me.itsadrift.adriftbattleboxbeta.teamselector.TeamSelector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class GameManager {

    private List<BattleBoxLocation> locations = new ArrayList<>();

    private List<BattleBox> games = new ArrayList<>();
    private TeamSelector teamSelector;

    private AdriftBattleBoxBETA main;
    public GameManager(AdriftBattleBoxBETA main) {
        this.main = main;

        loadLocations();
    }

    public void createBattleBox() {

    }
    public void createNewTeamSelector() {
        teamSelector = new TeamSelector(this);
    }

    public TeamSelector getTeamSelector() {
        return teamSelector;
    }

    public BattleBox getManhunt(UUID uuid) {
        for (BattleBox battleBox : games) {
            if (battleBox.getPlayers().contains(uuid)) {
                return battleBox;
            }
        }

        return null;
    }

    public void loadLocations() {
        locations.clear();

        locations.add(new BattleBoxLocation(main, "centerblocks"));
        locations.add(new BattleBoxLocation(main, "redwall"));
        locations.add(new BattleBoxLocation(main, "bluewall"));
        locations.add(new BattleBoxLocation(main, "redlobby"));
        locations.add(new BattleBoxLocation(main, "bluelobby"));
        locations.add(new BattleBoxLocation(main, "redcage"));
        locations.add(new BattleBoxLocation(main, "bluecage"));
        locations.add(new BattleBoxLocation(main, "lobby"));
        locations.add(new BattleBoxLocation(main, "harmingpotion"));
    }

    public BattleBoxLocation getBattleBoxLocation(String id) {
        for (BattleBoxLocation loc : locations) {
            if (loc.getId().equalsIgnoreCase(id)) {
                return loc;
            }
        }

        return null;
    }

    public AdriftBattleBoxBETA getMain() {
        return main;
    }
}
