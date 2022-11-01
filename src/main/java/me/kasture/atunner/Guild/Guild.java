package me.kasture.atunner.Guild;

import me.kasture.atunner.GuildPlayer.GuildPlayer;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.util.HashSet;

public class Guild {
    //properties
    @BsonProperty(value = "guild_id")
    private ObjectId id;
    @BsonProperty(value = "guild_name")
    private String name;
    @BsonProperty(value = "guild_description")
    private String desc;
    @BsonProperty(value = "guild_player_set")
    private final HashSet<GuildPlayer> guildPlayerSet = new HashSet<>();

    public Guild(ObjectId id, String name, String desc) {
        this.id = id;
        this.name = name;
        this.desc = desc;
    }

    public boolean addPlayer(GuildPlayer guildPlayer) {
        //check if guild
        boolean result = guildPlayerSet.contains(guildPlayer);
        if (!result && guildPlayer.getGuild() == null) {
            guildPlayerSet.add(guildPlayer);
            guildPlayer.setGuild(this);
        }
        return (!result && guildPlayer.getGuild() == null);
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", guildPlayerSet=" + guildPlayerSet +
                '}';
    }

    public ObjectId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public HashSet<GuildPlayer> getGuildPlayerSet() {
        return guildPlayerSet;
    }

    public Guild() {
    }

    public Guild(GuildPlayer guildPlayer) {
        this.name = "wild";
        this.desc = "default description";
        this.guildPlayerSet.add(guildPlayer);
    }

    public Guild(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
