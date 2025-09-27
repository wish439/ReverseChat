package org.wishtoday.ps.reverseChat.Command;

import com.mojang.brigadier.context.CommandContext;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.wishtoday.ps.reverseChat.ReverseChat;

import java.util.List;

import static io.papermc.paper.command.brigadier.Commands.argument;
import static io.papermc.paper.command.brigadier.Commands.literal;
import static org.wishtoday.ps.reverseChat.ReverseChat.NAME;

@SuppressWarnings("UnstableApiUsage")
public class MainCommand {
    public static void register(Commands command) {
        command.register(
                literal("reversechat")
                        .requires(target -> target.getSender().isOp())
                        .then(
                                literal("all")
                                        .executes(
                                                MainCommand::toggleAll
                                        )
                        )
                        .then(
                                literal("only")
                                        .then(
                                                argument(
                                                        "player", ArgumentTypes.players()
                                                ).executes(
                                                        context ->
                                                                toggleOnly(context.getArgument("player", PlayerSelectorArgumentResolver.class)
                                                                        .resolve(context.getSource()), context)
                                                )
                                        )
                        )
                        .build()
        );
    }

    private static void toggleState(Player player, CommandContext<CommandSourceStack> ctx) {
        Boolean b = player.getPersistentDataContainer().get(ReverseChat.REVERSED, PersistentDataType.BOOLEAN);
        player.getPersistentDataContainer().set(ReverseChat.REVERSED, PersistentDataType.BOOLEAN, b == null || !b);
    }

    private static int toggleOnly(List<Player> players, CommandContext<CommandSourceStack> ctx) {
        players.forEach(player -> {
            toggleState(player, ctx);
            ctx.getSource().getSender().sendMessage(String.format("%s已反转%s的聊天", NAME, player.getName()));
        });
        return 1;
    }

    private static int toggleAll(CommandContext<CommandSourceStack> ctx) {
        Bukkit.getOnlinePlayers().forEach(player -> toggleState(player, ctx));
        ctx.getSource().getSender().sendMessage(String.format("%s已反转全服聊天", NAME));
        return 1;
    }
}
