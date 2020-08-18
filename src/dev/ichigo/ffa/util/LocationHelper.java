package dev.ichigo.ffa.util;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import dev.ichigo.ffa.FFA;

public class LocationHelper
{
    static List<LocationHelper> all;
    private String name;
    private Location location;
	public ArrayList<Location> rushpoints = new ArrayList<>();
    
    public LocationHelper(final String name, final Location location) {
        this.name = name;
        this.location = location;
        LocationHelper.all.add(this);
    }
    
    public LocationHelper(final String name) {
        this(name, null);
    }
    
    public void save() {
        if (this.location == null) {
            return;
        }
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(this.location.getWorld().getName());
        stringBuilder.append(":");
        stringBuilder.append(this.location.getX());
        stringBuilder.append(":");
        stringBuilder.append(this.location.getY());
        stringBuilder.append(":");
        stringBuilder.append(this.location.getZ());
        stringBuilder.append(":");
        stringBuilder.append(this.location.getYaw());
        stringBuilder.append(":");
        stringBuilder.append(this.location.getPitch());
        if (stringBuilder.toString().equals(FFA.getInstance().locationConfig.get("locations." + this.name))) {
            return;
        }
        FFA.getInstance().locationConfig.set("locations." + this.name, (Object)stringBuilder.toString());
    }
    
    public boolean load() {
        if (FFA.getInstance().locationConfig.get("locations." + this.name) == null) {
            return false;
        }
        final String[] part = FFA.getInstance().locationConfig.getString("locations." + this.name).split(":");
        this.location = new Location(Bukkit.getWorld(part[0]), Double.parseDouble(part[1]), Double.parseDouble(part[2]), Double.parseDouble(part[3]), Float.parseFloat(part[4]), Float.parseFloat(part[5]));
        return true;
    }
    
    public static LocationHelper getLocationHelper(final String name) {
        return LocationHelper.all.stream().filter(locationHelper -> locationHelper.getName().equals(name)).findFirst().orElse(null);
    }
    
    public String getName() {
        return this.name;
    }
    
    public Location getLocation() {
        return this.location;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public void setLocation(final Location location) {
        this.location = location;
    }
    
    public static List<LocationHelper> getAll() {
        return LocationHelper.all;
    }
    
    static {
        LocationHelper.all = new ArrayList<LocationHelper>();
    }
}
