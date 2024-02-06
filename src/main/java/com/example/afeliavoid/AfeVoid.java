package com.example.voidafeliagen;

import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerLoadEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class AfeVoid extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
        getLogger().info("AfeVoid has been enabled!");
        loadVoidWorld();
    }

    @Override
    public void onDisable() {
        getLogger().info("AfeVoid has been disabled!");
    }

    @EventHandler
    public void onServerLoad(ServerLoadEvent event) {
        resetVoidWorld();
    }

    @EventHandler
    public void onServerShutdown(ServerCommandEvent event) {
        if (event.getCommand().equalsIgnoreCase("stop")) {
            saveVoidWorld();
        }
    }

    private void loadVoidWorld() {
        World voidWorld = getServer().getWorld("afevoid");
        if (voidWorld == null) {
            getLogger().info("Void world not found. Creating...");
            createVoidWorld();
        } else {
            getLogger().info("Loading void world: " + voidWorld.getName());
        }
    }

    private void createVoidWorld() {
        String worldName = "afevoid";
        if (getServer().getWorld(worldName) != null) {
            getLogger().info("Void world already exists!");
            return;
        }

        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.generator(new VoidGenerator());
        getServer().createWorld(worldCreator);
    }

    private void saveVoidWorld() {
        World voidWorld = getServer().getWorld("afevoid");
        if (voidWorld != null) {
            voidWorld.save();
            getLogger().info("Void world saved before server shutdown.");
        }
    }

    private void resetVoidWorld() {
        World voidWorld = getServer().getWorld("afevoid");
        if (voidWorld != null) {
            getLogger().info("Resetting void world: " + voidWorld.getName());

            // Unload the world
            getServer().unloadWorld(voidWorld, true);

            // Create and load the void world again
            createVoidWorld();
        } else {
            getLogger().warning("Void world not found during reset.");
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
}
