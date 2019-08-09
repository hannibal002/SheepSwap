package at.lorenz.test.listener;

import at.lorenz.test.TestPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Sheep;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;

import java.util.List;

public class SheepListener implements Listener {

    private final TestPlugin plugin;

    public SheepListener(final TestPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    void onSheepClick(final PlayerInteractEntityEvent event) {
        System.out.println("PlayerInteractEntityEvent");
        final Entity entity = event.getRightClicked();
        final List<Sheep> sheeps = plugin.getSheeps();
        for (int i = 0; i < sheeps.size(); i++) {
            final Sheep sheep = sheeps.get(i);
            if (sheep.getUniqueId().equals(entity.getUniqueId())) {
                toggleOthers(i);
            }
        }
    }

    private void toggleOthers(final int index) {
        System.out.println("toggleOthers " + index);
        Sheep first = null;
        for (int i = 0; i < plugin.getSheeps().size(); i++) {
            final Sheep sheep = plugin.getSheeps().get(i);
            if (i != index) {
                if (first == null) {
                    System.out.println("a = " + i);
                    first = sheep;
                } else {
                    System.out.println("b = " + i);
                    toggle(first, sheep);
                }
            }
        }
    }

    private void toggle(final Sheep a, final Sheep b) {
        delayedTp(a, b.getLocation());
        delayedTp(b, a.getLocation());
    }

    private void delayedTp(final Sheep sheep, final Location location) {
        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            sheep.teleport(location);
            effect(location);
        }, 1);
    }

    private void effect(final Location location) {
        location.getWorld().playEffect(location, Effect.ENDER_SIGNAL, 1);
    }
}
