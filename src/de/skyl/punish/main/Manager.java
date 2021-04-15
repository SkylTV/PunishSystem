package de.skyl.punish.main;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.punish.commands.ReportCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginManager;

public class Manager {

    private Main main;
    private PluginManager pluginManager;

    public Manager(Main main){
        this.main = main;
        this.pluginManager = ProxyServer.getInstance().getPluginManager();
    }

    public void registerListeners(){

    }
    public void registerCommands(){
        pluginManager.registerCommand(main, new ReportCommand("report"));
    }

}
