package org.wishtoday.ps.reverseChat.Events;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.persistence.PersistentDataType;
import org.wishtoday.ps.reverseChat.ReverseChat;
import org.wishtoday.ps.reverseChat.Utils.ReverseUtil;

public class PlayerChatEvent implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncChatEvent event) {
        Player player = event.getPlayer();
        Boolean b = player.getPersistentDataContainer().get(ReverseChat.REVERSED, PersistentDataType.BOOLEAN);
        if (b == null || !b) return;
        Component message = event.message();
        Component component = ReverseUtil.reverseComponent(message);
        event.message(component);
    }
}
