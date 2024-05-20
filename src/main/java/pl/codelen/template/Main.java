package pl.codelen.template;

import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import pl.codelen.template.commands.CommandManager;
import pl.codelen.template.commands.CommandRegistrar;

import static pl.codelen.template.logger.LoggingSystem.logError;
import static pl.codelen.template.logger.LoggingSystem.logInfo;

public final class Main extends JavaPlugin {
  private static Main instance;
  public YamlDocument config;

  @Override
  public void onEnable() {
    instance = this;
    loadConfig();
    loadConfig();
    registerCommands();
    logInfo("Plugin template is enable!");
    logInfo("Latest version of plugin is 1.0.0-BETA");
  }

  public void registerCommands() {
    CommandRegistrar commandRegistrar = new CommandRegistrar(this);
    List<CommandManager> commandManagers = new ArrayList<>();

//    commandManagers.add(new ExampleCommand());

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

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }

  public static Main getInstance() {
    return instance;
  }

  public YamlDocument getConfigurationFile() {
    return config;
  }
}
