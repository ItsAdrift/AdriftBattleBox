package me.itsadrift.adriftbattleboxbeta.powerups;

import org.bukkit.entity.Player;

public interface Powerup {

    void getId();

    void applyPowerup(Player player);

}
