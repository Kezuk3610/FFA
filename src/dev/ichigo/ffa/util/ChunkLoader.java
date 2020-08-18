package dev.ichigo.ffa.util;

import org.bukkit.Chunk;
import org.bukkit.Location;

public class ChunkLoader {
	
    public static void loadChunks(Location... locations) {
        for (Location location : locations) {
            Chunk chunk = location.getWorld().getChunkAt(location);
            location.getWorld().loadChunk(chunk);
        }
    }


    public static void unloadChunks(Location... locations) {
        for (Location location : locations) {
            Chunk chunk = location.getWorld().getChunkAt(location);
            location.getWorld().unloadChunk(chunk);
        }
    }

}
