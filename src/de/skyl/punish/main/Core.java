package de.skyl.punish.main;
// Coded By SkylTV //
// Copyright SkylTV //



import de.skyl.punish.utils.ReportSystem;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.Node;
import org.json.JSONObject;

import javax.net.ssl.HttpsURLConnection;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;


public class Core {

    public static Core core;
    private Main main;
    private Manager manager;
    private String prefix;
    private String noPlayer;
    private String noPermission;
    private String noSender;
    private URL url;
    private ReportSystem reportSystem;
    private LuckPerms api;
    private String ulr = "https://discordapp.com/api/webhooks/828983743896879125/O5TjixRubbJKTglRDIReRVgCX0qs8bCMwyDJy23kG8VKxbhf6OJ3-jiFJftZKu0Hc2Tn";

    public Core(Main main, Manager manager, ReportSystem reportSystem){
        core = this;
        this.main = main;
        this.manager = manager;
        prefix = "§6Report §8» §r";
        noPlayer = prefix + "§7Dieser Spieler wurde nicht gefunden.";
        noPermission = prefix + "§7Dazu hast du keine Rechte.";

        noSender = "Du musst ein Spieler sein.";
        this.reportSystem = reportSystem;
        try {
            url = new URL("https://discord.com/api/webhooks/830932906448257056/sPdpuvgdKlj65C4Bu8cysZW0-mvHpUJZ8h5fWkV3cFes6ZYpoTDPY4g8yKriFa2Y83BO");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        api = LuckPermsProvider.get();
    }

    public void enableCore(){
        manager.registerCommands();
        manager.registerListeners();


    }

    public Main getMain() {
        return main;
    }

    public static Core getCore() {
        return core;
    }

    public Manager getManager() {
        return manager;
    }

    public String getNoPermission() {
        return noPermission;
    }

    public String getNoPlayer() {
        return noPlayer;
    }

    public String getNoSender() {
        return noSender;
    }

    public String getPrefix() {
        return prefix;
    }

    public static void setCore(Core core) {
        Core.core = core;
    }

    public void sendReportToDiscord(String content){
        try{
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("content", content);


            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.addRequestProperty("Content-Type", "application/json");
            connection.addRequestProperty("User-Agent", "Java-DiscordWebhook");
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            OutputStream stream = connection.getOutputStream();
            stream.write(jsonObject.toString().getBytes());
            stream.flush();
            stream.close();

            connection.getInputStream().close();
            connection.disconnect();
        }catch (IOException e){

        }

    }
    public void addPermission(User user, String permission) {
        // Add the permission
        user.data().add(Node.builder(permission).build());

        // Now we need to save changes.
        api.getUserManager().saveUser(user);
    }
    public void removePermission(User user, String permission) {
        // Add the permission
        user.data().remove(Node.builder(permission).build());

        // Now we need to save changes.
        api.getUserManager().saveUser(user);
    }

    public boolean getPermission(User user, String permission) {
        // Add the permission
        return user.getCachedData().getPermissionData().checkPermission(permission).asBoolean();
    }

    public ReportSystem getReportSystem() {
        return reportSystem;
    }

    public URL getUrl() {
        return url;
    }
}
