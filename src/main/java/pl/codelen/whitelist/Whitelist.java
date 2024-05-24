package pl.codelen.whitelist;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.codelen.whitelist.commands.CommandManager;
import pl.codelen.whitelist.commands.CommandRegistrar;
import pl.codelen.whitelist.commands.components.WhitelistCommand;
import pl.codelen.whitelist.config.Config;
import pl.codelen.whitelist.database.RedisConnection;
import pl.codelen.whitelist.event.WhitelistEvent;

import static pl.codelen.whitelist.logger.LoggingSystem.logError;
import static pl.codelen.whitelist.logger.LoggingSystem.logInfo;

public final class Whitelist extends JavaPlugin {
  private static Whitelist instance;
  public YamlDocument config;
  @Override
  public void onEnable() {
    instance = this;
    loadConfig();
    registerCommands();
    registerEvents();

    if (redisTestConnection()) {
      logInfo("Plugin jest wlaczony");
      logInfo("Polaczenie z baza danych jest stabilne");
      return;
    }
    logError("Nie mozna bylo utrzymac polaczenia z baza danych. Plugin sie wylacza!");
    getServer().getPluginManager().disablePlugin(this);
  }

  private void registerEvents() {
    Bukkit.getPluginManager().registerEvents(new WhitelistEvent(), this);
  }

  public void registerCommands() {
    CommandRegistrar commandRegistrar = new CommandRegistrar(this);
    List<CommandManager> commandManagers = new ArrayList<>();

    commandManagers.add(new WhitelistCommand());

    commandRegistrar.registerCommands(commandManagers);
  }

  public void loadConfig() {
    try {
      config = YamlDocument.create(new File(getDataFolder(), "config.yml"),
              getResource("config.yml"),
              GeneralSettings.DEFAULT,
              LoaderSettings.builder().setAutoUpdate(true).build(),
              DumperSettings.DEFAULT,
              UpdaterSettings.builder().setVersioning(new BasicVersioning("config-version")).build());
    } catch (IOException e) {
      logError("Plugin could not load configuration file. Details:", e.getMessage());
    }
  }

  public boolean redisTestConnection() {
    RedisConnection redisConnection = new RedisConnection(Config.getRedisHost(), Config.getRedisPort(), Config.getRedisPassword());
    redisConnection.connect();
    String response = redisConnection.getJedis().ping();
    redisConnection.disconnect();
    return response.equalsIgnoreCase("pong");
  }

  @Override
  public void onDisable() {
    logInfo("Plugin zostaje wyłączony!");
  }

  public static Whitelist getInstance() {
    return instance;
  }
}
