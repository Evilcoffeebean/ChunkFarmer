package dev.chunkfarmer.engine;

import dev.chunkfarmer.engine.commands.CommandBase;
import lombok.Getter;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Core extends JavaPlugin {

    private final List<BlockData> dataList = new ArrayList<>();
    private static Core core;
    private BlockStorage blockStorage;
    private Economy economy;

    public static Core getCore() {
        return core;
    }

    @Override
    public void onEnable() {
        core = this;
        blockStorage = new BlockStorage(this, "blockdata.json");
        getCommand("chunkfarmer").setExecutor(new CommandBase());

        if (!setupEconomy()) {
            getServer().getLogger().severe(String.format("[%s] - Disabled due to no Vault dependency found!", getDescription().getName()));
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        blockStorage.loadBlockData();
    }

    @Override
    public void onDisable() {
        blockStorage.saveBlockData(dataList);
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null)
            return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null)
            return false;
        economy = rsp.getProvider();
        return economy != null;
    }
}
