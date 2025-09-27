package org.wishtoday.ps.reverseChat;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import org.wishtoday.ps.reverseChat.Command.MainCommand;
import org.wishtoday.ps.reverseChat.Events.PlayerChatEvent;

public final class ReverseChat extends JavaPlugin {
    public static NamespacedKey REVERSED;

    private static ReverseChat instance;

    public static final String NAME = "[ReverseChat]";

    public static ReverseChat getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        REVERSED = NamespacedKey.fromString("reversed", this);
        //noinspection UnstableApiUsage
        this.getLifecycleManager().registerEventHandler(
                LifecycleEvents.COMMANDS, event -> {
                    MainCommand.register(event.registrar());
                }
        );
        this.getServer().getPluginManager().registerEvents(new PlayerChatEvent(), this);
    }

    @Override
    public void onDisable() {
    }
}
