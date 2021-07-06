package main.gamemodemanager;

import main.gamemodemanager.gamemodes.*;
import main.gamemodemanager.listeners.Destroy;
import main.gamemodemanager.listeners.Move;
import main.gamemodemanager.listeners.Place;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public final class Main extends JavaPlugin {



    public String noperm = getConfig().getString("noperm");


    List<String> developers = getDescription().getAuthors();
    String pluginname = getDescription().getName();
    String version = getDescription().getVersion();

    private void events(){
        new Move(this);
        new Place(this);
        new Destroy(this);
    }

    private void load() {
        getServer().getConsoleSender().sendMessage("____________________________________");
        getServer().getConsoleSender().sendMessage("|                                  |");
        getServer().getConsoleSender().sendMessage("|__________________________________|");
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage("Name Plugin: " + pluginname);
        getServer().getConsoleSender().sendMessage("Version: " + version);
        getServer().getConsoleSender().sendMessage("Developers: " + developers);
        getServer().getConsoleSender().sendMessage("");
        getServer().getConsoleSender().sendMessage("____________________________________");
        getServer().getConsoleSender().sendMessage("|                                  |");
        getServer().getConsoleSender().sendMessage("|                                  |");
        getServer().getConsoleSender().sendMessage("____________________________________");
    }

    private void commands(){
        new Creative(this);
        new Adventure(this);
        new Spectator(this);
        new Survival(this);
        new Manager(this);
    }

    @Override
    public void onEnable() {
        commands();
        events();
        saveDefaultConfig();
        load();


    }

    @Override
    public void onDisable() {

    }
}
