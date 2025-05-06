package me.yourname.oneplayersleep;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class OnePlayerSleep extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getPluginManager().registerEvents(this, this);
        getLogger().info("One Player Sleep Enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("One Player Sleep Disabled!");
    }

    @EventHandler
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (event.getBedEnterResult() != PlayerBedEnterEvent.BedEnterResult.OK) return;

        Player sleeper = event.getPlayer();
        World world = sleeper.getWorld();

        Bukkit.getScheduler().runTaskLater(this, () -> {
            if (sleeper.isSleeping()) {
                world.setTime(0);
                world.setStorm(false);
                world.setThundering(false);
                Bukkit.broadcastMessage(ChatColor.YELLOW + sleeper.getName() + " slept and skipped the night!");
            }
        }, 100L); // wait 5 seconds
    }
}