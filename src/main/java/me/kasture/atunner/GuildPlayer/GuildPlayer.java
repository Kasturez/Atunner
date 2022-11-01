package me.kasture.atunner.GuildPlayer;

import me.kasture.atunner.Guild.Guild;
import org.bson.codecs.pojo.annotations.BsonProperty;

import java.util.UUID;

public final class GuildPlayer {
    //properties
    @BsonProperty(value = "uuid")
    private UUID uuid;
    @BsonProperty(value = "guild")
    private Guild guild = null;



    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Guild getGuild() {
        return guild;
    }

    public void setGuild(Guild guild) {
        this.guild = guild;
    }

    public GuildPlayer(UUID uuid) {
        this.uuid = uuid;
    }

    public GuildPlayer() {
    }

    @Override
    public String toString() {
        return "GuildPlayer{" +
                "uuid=" + uuid +
                ", guild=" + guild +
                '}';
    }
}
