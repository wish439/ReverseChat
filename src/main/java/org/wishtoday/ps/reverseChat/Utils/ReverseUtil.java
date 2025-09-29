package org.wishtoday.ps.reverseChat.Utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.jetbrains.annotations.NotNull;

public class ReverseUtil {
    public static String reverseString(String s) {
        return reverseWH(new StringBuilder(s).reverse().toString());
    }
    @NotNull
    public static Component reverseComponent(Component component) {
        if (!(component instanceof TextComponent text)) return component;
        String content = text.content();
        String reversed = reverseString(content);
        return text.content(reversed);
    }
    private static String reverseWH(String string) {
        return string.replace("[?？]", "¿");
    }
}
