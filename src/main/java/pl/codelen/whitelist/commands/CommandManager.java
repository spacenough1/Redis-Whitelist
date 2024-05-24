package pl.codelen.whitelist.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.codelen.whitelist.config.Config;

import static pl.codelen.whitelist.utils.Colors.fixSyntax;

public abstract class CommandManager implements CommandExecutor, CommandInfo {
  private final String name;
  private final String permission;
  private final boolean requirePlayer;

  public CommandManager(String name, String permission, boolean requirePlayer) {
    this.name = name;
    this.permission = permission;
    this.requirePlayer = requirePlayer;
  }

  @Override
  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
    if (!(permission().isEmpty()) && !sender.hasPermission(permission()) && !sender.hasPermission("whitelist.all")) {
      sender.sendMessage(fixSyntax(Config.getNoPermission()));
      return true;
    }
    if (requirePlayer()) {
      if (sender instanceof Player) {
        execute((Player) sender, args);
      }
      return true;
    }
    execute(sender, args);
    return true;
  }

  public void execute(Player player, String[] args) {

  }

  public void execute(CommandSender sender, String[] args) {

  }

  @Override
  public String name() {
    return this.name;
  }

  @Override
  public String permission() {
    return this.permission;
  }

  @Override
  public boolean requirePlayer() {
    return this.requirePlayer;
  }
}