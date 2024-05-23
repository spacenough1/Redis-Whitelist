package pl.codelen.whitelist.logger;

import org.jetbrains.annotations.NotNull;
import pl.codelen.whitelist.Whitelist;

import java.util.logging.Logger;

public class LoggingSystem {
  private static final Logger LOGGER = Whitelist.getInstance().getLogger();
  private static final String PREFIX = "[Whitelist]: ";
  private static StringBuilder stringBuilder;

  public static void logInfo(@NotNull String... args) {
    stringBuilder = new StringBuilder();

    for (String arg : args) {
      stringBuilder.append(arg).append(" ");
    }
    LOGGER.info(PREFIX + stringBuilder.toString());
  }

  public static void logWarn(@NotNull String... args) {
    stringBuilder = new StringBuilder();

    for (String arg : args) {
      stringBuilder.append(arg).append(" ");
    }
    LOGGER.warning(PREFIX + stringBuilder.toString());
  }

  public static void logError(@NotNull String... args) {
    stringBuilder = new StringBuilder();

    for (String arg : args) {
      stringBuilder.append(arg).append(" ");
    }
    LOGGER.severe(PREFIX + stringBuilder.toString());
  }
}