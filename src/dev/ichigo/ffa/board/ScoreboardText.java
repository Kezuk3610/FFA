package dev.ichigo.ffa.board;

import org.apache.commons.lang.StringUtils;
import org.bukkit.ChatColor;

class ScoreboardText {

    private String left;
    private String right;

    ScoreboardText(String input){
        input = ChatColor.translateAlternateColorCodes('&', input);
        if (input.length() <= 16) {
            left = input;
            right = "";
        } else {
            left = input.substring(0, 16);
            right = StringUtils.left(ChatColor.getLastColors(left) + input.substring(16) , 16);
        }
    }

    String getLeft() {
        return this.left;
    }

    String getRight() {
        return this.right;
    }
}
