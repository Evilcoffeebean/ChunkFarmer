package dev.chunkfarmer.engine.events;

import dev.chunkfarmer.engine.BlockData;
import dev.chunkfarmer.engine.Core;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BlockBreakListener implements Listener {

    @EventHandler (priority = EventPriority.LOW)
    public void onBlockBreak(final BlockBreakEvent e) {
        final BlockData data = new BlockData(e.getBlock().getLocation(), e.getBlock().getType());
        Core.getCore().getDataList().add(data);
    }
}
