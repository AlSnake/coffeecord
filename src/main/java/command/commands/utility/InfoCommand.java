package command.commands.utility;

import command.CommandContext;
import command.ICommand;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.util.List;

public class InfoCommand implements ICommand {
    private int numberOfGuilds;
    private static String botInviteUrl;

    @Override
    public void execute(CommandContext ctx) {
        List<String> args = ctx.getArgs();
        TextChannel channel = ctx.getChannel();
        numberOfGuilds = ctx.getJDA().getGuilds().size();
        botInviteUrl = ctx.getJDA().getInviteUrl(Permission.ADMINISTRATOR);

        if(args.size() <= 0) {
            channel.sendMessage(getHelp()).queue();
            return;
        }

        if(args.get(0).equals("enforcer"))
            channel.sendMessageEmbeds(getEnforcerInfoEmbed().build()).queue();
        else if(args.get(0).equals("bot"))
            channel.sendMessageEmbeds(getBotInfoEmbed().build()).queue();
        else
            channel.sendMessage(getHelp()).queue();
    }

    @Override
    public String getCommand() {
        return "info";
    }

    @Override
    public String getHelp() {
        return "Get Info about Enforcer or Bot\ninfo [bot/enforcer]\n";
    }

    private EmbedBuilder getEnforcerInfoEmbed() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Enforcer Info");
        embed.setColor(Color.CYAN);

        embed.addField("\u276F Enforcer Discord Server", "[Enforcer](https://discord.gg/556T79UhYr)", true);
        embed.addField("\u276F Enforcer Github", "[Enforcer](https://github.com/lowlevelenforcer)", true);

        embed.setFooter("\u00A9 2021 Enforcer");
        return embed;
    }

    private EmbedBuilder getBotInfoEmbed() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Bot Info");
        embed.setColor(Color.CYAN);

        embed.addField("\u276F Home Server", "[CoffeeCord](https://discord.gg/WYJgdKbfKK)", true);
        embed.addField("\u276F Invite Bot", "[CoffeeCord](" + botInviteUrl + ")", true);
        embed.addField("\u276F Servers", Integer.toString(numberOfGuilds), true);
        embed.addField("\u276F Source Code", "[Github](https://github.com/lowlevelenforcer/coffeecord)", true);
        embed.addField("\u276F Version", "0.0.1", true);
        embed.addField("\u276F Author", "Enforcer", true);

        embed.setFooter("\u00A9 2021 Enforcer");
        return embed;
    }
}
