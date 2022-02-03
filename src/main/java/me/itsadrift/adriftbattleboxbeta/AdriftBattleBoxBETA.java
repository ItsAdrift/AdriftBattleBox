package me.itsadrift.adriftbattleboxbeta;

import me.itsadrift.adriftbattleboxbeta.game.GameManager;
import me.itsadrift.adriftbattleboxbeta.teamselector.TeamSelectorGUI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class AdriftBattleBoxBETA extends JavaPlugin {

    private static AdriftBattleBoxBETA instance;

    private GameManager gameManager;
    private TeamSelectorGUI teamSelectorGUI;

    @Override
    public void onEnable() {
        // Plugin startup logic
        instance = this;

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();

        gameManager = new GameManager(this);
        teamSelectorGUI = new TeamSelectorGUI(this);

        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BattleBoxListener(), this);
        pm.registerEvents(new BattleBoxSetupListener(this), this);
        pm.registerEvents(teamSelectorGUI, this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic


        instance = null;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public TeamSelectorGUI getTeamSelectorGUI() {
        return teamSelectorGUI;
    }

    public static AdriftBattleBoxBETA getInstance() {
        return instance;
    }
}
