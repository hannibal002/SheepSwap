package at.lorenz.test;

import at.lorenz.test.command.TestCommand;
import at.lorenz.test.listener.SheepListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Sheep;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin extends JavaPlugin {

    private final List<Sheep> sheeps = new ArrayList<>();

    public List<Sheep> getSheeps() {
        return sheeps;
    }

    @Override
    public void onEnable() {
        registerCommands();
        registerListeners();
    }

    @Override
    public void onDisable() {
        removeSheeps();
    }

    private void removeSheeps() {
        for (final Sheep sheep : sheeps) {
            sheep.remove();
        }
    }

    private void registerListeners() {
        Bukkit.getPluginManager().registerEvents(new SheepListener(this), this);
    }

    private void registerCommands() {
        getCommand("test").setExecutor(new TestCommand(this));
    }
}
