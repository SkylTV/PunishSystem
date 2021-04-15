package de.skyl.punish.commands;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.punish.main.Core;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

import java.util.concurrent.TimeUnit;

public class ReportCommand extends Command {
    public ReportCommand(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(sender instanceof ProxiedPlayer){
            ProxiedPlayer player = (ProxiedPlayer) sender;
            LuckPerms api = LuckPermsProvider.get();
            User user = api.getUserManager().getUser(player.getUniqueId());

            if(args.length == 2){
                ProxiedPlayer reported = ProxyServer.getInstance().getPlayer(args[0]);

                if(args[1].equalsIgnoreCase("teaming")){
                    int id = Core.getCore().getReportSystem().getReportID(reported, "Teaming");
                    if(id == -1){
                        Core.getCore().getReportSystem().registerReport(player, reported, "unbearbeitet", "Teaming");
                        player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix() + "§7Du hast den Spieler §6" + reported.getName()).append(" §7erfolgreich für Teaming §7Reportet.").create());

                        Core.getCore().sendReportToDiscord("> :scroll: Neuer Report :scroll: \n\n> Spieler : " + reported.getName() + "\n> Reporter : " + player.getName() + "\n> Grund : " + Core.getCore().getReportSystem().getSetting(Core.getCore().getReportSystem().getReportID(reported,"Teaming"), "Grund") + "\n> ID : " + Core.getCore().getReportSystem().getReportID(reported, "Teaming"));
                    }else{
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast bereits Report erstellt."));

                    }


                }else if (args[1].equalsIgnoreCase("hacking")){
                    int id = Core.getCore().getReportSystem().getReportID(reported, "Teaming");
                    if(Core.getCore().getReportSystem().isReportIDRegister(id)){
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast bereits Report erstellt."));
                    }else{
                        Core.getCore().getReportSystem().registerReport(player, reported, "unbearbeitet", "Hacking");

                        player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix() + "§7Du hast den Spieler §6" + reported.getName()).append(" §7erfolgreich für Hacking §7Reportet.").create());
                        Core.getCore().sendReportToDiscord("> :scroll: Neuer Report :scroll: \n\n> Spieler : " + reported.getName() + "\n> Reporter : " + player.getName() + "\n> Grund : " + Core.getCore().getReportSystem().getSetting(Core.getCore().getReportSystem().getReportID(reported,"Teaming"), "Grund") + "\n> ID : " + Core.getCore().getReportSystem().getReportID(reported, "Teaming"));
                    }
                }else if (args[1].equalsIgnoreCase("wortwahl")){
                    int id = Core.getCore().getReportSystem().getReportID(reported, "Teaming");
                    if(Core.getCore().getReportSystem().isReportIDRegister(id)){
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast bereits Report erstellt."));
                    }else{
                        Core.getCore().getReportSystem().registerReport(player, reported, "unbearbeitet", "Wortwahl");

                        player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix() + "§7Du hast den Spieler §6" + reported.getName()).append(" §7erfolgreich für seine Wortwahl §7Reportet.").create());
                        Core.getCore().sendReportToDiscord("> :scroll: Neuer Report :scroll: \n\n> Spieler : " + reported.getName() + "\n> Reporter : " + player.getName() + "\n> Grund : " + Core.getCore().getReportSystem().getSetting(Core.getCore().getReportSystem().getReportID(reported,"Teaming"), "Grund") + "\n> ID : " + Core.getCore().getReportSystem().getReportID(reported, "Teaming"));
                    }

                }else if (args[1].equalsIgnoreCase("werbung")){
                    int id = Core.getCore().getReportSystem().getReportID(reported, "Teaming");
                    if(Core.getCore().getReportSystem().isReportIDRegister(id)){
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast bereits Report erstellt."));
                    }else {
                        Core.getCore().getReportSystem().registerReport(player, reported, "unbearbeitet", "Werbung");
                        player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix() + "§7Du hast den Spieler §6" + reported.getName()).append(" §7erfolgreich für Werbung §7Reportet.").create());

                        Core.getCore().sendReportToDiscord("> :scroll: Neuer Report :scroll: \n\n> Spieler : " + reported.getName() + "\n> Reporter : " + player.getName() + "\n> Grund : " + Core.getCore().getReportSystem().getSetting(Core.getCore().getReportSystem().getReportID(reported,"Teaming"), "Grund") + "\n> ID : " + Core.getCore().getReportSystem().getReportID(reported, "Teaming"));
                    }

                }else if (args[1].equalsIgnoreCase("spam")){
                    int id = Core.getCore().getReportSystem().getReportID(reported, "Teaming");
                    if(Core.getCore().getReportSystem().isReportIDRegister(id)){
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast bereits Report erstellt."));
                    }else {
                        Core.getCore().getReportSystem().registerReport(player, reported, "unbearbeitet", "Spamming");
                        player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix() + "§7Du hast den Spieler §6" + reported.getName()).append(" §7erfolgreich für Spamming §7Reportet.").create());

                        Core.getCore().sendReportToDiscord("> :scroll: Neuer Report :scroll: \n\n> Spieler : " + reported.getName() + "\n> Reporter : " + player.getName() + "\n> Grund : " + Core.getCore().getReportSystem().getSetting(Core.getCore().getReportSystem().getReportID(reported,"Teaming"), "Grund") + "\n> ID : " + Core.getCore().getReportSystem().getReportID(reported, "Teaming"));                    }

                }



                if(player.hasPermission("report.logged")){
                    if(args[0].equalsIgnoreCase("info")){
                        int reportID = Integer.parseInt(args[1]);
                        if(Core.getCore().getReportSystem().isReportIDRegister(reportID)){
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7---------------------"));
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Gemldeter: §6" + Core.getCore().getReportSystem().getSetting(reportID, "Gemeldeter")));
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Ersteller: §6" + Core.getCore().getReportSystem().getSetting(reportID, "Ersteller")));
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Grund: §6" + Core.getCore().getReportSystem().getSetting(reportID, "Grund")));
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7ReportID: §6" + reportID));
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7---------------------"));
                        }else{
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Dieser ReportID existiert nicht oder wurde schon bearbeitet."));
                        }

                    }else if(args[0].equalsIgnoreCase("jump")){
                        int reportID = Integer.parseInt(args[1]);
                        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(Core.getCore().getReportSystem().getSetting(reportID, "Gemeldeter"));
                        if(target.isConnected()){
                            if(Core.getCore().getReportSystem().getEditor(reportID).equalsIgnoreCase("Niemand")){
                                player.connect(target.getServer().getInfo());
                                player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du bearbeitest nun den Report von §6" + Core.getCore().getReportSystem().getSetting(reportID, "Ersteller")));
                                Core.getCore().getReportSystem().setEditor(player.getName(), reportID);
                            }else{
                                player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Dieser Report wird bereits von dem Spieler §6" + Core.getCore().getReportSystem().getSetting(reportID, "Bearbeiter") + " §7Bearbeitet."));
                            }

                        }else{
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Diese ReportID wurde nicht gefunden oder der Spieler ist nicht Online."));
                        }
                    }else if(args[0].equalsIgnoreCase("edit")){
                        int reportID = Integer.parseInt(args[1]);
                        if(Core.getCore().getReportSystem().isReportIDRegister(reportID)){
                            if(Core.getCore().getReportSystem().getEditor(reportID).equalsIgnoreCase(player.getName())){
                                TextComponent accept = new TextComponent("§a[Akzeptieren] ");
                                accept.setBold(true);
                                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report accept " + reportID));
                                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aKlicke hier um den Report zu Akzeptieren").create()));
                                TextComponent decline = new TextComponent("§c[Ablehnen]");
                                decline.setBold(true);
                                decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report deny " + reportID));
                                decline.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cKlicke hier um den Report Abzulehnen").create()));
                                TextComponent delete = new TextComponent("§7[Löschen] ");
                                delete.setBold(true);
                                delete.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report delete " + reportID));
                                delete.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Klicke Hier um den Report zu Löschen").create()));
                                ComponentBuilder builder = new ComponentBuilder();
                                player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix()).append(accept).append(delete).append(decline).create());


                            }
                            if(Core.getCore().getReportSystem().getEditor(reportID).equalsIgnoreCase("Niemand")){
                                TextComponent accept = new TextComponent("§a[Akzeptieren] ");
                                accept.setBold(true);
                                accept.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report accept " + reportID));
                                accept.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aKlicke hier um den Report zu Akzeptieren").create()));
                                TextComponent decline = new TextComponent("§c[Ablehnen]");
                                decline.setBold(true);
                                decline.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report deny " + reportID));
                                decline.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cKlicke hier um den Report Abzulehnen").create()));
                                TextComponent delete = new TextComponent("§7[Löschen] ");
                                delete.setBold(true);
                                delete.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/report delete " + reportID));
                                delete.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6Klicke Hier um den Report zu Löschen").create()));
                                player.sendMessage(new ComponentBuilder(Core.getCore().getPrefix()).append(accept).append(delete).append(decline).create());
                            }
                        }else{
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Diese ReportID wurde nicht gefunden."));
                        }
                    }else if(args[0].equalsIgnoreCase("delete")){
                        int reportID = Integer.parseInt(args[1]);
                        if(Core.getCore().getReportSystem().isReportIDRegister(reportID)){
                            Core.getCore().getReportSystem().removeReport(reportID);
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast Den Report erfolgreich gelöscht"));
                        }else{
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Diese ReportID wurde nicht gefunden."));
                        }
                    }else if(args[0].equalsIgnoreCase("deny")){
                        int reportID = Integer.parseInt(args[1]);
                        if(Core.getCore().getReportSystem().isReportIDRegister(reportID)){

                            Core.getCore().getReportSystem().setStatus(reportID, "abgelehnt");
                            ProxyServer.getInstance().getScheduler().schedule(Core.getCore().getMain(), new Runnable() {
                                @Override
                                public void run() {
                                    Core.getCore().getReportSystem().removeReport( reportID);
                                }

                            }, 1, TimeUnit.HOURS);
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast den Report erfolgreich abgelehnt."));
                        }else{
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Diese ReportID wurde nicht gefunden."));
                        }


                    }else if(args[0].equalsIgnoreCase("accept")){
                        int reportID = Integer.parseInt(args[1]);
                        if(Core.getCore().getReportSystem().isReportIDRegister(reportID)){

                            Core.getCore().getReportSystem().setStatus(reportID, "angenommen");
                            Core.getCore().getReportSystem().setEditor(player.getName(), reportID);
                            ProxyServer.getInstance().getScheduler().schedule(Core.getCore().getMain(), new Runnable() {
                                @Override
                                public void run() {
                                    Core.getCore().getReportSystem().removeReport(reportID);
                                }

                            }, 1, TimeUnit.HOURS);
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast den Report erfolgreich angenommen."));
                        }else{
                            player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Diese ReportID wurde nicht gefunden."));
                        }


                    }
                }else{
                    player.sendMessage(new TextComponent(Core.getCore().getPrefix()+ "§7Dazu hast du keine Rechte oder du bist nicht eingelogt."));
                }

            }

            if(args.length == 0){
                player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Syntax: §6/report <Spieler> <Teaming|Hacking|Spam|Wortwahl|Werbung>"));
            }
            if(args.length == 1){


                if(args[0].equalsIgnoreCase("login")){
                    if(player.hasPermission("report.logged")){
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du bist bereits eingelogt."));
                    }else{
                        Core.getCore().addPermission(user, "report.logged");
                        Core.getCore().addPermission(user, "games.ignore");
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§aDu hast dich erfolgreich eingelogt."));
                    }

                }else if(args[0].equalsIgnoreCase("logout")){
                    if(player.hasPermission("report.logged")){
                        Core.getCore().removePermission(user, "report.logged");
                        Core.getCore().removePermission(user, "games.ignore");
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du hast dich erfolreich §causgelogt."));
                    }else {
                        player.sendMessage(new TextComponent(Core.getCore().getPrefix() + "§7Du bist nicht eingelogt."));
                    }
                }


            }

        }else{
            sender.sendMessage(new TextComponent("Du musst ein Spieler sein"));

        }

    }
}
