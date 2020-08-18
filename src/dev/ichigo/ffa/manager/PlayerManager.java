package dev.ichigo.ffa.manager;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.config.Config;
import dev.ichigo.ffa.enums.ProfileStatus;
import dev.ichigo.ffa.interfaces.iKit;
import dev.ichigo.ffa.util.LocationHelper;

public class PlayerManager {
		
    static Map<UUID, PlayerManager> playerManagers;
    private UUID uuid;
    private Player player;
    private ProfileStatus status;
    private int enderPearl;
    private int kill;
    private int death;
    private int killstreak;
    private int coins;
    private float block;
	private float exp;
	private String league;
    
	public PlayerManager(final UUID uuid) {
		this.uuid = uuid;
		this.status = ProfileStatus.SPAWN;
		this.enderPearl = 0;
		this.kill = 0;
		this.death = 0;
		this.killstreak = 0;
        this.coins = 0;
        this.block = 0.0f;
        this.exp = 0.0f;
        this.league = "Silver I";
		PlayerManager.playerManagers.put(uuid, this);
	}   
	
    public void reset(final Player player, final GameMode gameMode) {
        player.getInventory().clear();
        player.getInventory().setHelmet(new ItemStack(Material.AIR));
        player.getInventory().setChestplate(new ItemStack(Material.AIR));
        player.getInventory().setLeggings(new ItemStack(Material.AIR));
        player.getInventory().setBoots(new ItemStack(Material.AIR));
        player.updateInventory();
        for (final PotionEffect effect : player.getActivePotionEffects()) {
            player.removePotionEffect(effect.getType());
        }
        player.setMaximumNoDamageTicks(20);
        player.setNoDamageTicks(20);
        player.setHealthScale(20.0);
        player.setMaxHealth(20.0);
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.setLevel(0);
        player.setFireTicks(0);
        player.setSaturation(10.0f);
        player.setGameMode(gameMode);
        player.setAllowFlight(false);
        player.setFlying(false);
        player.setLevel(0);
    }
	
    public void sendKit(final iKit kit) {
        final Player player = Bukkit.getPlayer(this.uuid);
        player.getInventory().clear();
        player.getInventory().setContents(kit.content());
        player.getInventory().setArmorContents(kit.armor());
        player.updateInventory();
    }
	
    public void teleport(final Player player, final LocationHelper locationHelper) {
        if (locationHelper == null || locationHelper.getLocation() == null) {
            player.sendMessage(ChatColor.RED + "You can't be teleported because this location is not set!");
        }
        else {
            player.teleport(locationHelper.getLocation());
        }
    }
	
    public static Map<UUID, PlayerManager> getPlayerManagers() {
		return playerManagers;
	}
    
    public String getLeague() {
		return league;
	}
    
    public void setLeague(String league) {
		this.league = league;
	}
    
    public float getExp() {
		return exp;
	}
    
    public void setExp(float exp) {
		this.exp = exp;
	}
    
    public float getBlock() {
		return block;
	}
    
    public void setBlock(float block) {
		this.block = block;
	}
    
    public int getCoins() {
		return coins;
	}
    
    public void setCoins(int coins) {
		this.coins = coins;
	}
    
    public int getKillstreak() {
		return killstreak;
	}
    
    public void setKillstreak(int killstreak) {
		this.killstreak = killstreak;
	}
    
    public int getKill() {
		return kill;
	}
    
    public void setKill(int kill) {
		this.kill = kill;
	}
    
    public int getDeath() {
		return death;
	}
    
    public void setDeath(int death) {
		this.death = death;
	}
    
    public int getEnderPearl() {
		return this.enderPearl;
	}
    
    public void setEnderPearl(int enderPearl) {
		this.enderPearl = enderPearl;
	}
    
    public Player getPlayer() {
		return player;
	}
    
    public void setPlayer(Player player) {
		this.player = player;
	}
    
    public ProfileStatus getStatus() {
		return status;
	}
    
    public void setStatus(ProfileStatus status) {
		this.status = status;
	}
    
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	   public void createPlayerData(Player player) {
		      PlayerManager data = new PlayerManager(player.getUniqueId());
		      PlayerManager.playerManagers.put(data.getUuid(), data);
		      this.loadData(data);
		   }

		   private void loadData(PlayerManager playerData) {
		      Config config = new Config("/players/" + playerData.getUuid().toString(), FFA.getInstance());
		      FileConfiguration fileConfig = config.getConfig();
		      ConfigurationSection playerDataSelection = fileConfig.getConfigurationSection("playerdata");
		      if (playerDataSelection != null) {
		         playerData.setCoins(playerDataSelection.getInt("coins"));
		         playerData.setKill(playerDataSelection.getInt("kills"));
		         playerData.setDeath(playerDataSelection.getInt("deaths"));
		         playerData.setExp(playerDataSelection.getFloat("exp"));
		         playerData.setLeague(playerDataSelection.getString("league"));
		      }

		      playerData.setStatus(ProfileStatus.SPAWN);
		   }

		   public void removePlayerData(UUID uuid) {
		      FFA.getInstance().getServer().getScheduler().runTaskAsynchronously(FFA.getInstance(), () -> {
		         this.saveData((PlayerManager)PlayerManager.getPlayerManagers().get(uuid));
		         PlayerManager.playerManagers.remove(uuid);
		      });
		   }

		   public void saveData(PlayerManager playerData) {
		      if (playerData != null) {
		         Config config = new Config("/players/" + playerData.getUuid().toString(), FFA.getInstance());
		         config.getConfig().set("playerdata.coins", playerData.getCoins());
		         config.getConfig().set("playerdata.kills", playerData.getKill());
		         config.getConfig().set("playerdata.deaths", playerData.getDeath());
		         config.getConfig().set("playerdata.exp", playerData.getExp());
		         config.getConfig().set("playerdata.league", playerData.getLeague());
		         config.save();
		      }

		   }
	
    static {
        PlayerManager.playerManagers = new HashMap<UUID, PlayerManager>();
    }

}
