package dev.ichigo.ffa.board;

import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.google.common.collect.Maps;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.enums.ProfileStatus;
import dev.ichigo.ffa.manager.PlayerManager;
import dev.jiraiya.paper.JiraPaper;

public class ScoreboardRunnable extends BukkitRunnable {

    private static Map<UUID, ScoreboardHelper> boardMap = Maps.newConcurrentMap();

    @Override
    public void run() {
        for (Map.Entry<UUID, ScoreboardHelper> entry : boardMap.entrySet()){
            UUID uuid = entry.getKey();
            if (Bukkit.getPlayer(uuid) == null)boardMap.remove(uuid);
        }

        int players = Bukkit.getOnlinePlayers().length;
        int atspawn = FFA.getInstance().atSpawn.size();
        int inpot = FFA.getInstance().inPotion.size();
        int inuhc = FFA.getInstance().inUhc.size();

        for (Player player : Bukkit.getOnlinePlayers()){	
            final UUID uuid = player.getUniqueId();
            ScoreboardHelper board = boardMap.get(uuid);

            if (board == null){
                board = new ScoreboardHelper(player, ScoreboardUtils.SCOREBOARD_TITLE);
                boardMap.put(uuid, board);
            }
           
            PlayerManager pm = PlayerManager.getPlayerManagers().get(player.getUniqueId());
            ProfileStatus status = pm.getStatus();
            board.clear();
            if (status == ProfileStatus.SPAWN) {
                board.add(ScoreboardUtils.SCOREBOARD_LINE);
                switch(JiraPaper.getInstance().getColor()) {
                	case RED: {
                        board.add(ChatColor.RED + "Online: " + ChatColor.WHITE + players);
                        board.add("");
                        board.add(ChatColor.WHITE + "Spawn: " + ChatColor.DARK_RED + atspawn);
                        board.add(ChatColor.WHITE + "Potions: " + ChatColor.DARK_RED + inpot);
                        board.add(ChatColor.WHITE + "Build-UHC: " + ChatColor.DARK_RED + inuhc);
                        break;
                	}
                	case AQUA: {
                        board.add(ChatColor.AQUA + "Online: " + ChatColor.WHITE + players);
                        board.add("");
                        board.add(ChatColor.WHITE + "Spawn: " + ChatColor.DARK_AQUA + atspawn);
                        board.add(ChatColor.WHITE + "Potions: " + ChatColor.DARK_AQUA + inpot);
                        board.add(ChatColor.WHITE + "Build-UHC: " + ChatColor.DARK_AQUA + inuhc);
                        break;
                	}
                	case BLUE: {
                        board.add(ChatColor.DARK_BLUE + "Online: " + ChatColor.WHITE + players);
                        board.add("");
                        board.add(ChatColor.WHITE + "Spawn: " + ChatColor.BLUE + atspawn);
                        board.add(ChatColor.WHITE + "Potions: " + ChatColor.BLUE + inpot);
                        board.add(ChatColor.WHITE + "Build-UHC: " + ChatColor.BLUE + inuhc);
                        break;
                	}
                	case GOLD: {
                        board.add(ChatColor.GOLD + "Online: " + ChatColor.WHITE + players);
                        board.add("");
                        board.add(ChatColor.WHITE + "Spawn: " + ChatColor.YELLOW + atspawn);
                        board.add(ChatColor.WHITE + "Potions: " + ChatColor.YELLOW + inpot);
                        board.add(ChatColor.WHITE + "Build-UHC: " + ChatColor.YELLOW + inuhc);
                        break;
                	}
                	default: {
                        board.add(ChatColor.RED + "Online: " + ChatColor.WHITE + players);
                        board.add("");
                        board.add(ChatColor.WHITE + "Spawn: " + ChatColor.DARK_RED + atspawn);
                        board.add(ChatColor.WHITE + "Potions: " + ChatColor.DARK_RED + inpot);
                        board.add(ChatColor.WHITE + "Build-UHC: " + ChatColor.DARK_RED + inuhc);
                        break;
                	}
                }
            	board.add("");
                board.add(ScoreboardUtils.SCOREBOARD_LINE);
            }
            if (status == ProfileStatus.POTION) {
                board.add(ScoreboardUtils.SCOREBOARD_LINE);
                switch(JiraPaper.getInstance().getColor()) {
                	case RED: {
                        board.add(ChatColor.RED + "Playing: " + ChatColor.WHITE + inpot);
                        board.add("");
                        board.add(ChatColor.WHITE + "Gametype: " + ChatColor.DARK_RED + "Potions");
                        board.add("");
                        board.add(ChatColor.WHITE + "Kill: " + ChatColor.DARK_RED + pm.getKill());
                        board.add(ChatColor.WHITE + "Death: " + ChatColor.DARK_RED + pm.getDeath());
                        board.add(ChatColor.WHITE + "Killstreak: " + ChatColor.DARK_RED + pm.getKillstreak());
                        break;
                	}
                	case AQUA: {
                        board.add(ChatColor.DARK_AQUA + "Playing: " + ChatColor.WHITE + inpot);
                        board.add("");
                        board.add(ChatColor.WHITE + "Gametype: " + ChatColor.AQUA + "Potions");
                        board.add("");
                        board.add(ChatColor.WHITE + "Kill: " + ChatColor.AQUA + pm.getKill());
                        board.add(ChatColor.WHITE + "Death: " + ChatColor.AQUA + pm.getDeath());
                        board.add(ChatColor.WHITE + "Killstreak: " + ChatColor.AQUA + pm.getKillstreak());
                        break;
                	}
                	case BLUE: {
                        board.add(ChatColor.RED + "Playing: " + ChatColor.WHITE + inpot);
                        board.add("");
                        board.add(ChatColor.WHITE + "Gametype: " + ChatColor.DARK_RED + "Potions");
                        board.add("");
                        board.add(ChatColor.WHITE + "Kill: " + ChatColor.DARK_RED + pm.getKill());
                        board.add(ChatColor.WHITE + "Death: " + ChatColor.DARK_RED + pm.getDeath());
                        board.add(ChatColor.WHITE + "Killstreak: " + ChatColor.DARK_RED + pm.getKillstreak());
                        break;
                	}
                	default: {
                        board.add(ChatColor.RED + "Playing: " + ChatColor.WHITE + inpot);
                        board.add("");
                        board.add(ChatColor.WHITE + "Gametype: " + ChatColor.DARK_RED + "Potions");
                        board.add("");
                        board.add(ChatColor.WHITE + "Kill: " + ChatColor.DARK_RED + pm.getKill());
                        board.add(ChatColor.WHITE + "Death: " + ChatColor.DARK_RED + pm.getDeath());
                        board.add(ChatColor.WHITE + "Killstreak: " + ChatColor.DARK_RED + pm.getKillstreak());
                        break;
                	}
                }
            	board.add("");
                board.add(ScoreboardUtils.SCOREBOARD_LINE);
            }
            if (status == ProfileStatus.UHC) {
                board.add(ScoreboardUtils.SCOREBOARD_LINE);
                board.add(ChatColor.RED + "Playing: " + ChatColor.WHITE + inuhc);
                board.add("");
                board.add(ChatColor.WHITE + "Gametype: " + ChatColor.DARK_RED + "Build-UHC");
                board.add("");
                board.add(ChatColor.WHITE + "Kill: " + ChatColor.DARK_RED + pm.getKill());
                board.add(ChatColor.WHITE + "Death: " + ChatColor.DARK_RED + pm.getDeath());
                board.add(ChatColor.WHITE + "Killstreak: " + ChatColor.DARK_RED + pm.getKillstreak());
                board.add(ChatColor.WHITE + "Counter Block: " + ChatColor.DARK_RED + pm.getBlock());
            	board.add("");
                board.add(ScoreboardUtils.SCOREBOARD_LINE);
            }
            board.update();
        }
    }
}
