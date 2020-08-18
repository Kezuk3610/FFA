package dev.ichigo.ffa.util;

import java.io.*;
import org.bukkit.configuration.file.*;

public class FileUtils
{
    public static void save(final File file, final FileConfiguration configuration) {
        try {
            configuration.save(file);
            configuration.load(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
