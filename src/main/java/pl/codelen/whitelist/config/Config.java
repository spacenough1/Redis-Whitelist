package pl.codelen.whitelist.config;

import pl.codelen.whitelist.Whitelist;

public class Config {
  // redis
  private static final String redisHost;
  private static final int redisPort;
  private static final String redisPassword;
  private static final String noPermission;

  static {
    redisHost = Whitelist.getInstance().config.getString("redis-host");
    redisPort = Whitelist.getInstance().config.getInt("redis-port");
    redisPassword = Whitelist.getInstance().config.getString("redis-password");
    noPermission = Whitelist.getInstance().config.getString("no-permission");
  }

  public static String getRedisHost() {
    return redisHost;
  }

  public static int getRedisPort() {
    return redisPort;
  }

  public static String getRedisPassword() {
    return redisPassword;
  }

  public static String getNoPermission() {
    return noPermission;
  }


}
