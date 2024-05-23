package pl.codelen.whitelist.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.jetbrains.annotations.NotNull;

public class Colors {
  @NotNull
  public static Component fixSyntax(String text) {
    return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
  }
}