package at.lorenz.test.command;

import at.lorenz.test.TestPlugin;
import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestCommand implements CommandExecutor {

    private final TestPlugin plugin;

    public TestCommand(final TestPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(final CommandSender commandSender, final Command command, final String s, final String[] strings) {
        if (commandSender instanceof Player) {
            final Player player = (Player) commandSender;

            List<Sheep> sheeps = plugin.getSheeps();
            for (final Sheep sheep : sheeps) {
                sheep.remove();
            }
            sheeps.clear();
            final Location location = player.getLocation();
            final World world = location.getWorld();

            final List<DyeColor> colors = new ArrayList<>(Arrays.asList(DyeColor.values()));
            final ThreadLocalRandom random = ThreadLocalRandom.current();
            for (int i = 0; i < 3; i++) {
                final Sheep sheep = world.spawn(location, Sheep.class);

                sheeps.add(sheep);

                final int index = random.nextInt(colors.size() - 1);
                final DyeColor color = colors.get(index);
                colors.remove(color);
                sheep.setColor(color);
            }
        }
        return true;
    }
}
