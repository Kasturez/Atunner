package me.kasture.atunner;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import me.kasture.atunner.Guild.Guild;
import me.kasture.atunner.Guild.GuildManager;
import me.kasture.atunner.GuildPlayer.GuildPlayer;
import me.kasture.atunner.GuildPlayer.GuildPlayerManager;
import me.kasture.atunner.Utils.ConnectDb;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Atunner extends JavaPlugin implements Listener {

    ConnectDb connectDb;
    MongoClient mongoClient;
    MongoDatabase database;
    MongoCollection<Guild> guilds;
    MongoCollection<GuildPlayer> guildPlayers;
    FindIterable<Guild> guildDocs;
    FindIterable<GuildPlayer> guildPlayerDocs;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);

        //connect to MongoDB
        connectDb = new ConnectDb();
        mongoClient = connectDb.getMongoClient();
        database = mongoClient.getDatabase("atunner");
        guilds = database.getCollection("guild", Guild.class);
        guildPlayers = database.getCollection("guild_player", GuildPlayer.class);

        //fetch collections from MongoDB
        guildDocs = guilds.find();
        for (Guild guild : guildDocs) {
            getLogger().info("fetching guild info from database to GuildManager" + guild.toString());
            GuildManager.addGuild(guild);
        }
        guildPlayerDocs = guildPlayers.find();
        for (GuildPlayer guildPlayer : guildPlayerDocs) {
            getLogger().info("fetching guildPlayer info from database to GuildPlayerManager" + guildPlayer.toString());
            GuildPlayerManager.addGuildPlayer(guildPlayer);
        }
    }

    //register new player to Guild Player
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        e.getPlayer().sendMessage("hello there :D");
        // check if player

        GuildPlayerManager.addGuildPlayer(new GuildPlayer(e.getPlayer().getUniqueId()));
    }

    //handle commands
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            sender.sendMessage("you are a player");
            Player player = (Player) sender;
            GuildPlayer guildPlayer = GuildPlayerManager.seekGuildPlayer(player.getUniqueId());
            if (command.toString().equalsIgnoreCase("guild")) {
                if (args[0].equalsIgnoreCase("create")) {
                    if (args.length == 1) return false;
                    Guild guild = new Guild(args[1], "default description");
                    guild.addPlayer(guildPlayer);
                }
                if (args[0].equalsIgnoreCase("invite")){
                    if (args.length == 1) return false;

                }
            }
        }

        sender.sendMessage("you are server" + args[0]);


        return true;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        guilds.deleteMany(new BasicDBObject());
        for (Guild guild : GuildManager.getGuildSet()) {
            guilds.insertOne(guild);
        }
        guildPlayers.deleteMany(new BasicDBObject());
        for (GuildPlayer guildPlayer : GuildPlayerManager.getGuildPlayerSet()) {
            guildPlayers.insertOne(guildPlayer);

        }
    }
}
