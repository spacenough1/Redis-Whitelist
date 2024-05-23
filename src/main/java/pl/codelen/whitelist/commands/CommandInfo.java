package pl.codelen.whitelist.commands;

public interface CommandInfo {
  String name();

  default String permission() {
    return "";
  }

  default boolean requirePlayer() {
    return false;
  }
}