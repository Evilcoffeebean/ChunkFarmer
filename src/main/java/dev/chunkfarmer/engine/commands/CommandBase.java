package dev.chunkfarmer.engine.commands;

import dev.chunkfarmer.engine.utils.StringUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandBase implements CommandExecutor {

    private static final Map<String, ICommand> commands = new ConcurrentHashMap<>();

    public CommandBase() {
        //todo: manually cache commands
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("chunkfarmer")) {
            if (!(sender instanceof Player)) {
                if (args.length == 0) {
                    final StringBuilder response = new StringBuilder();
                    response.append("[ChunkFarmer] Command Center:\n");
                    for (ICommand command : commands.values()) {
                        response.append(command.getUsage()).append(" - ").append(command.getDescription()).append("\n");
                    }
                    StringUtils.log(response.toString());
                    return true;
                }

                final ICommand command = commands.get(args[0]);
                if (command == null || !commands.containsKey(args[0])) {
                    StringUtils.log(args[0] + " isn't a valid ChunkFarmer command.");
                    return true;
                }

                if (command.isPlayer()) {
                    StringUtils.playerOnly();
                    return true;
                }

                if (args.length != command.getLength()) {
                    StringUtils.log(command.getUsage());
                    return true;
                }

                command.execute(sender, args);
                return true;
            }

            final Player player = (Player) sender;
            if (args.length == 0) {
                final StringBuilder response = new StringBuilder();
                response.append("&b[&9Chunk&fFarmer&b] &9Command Center:\n");
                for (ICommand command : commands.values()) {
                    if (player.hasPermission(command.getPermission()))
                        response.append("&b").append(command.getUsage()).append(" &9- ").append(command.getDescription());
                }
                player.sendMessage(StringUtils.color(response.toString()));
                return true;
            }

            final ICommand command = commands.get(args[0]);
            if (command == null || !commands.containsKey(args[0])) {
                StringUtils.help(player, args[0] + " isn't a valid ChunkFarmer command.");
                return true;
            }

            if (!player.hasPermission(command.getPermission())) {
                StringUtils.help(player, "You don't have permission to execute this command.");
                return true;
            }

            if (args.length != command.getLength()) {
                StringUtils.help(player, command.getUsage());
                return true;
            }

            command.execute(player, args);
            return true;
        }
        return false;
    }
}
