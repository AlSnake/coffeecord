package command.commands.utility;

import command.CommandContext;
import command.CommandManager;
import command.ICommand;
import config.Config;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class HelpCommand implements ICommand {
    private static String botInviteUrl;

    @Override
    public void execute(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        botInviteUrl = ctx.getJDA().getInviteUrl(Permission.ADMINISTRATOR);

        if(args.size() > 0) {
            ICommand cmd = CommandManager.getCommand(args.get(0));
            if(cmd != null) {
                channel.sendMessage(cmd.getHelp()).queue();
                return;
            }
        }

        channel.sendMessageEmbeds(getHelpEmbed().build()).queue();
    }

    @Override
    public String getCommand() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "help menu\n" + "help [COMMAND]";
    }

    private EmbedBuilder getHelpEmbed() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Help Commands");
        embed.setColor(Color.CYAN);
        embed.setDescription("Prefix: " + Config.get("prefix") + "\nVersion: 0.0.1");

        embed.addField("\u276F Home Server", "[CoffeeCord](https://discord.gg/WYJgdKbfKK)", true);
        embed.addField("\u276F Invite Bot", "[CoffeeCord](" + botInviteUrl + ")", true);
        embed.addField("\u276F Author", "[Enforcer](https://github.com/lowlevelenforcer)", true);

        embed.addField("\u276F Utility", "`help` " +
                "`ping` " +
                "`info [bot/enforcer]` ", true);

        embed.setFooter("\u276F \u00A9 2021 Enforcer");
        return embed;
    }
}
