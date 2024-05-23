package pl.codelen.whitelist.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;

import static pl.codelen.whitelist.utils.Colors.fixSyntax;
import static pl.codelen.whitelist.utils.RedisUtils.getWhitelist;
import static pl.codelen.whitelist.utils.RedisUtils.getWhitelistStatus;

public class WhitelistEvent implements Listener {
  @EventHandler
  public void whitelistCheck(AsyncPlayerPreLoginEvent e) {
    if (getWhitelistStatus()) {
      if (!getWhitelist().contains(e.getName())) {
        e.kickMessage(fixSyntax("&f&l[&4&lHADESCRAFT&f&l] » &cSerwer jest obecnie niedostępny dla graczy!"));
        e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
      }
    }
  }
}
