package dev.chunkfarmer.engine;

import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public final class BlockData {

    private final double x;
    private final double y;
    private final double z;
    private final World world;
    private final Chunk chunk;
    private final String material;

    public BlockData(Location location, Material material) {
        this.x = location.getX();
        this.y = location.getY();
        this.z = location.getZ();
        this.world = location.getWorld();
        this.chunk = location.getChunk();
        this.material = material.name();
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public String getMaterialName() {
        return material;
    }

    public Location getLocation() {
        return new Location(world, x, y, z);
    }
}
