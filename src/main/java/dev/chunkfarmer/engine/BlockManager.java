package dev.chunkfarmer.engine;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;

import java.util.ArrayList;
import java.util.List;

public final class BlockManager {

    //prevent instantiation
    private BlockManager() {}

    public static boolean containsBlock(Chunk chunk, Material block) {
        boolean foundOne = false;
        for(int y = 0; y <= 256; y++) {
            for(int x = 0; x <= 15; x++) {
                for(int z = 0; z <= 15; z++) {
                    if(chunk.getBlock(x, y, z).getType() == block) {
                        if(foundOne)
                            return true;
                        else
                            foundOne = true;
                    }
                }
            }
        }
        return false;
    }

    public static List<Item> getChunkDrops(Chunk chunk) {
        List<Item> response = new ArrayList<>();
        for (Entity entity : chunk.getEntities()) {
            if (entity instanceof Item)
                response.add((Item) entity);
        }
        return response;
    }
}
