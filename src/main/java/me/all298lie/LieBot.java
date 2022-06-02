package me.all298lie;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.security.auth.login.LoginException;
import java.util.Collections;

public class LieBot extends ListenerAdapter {
    public static void main(String[] args) throws LoginException {
        JDA jda = JDABuilder.createLight(args[0], Collections.emptyList())
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("소아 염탐"))
                .build();
    }
}
