package dev.ichigo.ffa;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import dev.ichigo.ffa.board.ScoreboardRunnable;
import dev.ichigo.ffa.command.FFACommand;
import dev.ichigo.ffa.command.MoneyCommand;
import dev.ichigo.ffa.command.ShopCommand;
import dev.ichigo.ffa.gui.InformationsGui;
import dev.ichigo.ffa.gui.KitGui;
import dev.ichigo.ffa.gui.LeagueGui;
import dev.ichigo.ffa.gui.ShopGui;
import dev.ichigo.ffa.interfaces.iGui;
import dev.ichigo.ffa.interfaces.iKit;
import dev.ichigo.ffa.inventory.PotionInventory;
import dev.ichigo.ffa.inventory.SpawnInventory;
import dev.ichigo.ffa.inventory.UHCInventory;
import dev.ichigo.ffa.listener.BlockListeners;
import dev.ichigo.ffa.listener.ChatListener;
import dev.ichigo.ffa.listener.EntityListener;
import dev.ichigo.ffa.listener.InventoryListener;
import dev.ichigo.ffa.listener.PlayerListener;
import dev.ichigo.ffa.listener.ServerListener;
import dev.ichigo.ffa.manager.PlayerManager;
import dev.ichigo.ffa.util.LocationHelper;
import net.minecraft.server.v1_7_R4.MinecraftServer;

public class FFA extends JavaPlugin {
	
	public ArrayList<UUID> atSpawn = new ArrayList<>();
	public ArrayList<UUID> inPotion = new ArrayList<>();
	public ArrayList<UUID> inUhc = new ArrayList<>();
	
	public static FFA instance;
	
    public File locationFile;
    public YamlConfiguration locationConfig;
    
    public DateFormat mediumDateFormatEN;
    
    public LocationHelper spawn;
    public LocationHelper potion;
    public LocationHelper uhc;
    
    public iGui chooseKit;
    public iGui shop;
    public iGui info;
    public iGui league;
    
    public iKit spawnInv;
    public iKit potionInv;
    public iKit uhcInv;
    
    private PlayerManager playerManager;
    
    public FFA() {
    	this.spawn = new LocationHelper("spawn");
    	this.potion = new LocationHelper("potion");
    	this.uhc = new LocationHelper("uhc");
    	
    	this.chooseKit = new KitGui();
    	this.shop = new ShopGui();
    	this.info = new InformationsGui();
    	this.league = new LeagueGui();
    	
    	this.spawnInv = new SpawnInventory();
    	this.potionInv = new PotionInventory();
    	this.uhcInv = new UHCInventory();
    	
        this.mediumDateFormatEN = DateFormat.getDateTimeInstance(2, 2, new Locale("EN", "en"));
    }
	
	public void onEnable() {
    	new ScoreboardRunnable().runTaskTimer(this, 0, 1);
        FFA.instance = this;
        this.registerRessource();
        this.registerListeners();
        this.registerCommands();
        this.registerFile();
        this.registerLocation();
	}

	private void registerFile() {
        this.locationFile = new File(this.getDataFolder() + "/locations.yml");
        this.locationConfig = YamlConfiguration.loadConfiguration(this.locationFile);	
	}

	private void registerListeners() {
		Arrays.asList(new PlayerListener(),new ServerListener(),new ChatListener(), new EntityListener(),new BlockListeners(), new InventoryListener()).forEach(listener -> this.getServer().getPluginManager().registerEvents(listener, (Plugin)this));	
	}

	public void onDisable() {
        LocationHelper.getAll().forEach(locationHelper -> locationHelper.save());
        try {
            this.locationConfig.save(this.locationFile);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
	}
	
    private void registerRessource() {
        this.saveResource("locations.yml", false);
	}

	private void registerLocation() {
        for (final LocationHelper locationHelper : LocationHelper.getAll()) {
            final String message = locationHelper.load() ? ("The location" + locationHelper.getName() + " is successfully registered!") : ("The location " + locationHelper.getName() + " is not registered!");
            this.getServer().getConsoleSender().sendMessage(message);
        }
    }
	
    private void registerCommands() {
        Arrays.asList(new FFACommand(), new ShopCommand(), new MoneyCommand()).forEach(command -> this.registerCommand(command, this.getName()));
    }
    
    public void registerCommand(final Command cmd, final String fallbackPrefix) {
        MinecraftServer.getServer().server.getCommandMap().register(cmd.getName(), fallbackPrefix, cmd);
    }
    
    @SuppressWarnings("unused")
    private void registerCommand(final Command cmd) {
        this.registerCommand(cmd, this.getName());
    }
    
    public static FFA getInstance() {
		return instance;
	}
    
    public PlayerManager getPlayerManager() {
		return this.playerManager;
	}

}