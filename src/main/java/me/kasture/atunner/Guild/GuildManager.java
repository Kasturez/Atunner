package me.kasture.atunner.Guild;

import java.util.HashSet;

public class GuildManager {
    static HashSet<Guild> guildSet = new HashSet<>();

    public static HashSet<Guild> getGuildSet() {
        return guildSet;
    }

    public static void addGuild(Guild guild) {
        guildSet.add(guild);
    }
}
