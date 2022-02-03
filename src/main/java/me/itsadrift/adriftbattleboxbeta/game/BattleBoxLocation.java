package me.itsadrift.adriftbattleboxbeta.game;

import me.itsadrift.adriftbattleboxbeta.AdriftBattleBoxBETA;
import me.itsadrift.adriftbattleboxbeta.utils.LocUtils;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class BattleBoxLocation {

    private AdriftBattleBoxBETA main;
    private String id;
    private List<Location> locations = new ArrayList<>();
    public BattleBoxLocation(AdriftBattleBoxBETA main, String id) {
        this.main = main;
        this.id = id;
        if (main.getConfig().contains(id)) {
            if (id.equalsIgnoreCase("centerblocks") || id.equalsIgnoreCase("harmingpotion")) {
                for (String s : main.getConfig().getStringList("locations." + id)) {
                    locations.add(LocUtils.decode(s));
                }
            } else {
                locations.add(LocUtils.decode(main.getConfig().getString("locations." + id)));
            }
        }
    }

    public Location getLocation() {
        return locations.get(0);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public String getId() {
        return id;
    }

}
