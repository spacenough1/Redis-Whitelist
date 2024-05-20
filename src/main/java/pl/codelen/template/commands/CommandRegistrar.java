package pl.codelen.template.commands;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class CommandRegistrar {
  private final Plugin plugin;

  public CommandRegistrar(Plugin plugin) {
    this.plugin = plugin;
  }

  public void registerCommands(@NotNull List<CommandManager> commandManagers) {
    for (CommandManager commandManager : commandManagers) {
      Objects.requireNonNull(plugin.getServer().getPluginCommand(commandManager.name())).setExecutor(commandManager);
    }
  }
}
