package pl.codelen.whitelist.utils;

import org.jetbrains.annotations.NotNull;
import pl.codelen.whitelist.database.RedisConnection;

import java.util.List;

import static pl.codelen.whitelist.config.Config.*;

public class RedisUtils {
  private final static RedisConnection redis = new RedisConnection(redisHost, redisPort, redisPassword);
  private static final String KEY = "whitelist-list";
  private static final String STATUS_KEY = "whitelist-properties";
  
  public static void addToWhitelist(@NotNull String playerName) {
    redis.connect();
    redis.getJedis().sadd(KEY, playerName);
    redis.disconnect();
  }

  public static void setWhitelistStatus(boolean status) {
    redis.connect();
    redis.getJedis().hset(STATUS_KEY, "status", String.valueOf(status));
    redis.disconnect();
  }

  public static void removePlayerFromWhitelist(@NotNull String playerName) {
    redis.connect();
    redis.getJedis().srem(KEY, playerName);
    redis.disconnect();
  }

  public static List<String> getWhitelist() {
    redis.connect();
    List<String> whitelistList = redis.getJedis().smembers(KEY).stream().toList();
    redis.disconnect();
    return whitelistList;
  }

  public static boolean getWhitelistStatus() {
    redis.connect();
    String whitelistStatus = redis.getJedis().hget(STATUS_KEY, "status");
    redis.disconnect();
    return Boolean.parseBoolean(whitelistStatus);
  }
}
