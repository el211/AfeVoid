package com.example.AfeliaVoid;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class AfeVoid extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("AfeVoid has been enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("AfeVoid has been disabled!");
    }

    @EventHandler
    public void onWorldInit(WorldInitEvent event) {
        World world = event.getWorld();
        getLogger().info("WorldInitEvent for world: " + world.getName());

        // Check if the world is a void world
        if (world.getGenerator() == null || world.getGenerator() instanceof VoidGenerator) {
            getLogger().info("Void world detected: " + world.getName());
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("afevoid")) {
            createVoidWorld();
            sender.sendMessage("Void world created!");
            return true;
        }
        return false;
    }

    private void createVoidWorld() {
        String worldName = "void";
        if (getServer().getWorld(worldName) != null) {
            getLogger().info("Void world already exists!");
            return;
        }

        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.generator(new VoidGenerator());
        getServer().createWorld(worldCreator);
    }

    public static class VoidGenerator extends ChunkGenerator {
        @Override
        public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
            return createChunkData(world);
        }
    }
}
