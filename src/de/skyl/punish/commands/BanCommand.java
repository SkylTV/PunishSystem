package de.skyl.punish.commands;
// Coded By SkylTV //
// Copyright SkylTV //

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BanCommand extends Command {

    public BanCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length == 3){
            ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
            ProxiedPlayer player = (ProxiedPlayer) sender;
            int message = Integer.parseInt(args[2]);
            if(args[2].endsWith("m")){
                if(message > Integer.parseInt(60 + "m")){

                }
            }else if(args[2].endsWith("h")){
                if(message > Integer.parseInt(24 + "h")){

                }
            }else if(args[2].endsWith("d")){
                if(message > Integer.parseInt( 30 + "d")){

                }
            }else if(args[2].endsWith("m")){
                if(message > Integer.parseInt(12 + "m")){

                }
            }else if(args[2].endsWith("y")){
                if(message > Integer.parseInt(60 + "m")){

                }
            }
        }
    }
}
