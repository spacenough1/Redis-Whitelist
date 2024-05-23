package pl.codelen.whitelist.config;

import static pl.codelen.whitelist.Whitelist.getInstance;
public class Config {
  // redis
  public static final String redisHost = getInstance().config.getString("redis-host");
  public static final int redisPort = getInstance().config.getInt("redis-port");
  public static final String redisPassword = getInstance().config.getString("redis-password");
  public static final String noPermission = getInstance().config.getString("no-permission");
}
