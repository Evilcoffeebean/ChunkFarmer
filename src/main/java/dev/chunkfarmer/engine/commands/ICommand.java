package dev.chunkfarmer.engine.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

public interface ICommand {

    String getCommand();
    String getUsage();
    String getDescription();
    int getLength();
    Permission getPermission();
    boolean isPlayer();
    void execute(CommandSender sender, String[] args);
}
