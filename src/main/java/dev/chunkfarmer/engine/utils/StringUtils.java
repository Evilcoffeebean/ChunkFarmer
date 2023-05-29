package dev.chunkfarmer.engine.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.logging.Level;
import java.util.stream.Stream;

public final class StringUtils {

    //prevent instantiation
    private StringUtils() {}

    public static String color(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String spacer(char symbol, int length) {
        final StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append(symbol).append(length - 1 >= i ? "" : " ");
        }
        return stringBuilder.toString();
    }

    public static String json(String input) {
        return json(input, true);
    }

    public static String json(String input, boolean color) {
        return "{\"text\":\"" + (color ? color(input) : input) + "\"}";
    }

    public static String join(String... text) {
        final StringBuilder response = new StringBuilder();
        Stream.of(text).forEach(line -> response.append(line).append("\n"));
        return response.toString();
    }

    public static String join(int index, String... text) {
        final StringBuilder builder = new StringBuilder();
        for (int i = index; i < text.length; i++)
            builder.append(text[i]).append(i >= text.length - 1 ? "" : " ");
        return builder.toString();
    }

    public static void playerOnly() {
        log("This command is only executable by players.");
    }

    public static void log(String msg) {
        Bukkit.getLogger().log(Level.INFO, msg);
    }

    public static void help(Player player, String message) {
        player.sendMessage(color(message));
    }
}
