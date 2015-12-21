package com.gmail.gogobebe2.christmasmiracle;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFormEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.event.world.ChunkLoadEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class ChristmasMiracle extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getLogger().info("Starting up " + this.getName() + ". If you need me to update this plugin, email at gogobebe2@gmail.com");
        for (World world : Bukkit.getWorlds()) world.setStorm(true);
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("Disabling " + this.getName() + ". If you need me to update this plugin, email at gogobebe2@gmail.com");
    }

    @EventHandler
    private void onChunkLoad(ChunkLoadEvent event) {
        Chunk chunk = event.getChunk();
        int cX = chunk.getX() * 16; // make it a world coordinate...
        int cZ = chunk.getZ() * 16;
        World world = chunk.getWorld();

        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                world.setBiome(cX + x, cZ + z, Biome.ICE_MOUNTAINS);
            }
        }
    }

    @EventHandler
    private void onWeatherChange(WeatherChangeEvent event) {
        event.setCancelled(true);
    }

    @EventHandler
    private void onBlockForm(BlockFormEvent event) {
        Material type = event.getBlock().getType();
        if (type == Material.SNOW || type == Material.SNOW_BLOCK) event.setCancelled(true);
    }
}