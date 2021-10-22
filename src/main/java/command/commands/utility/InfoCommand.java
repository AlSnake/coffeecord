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

        if(args.get(0).equals("alsnake"))
            channel.sendMessageEmbeds(getAlSnakeInfoEmbed().build()).queue();
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
        return "Get Info about AlSnake or Bot\ninfo [bot/alsnake]\n";
    }

    private EmbedBuilder getAlSnakeInfoEmbed() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("AlSnake Info");
        embed.setColor(Color.CYAN);

        embed.addField("\u276F AlSnake Discord Server", "[AlSnake](https://discord.gg/556T79UhYr)", true);
        embed.addField("\u276F AlSnake Github", "[AlSnake](https://github.com/AlSnake)", true);

        embed.setFooter("\u00A9 2021 AlSnake");
        return embed;
    }

    private EmbedBuilder getBotInfoEmbed() {
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("Bot Info");
        embed.setColor(Color.CYAN);

        embed.addField("\u276F Home Server", "[CoffeeCord](https://discord.gg/WYJgdKbfKK)", true);
        embed.addField("\u276F Invite Bot", "[CoffeeCord](" + botInviteUrl + ")", true);
        embed.addField("\u276F Servers", Integer.toString(numberOfGuilds), true);
        embed.addField("\u276F Source Code", "[Github](https://github.com/AlSnake/coffeecord)", true);
        embed.addField("\u276F Version", "0.0.1", true);
        embed.addField("\u276F Author", "AlSnake", true);

        embed.setFooter("\u00A9 2021 AlSnake");
        return embed;
    }
}
