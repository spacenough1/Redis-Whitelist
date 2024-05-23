package pl.codelen.whitelist.commands.components;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import pl.codelen.whitelist.commands.CommandManager;

import static pl.codelen.whitelist.utils.Colors.fixSyntax;
import static pl.codelen.whitelist.utils.RedisUtils.*;

public class WhitelistCommand extends CommandManager {

  public WhitelistCommand() {
    super("whitelist", "whitelist.manipulation", false);
  }

  @Override
  public void execute(CommandSender commandSender, @NotNull String[] args) {
    if (args.length == 0) {
      commandSender.sendMessage(Component.text("/whitelist <on/off/add/remove/list/status> (gracz)").color(TextColor.fromCSSHexString("#FF0000")));
      return;
    }

    if (args.length == 1) {
      switch (args[0]) {
        case "on" -> whitelistStatusChange(commandSender, true);
        case "status" -> commandSender.sendMessage(fixSyntax("&aWhitelista jest &7" + whitelistStatusFormatter()));
        case "off" -> whitelistStatusChange(commandSender, false);
        case "list" -> commandSender.sendMessage(fixSyntax("&aGracze na whitelist'cie &7" + getWhitelist()));
        default -> commandSender.sendMessage(Component.text("/whitelist <on/off/add/remove/list/status> (gracz)").color(TextColor.fromCSSHexString("#FF0000")));
      }
      return;
    }

    if (args.length == 2) {
      whitelistUserManipulationLogic(commandSender, args);
      return;
    }
    commandSender.sendMessage(Component.text("/whitelist <on/off/add/remove/list/status> (gracz)").color(TextColor.fromCSSHexString("#FF0000")));
  }

  private void whitelistStatusChange(@NotNull CommandSender commandSender, boolean status) {
    setWhitelistStatus(status);
    commandSender.sendMessage(fixSyntax("&aWhitelista została &7" + whitelistStatusFormatter()));
  }

  private void whitelistUserManipulationLogic(CommandSender commandSender, @NotNull String[] args) {
    switch (args[0]) {
      case "add" -> {
        String player = args[1];
        if (player == null) {
          commandSender.sendMessage(Component.text("/whitelist <on/off/add/remove/list/status> (gracz)").color(TextColor.fromCSSHexString("#FF0000")));
          return;
        }
        addToWhitelist(player);
        commandSender.sendMessage(fixSyntax("&aDodano gracza &7" + player + " &a do whitelist'y"));
      }
      case "remove" -> {
        String player = args[1];
        if (player == null) {
          commandSender.sendMessage(Component.text("/whitelist <on/off/add/remove/list/status> (gracz)").color(TextColor.fromCSSHexString("#FF0000")));
          return;
        }
        removePlayerFromWhitelist(player);
        commandSender.sendMessage(fixSyntax("&aUsunięto gracza &7" + player + " &a z whitelist'y"));

      }
      default -> commandSender.sendMessage(Component.text("/whitelist <on/off/add/remove/list/status> (gracz)").color(TextColor.fromCSSHexString("#FF0000")));
    }
  }

  @NotNull
  private String whitelistStatusFormatter() {
    return getWhitelistStatus() ? "włączona" : "wyłączona";
  }
}