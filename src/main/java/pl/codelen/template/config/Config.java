package pl.codelen.template.config;

import pl.codelen.template.Main;

public class Config {
  // redis
  public static final String redisHost = Main.getInstance().getConfigurationFile().getString("redis-host");
  public static final String redisPort = Main.getInstance().getConfigurationFile().getString("redis-port");
  public static final String redisPassword = Main.getInstance().getConfigurationFile().getString("redis-password");

  // messages
  public static final String unknownCommand = Main.getInstance().getConfigurationFile().getString("unknown-command");
  public static final String noPermission = Main.getInstance().getConfigurationFile().getString("no-permission");
}
