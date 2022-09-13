package me.all298lie.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Dice {
    public static void run(SlashCommandInteractionEvent event) {
        int value = (int) (Math.random() * 100) + 1;
        MessageEmbed embed = new EmbedBuilder()
                .setTitle(":game_die: 랜덤 주사위")
                .setDescription("주사위 눈금 : " + value)
                .setFooter(event.getUser().getAsTag())
                .setColor(0x00b4d8)
                .build();
        event.replyEmbeds(embed).queue();
    }
}
