package me.all298lie.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class Profile {
    public static void run(SlashCommandInteractionEvent event) {
        Member member = event.getOption("유저", event::getMember, OptionMapping::getAsMember);
        MessageEmbed embed = new EmbedBuilder()
                .setAuthor(member.getEffectiveName())
                .setImage(member.getEffectiveAvatarUrl()+"?size=1024")
                .setFooter(member.getUser().getAsTag())
                .setColor(0x00b4d8)
                .build();
        Message message = new MessageBuilder(embed).build();
        event.reply(message).queue();
    }
}
