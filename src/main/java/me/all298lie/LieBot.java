package me.all298lie;

import me.all298lie.commands.Dice;
import me.all298lie.commands.MapleStory;
import me.all298lie.commands.Profile;
import net.dv8tion.jda.api.*;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;

import javax.security.auth.login.LoginException;
import java.util.Collections;

public class LieBot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {

        JDA jda = JDABuilder.createLight(args[0], Collections.emptyList())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("소아 염탐"))
                .addEventListeners(new LieBot())
                .build();

        CommandListUpdateAction commands = jda.updateCommands();

        // /프로필 (유저)
        commands.addCommands(
                Commands.slash("프로필", "디스코드 프로필 사진을 불러옵니다.")
                        .addOptions(new OptionData(OptionType.USER, "유저", "프로필을 확인할 유저"))
        );

        // /주사위 (숫자)
        commands.addCommands(
                Commands.slash("주사위", "1에서 100까지의 눈금을 가진 주사위를 던집니다.")
        );

        // /분배금 (금액) (인원수)
        commands.addCommands(
                Commands.slash("분배금", "옥션에서 판매한 아이템의 수수료를 계산해 분배금을 책정합니다.")
                        .addOptions(new OptionData(OptionType.INTEGER, "금액", "아이템 가격")
                                .setMinValue(1)
                                .setRequired(true))
                        .addOptions(new OptionData(OptionType.INTEGER, "인원_수", "분배해줄 인원")
                                .setMinValue(1)
                                .setRequired(true))
        );

        commands.addCommands(
                Commands.slash("정보", "메이플 유저 정보를 maple.gg에서 불러옵니다.")
                        .addOptions(new OptionData(OptionType.STRING, "닉네임", "정보를 확인할 닉네임")
                                .setRequired(true))
        );

        commands.queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) return;

        switch (event.getName()) {
            case "프로필" -> Profile.run(event);

            case "주사위" -> Dice.run(event);

            case "분배금" -> MapleStory.runDistributionMoney(event);

            case "정보" -> MapleStory.runUserInfo(event);
        }
    }
}
