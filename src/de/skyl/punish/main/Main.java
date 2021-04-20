package de.skyl.punish.main;
// Coded By SkylTV //
// Copyright SkylTV //



import de.skyl.punish.utils.ReportSystem;
import de.skyl.punish.utils.MySQL;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;

import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


import java.io.File;
import java.io.IOException;

public class Main extends Plugin {

    private Main main;
    private Manager manager;
    private ReportSystem reportSystem;
    private Configuration config, reportid;

    @Override
    public void onLoad() {
        main = this;
    }

    @Override
    public void onEnable() {
        manager = new Manager(this);
        reportSystem = new ReportSystem(this);
        Core.setCore(new Core(main, manager, reportSystem));
        Core.getCore().enableCore();
        createFolder();


        MySQL.host = getConfig("config").get("host").toString();
        MySQL.port = getConfig("config").getInt("port");
        MySQL.database = getConfig("config").get("database").toString();
        MySQL.username = getConfig("config").get("username").toString();
        MySQL.password = getConfig("config").get("password").toString();
        MySQL.connect();
        reportSystem.createTable();
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("----- §cPunishSystem §r----"));
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("§7Enabled"));
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("§7Coded By SkylTV"));
        ProxyServer.getInstance().getConsole().sendMessage(new TextComponent("-------------------------"));
    }


    public void onDisable() {
        if(MySQL.isConnected()){
            MySQL.disconnect();
        }else{
            return;
        }

    }

    private void createFolder(){
        if(!getDataFolder().exists()){
            getDataFolder().mkdirs();
        }
        File MysqlFile = new File(getDataFolder(), "mysql.yml");
        File reportFile = new File(getDataFolder(), "reportids.yml");
        if (!MysqlFile.exists()) {
            try {
                MysqlFile.createNewFile();
                getConfig("config").set("host", "localhost");
                getConfig("config").set("port", 3306);
                getConfig("config").set("username", "localuser");
                getConfig("config").set("password", "localpassword");
                getConfig("config").set("database", "PunishSystem");
                saveConfig();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(!reportFile.exists()){
            try {
                reportFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    private void loaderConfiguration(){
        try {
            config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "mysql.yml"));
            reportid = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "reportids.yml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public Configuration getConfig(String type) {
        if(type.equalsIgnoreCase("config")){
            if(config == null){
                reloadConfig();
            }
            return config;
        }

        if(type.equalsIgnoreCase("report")){
            if(reportid == null){
                reloadConfig();
            }
            return reportid;
        }

        return null;
    }
    public void saveConfig() {
        if ((config != null)) {
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, new File(getDataFolder(), "mysql.yml"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if ((reportid != null)) {
            try {
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(reportid, new File(getDataFolder(), "reportids.yml"));

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void reloadConfig() {
        loaderConfiguration();
    }
}
