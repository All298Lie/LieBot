package me.all298lie;

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

        commands.queue();
    }

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getGuild() == null) return;

        switch (event.getName()) {
            case "프로필" -> Profile.run(event);
        }
    }
}
