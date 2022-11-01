package me.kasture.atunner.GuildPlayer;

import java.util.HashSet;
import java.util.UUID;

public class GuildPlayerManager {
    static HashSet<GuildPlayer> guildPlayerSet = new HashSet<>();
    static HashSet<UUID> registeredUUIDs = new HashSet<>();

    public static HashSet<GuildPlayer> getGuildPlayerSet() {
        return guildPlayerSet;
    }

    public static void addGuildPlayer(GuildPlayer guildPlayer) {
        guildPlayerSet.add(guildPlayer);
        registeredUUIDs.add(guildPlayer.getUuid());
    }

    public static boolean guildPlayerRegistered(UUID uuid) {
        return registeredUUIDs.contains(uuid);
    }

    public static GuildPlayer seekGuildPlayer(UUID uuid) {
        for (GuildPlayer gPlayer : guildPlayerSet) {
            if (gPlayer.getUuid().equals(uuid)) return gPlayer;
        }
        return null;
    }
}
