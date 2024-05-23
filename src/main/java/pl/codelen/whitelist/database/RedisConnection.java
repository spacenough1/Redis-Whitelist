package pl.codelen.whitelist.database;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.Connection;
import redis.clients.jedis.JedisPooled;

public class RedisConnection {
  private JedisPooled jedisPooled;
  private final String host;
  private final int port;
  private final String password;

  public RedisConnection(String host, int port, String password) {
    this.host = host;
    this.port = port;
    this.password = password;
  }

  public void connect() {
    GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
    jedisPooled = new JedisPooled(poolConfig, host, port, 2000, password, 3);
  }

  public void disconnect() {
    jedisPooled.close();
  }

  public JedisPooled getJedis() {
    return jedisPooled;
  }
}
