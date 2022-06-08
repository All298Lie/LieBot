package me.all298lie.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.text.DecimalFormat;

public class MapleStory {
    private static final DecimalFormat money = new DecimalFormat("###,###");

    public static void runDistributionMoney(SlashCommandInteractionEvent event) {
        long price = event.getOption("금액").getAsLong();
        int count = event.getOption("인원_수").getAsInt();
        MessageEmbed embed = new EmbedBuilder()
                .setTitle(":scales: 메이플 분배금 계산기")
                .setDescription(money.format(price) + " 메소를 " + count + "명에게 분배금 분배 \n"
                        + "- MVP, PC방 혜택 X: 인당 " + money.format(price*0.95/count) + " 메소 \n"
                        + "- MVP, PC방 혜택 O: 인당 " + money.format(price*0.97/count) + " 메소")
                .setFooter(event.getUser().getAsTag())
                .setColor(0x00b4d8)
                .build();
        Message message = new MessageBuilder(embed).build();
        event.reply(message).queue();
    }
}
