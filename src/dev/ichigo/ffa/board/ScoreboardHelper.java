package dev.ichigo.ffa.board;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

public class ScoreboardHelper {

    private Player player;
    private List<ScoreboardText> list = new ArrayList<>();
    private Scoreboard scoreboard;
    private Objective objective;
    private String title;
    private int lastSentCount = -1;

    public Objective getObjective() {
        return objective;
    }

    ScoreboardHelper(Player player, String title) {
        this.player = player;
        this.title = ChatColor.translateAlternateColorCodes('&', title);
        scoreboard = Bukkit.getServer().getScoreboardManager().getNewScoreboard();
        (this.objective = scoreboard.registerNewObjective(player.getName() , "dummy")).setDisplaySlot(DisplaySlot.SIDEBAR);
        this.objective.setDisplayName(this.title);
    }

    void add(String input) {
        this.list.add(new ScoreboardText(input));
    }

    void clear() {
        this.list.clear();
    }

    private void remove(int index) {
        final String name = this.getChatColor(index);
        this.scoreboard.resetScores(name);
        final Team team = this.getOrCreateTeam(String.valueOf(String.valueOf(ChatColor.stripColor(StringUtils.left(title, 14)))) + index, index);
        team.unregister();
    }

    void update() {
        for (int sentCount = 0; sentCount < this.list.size(); ++sentCount) {
            final Team team = this.getOrCreateTeam(ChatColor.stripColor(StringUtils.left(title, 14)) + sentCount, sentCount);
            final ScoreboardText str = this.list.get(this.list.size() - sentCount - 1);
            team.setPrefix(str.getLeft());
            team.setSuffix(str.getRight());
            this.objective.getScore(this.getChatColor(sentCount)).setScore(sentCount + 1);
        }
        if (this.lastSentCount != -1) {
            for (int sentCount = this.list.size(), var4 = 0; var4 < this.lastSentCount - sentCount; ++var4) {
                this.remove(sentCount + var4);
            }
        }
        this.lastSentCount = this.list.size();
        this.player.setScoreboard(this.scoreboard);
    }

    private Team getOrCreateTeam(String team, int i) {
        Team value = this.scoreboard.getTeam(team);
        if (value == null) {
            (value = this.scoreboard.registerNewTeam(team)).addEntry(this.getChatColor(i));
        }
        return value;
    }

    private String getChatColor(int index) {
        return ChatColor.values()[index].toString() + ChatColor.RESET;
    }
}
