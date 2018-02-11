package us.flowdesigns.listener;

import us.flowdesigns.fuse.Fuse;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static us.flowdesigns.fuse.Fuse.plugin;

public class PotionListener implements Listener {

String potions_enabled = plugin.getConfig().getString("server.splash_potions_enabled");
    @EventHandler
    public void onPotionSplashEvent(final PotionSplashEvent event) {
        Player player = (Player) event.getEntity().getShooter();
        Entity entity = event.getEntity();

        switch (event.getPotion().getType()) {
            case SPLASH_POTION:
                player.getInventory().setItem(player.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR, 1));
                player.sendMessage(ChatColor.GOLD + "Fuse >> Splash potions are disabled");
                event.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {
                        event.getAffectedEntities().forEach((entity) -> {
                            event.getPotion().getEffects().stream().filter((effect) -> (entity instanceof Player)).forEachOrdered((effect) -> {
                                Player player = (Player) entity;
                                player.removePotionEffect(effect.getType());
                            });
                        });
                    }
                }.runTaskLater(Fuse.getInstance(), 1);
                break;
        }
    }
}