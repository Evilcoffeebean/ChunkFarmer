package dev.chunkfarmer.engine;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public final class BlockStorage {

    private final File file;
    private final Gson gson;

    public BlockStorage(JavaPlugin plugin, String name) {
        this.file = new File(plugin.getDataFolder(), name);
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void saveBlockData(List<BlockData> dataList) {
        JsonArray jsonArray = new JsonArray();
        for (BlockData data : dataList) {

            JsonObject obj = new JsonObject();
            obj.addProperty("x", data.getLocation().getX());
            obj.addProperty("y", data.getLocation().getY());
            obj.addProperty("z", data.getLocation().getZ());
            obj.addProperty("material", data.getMaterialName());
            jsonArray.add(obj);
        }

        try (FileWriter writer = new FileWriter(file)) {
            gson.toJson(jsonArray, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<BlockData> loadBlockData() {
        List<BlockData> blockDataList = new ArrayList<>();

        if (!file.exists()) {
            return blockDataList;
        }

        try {
            BlockData[] blockDataArray = gson.fromJson(new FileReader(file), BlockData[].class);
            for (BlockData blockData : blockDataArray) {
                Location location = new Location(blockData.getLocation().getWorld(), blockData.getX(), blockData.getY(), blockData.getZ());
                Material material = Material.valueOf(blockData.getMaterialName());
                blockDataList.add(new BlockData(location, material));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blockDataList;
    }
}
