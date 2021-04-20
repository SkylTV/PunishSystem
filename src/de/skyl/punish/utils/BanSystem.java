package de.skyl.punish.utils;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.punish.main.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.util.Random;

public class BanSystem {

    private Main main;
    public static BanSystem banSystem;
    private String banTable;
    public BanSystem(Main main){
        this.main = main;
        banSystem = this;
        banTable = "bantable";
    }

    public void createTable(){
        try{
            PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS "+banTable+" (Ersteller VARCHAR(100), Gemeldeter VARCHAR(100), Bearbeiter VARCHAR(100), UUID VARCHAR(100), ReportID INT(100), Grund VARCHAR(100), Status VARCHAR(100))");
            ps.executeUpdate();
        }catch(Exception ex){

        }
    }

    public void registerBan(ProxiedPlayer writer, ProxiedPlayer reportedPlayer, String status, String grund){
        try{
            PreparedStatement ps = MySQL.getStatement("INSERT INTO "+banTable+"(Ersteller, Gemeldeter, Bearbeiter, UUID, ReportID, Grund, Status) VALUES(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, writer.getName());
            ps.setString(2, reportedPlayer.getName());
            ps.setString(3, "Niemand");
            ps.setString(4, reportedPlayer.getUniqueId().toString());
            Random random = new Random();
            int upperbound = 1000;
            int int_random = random.nextInt(upperbound);
            int upper = 5000;
            int newRandom = random.nextInt(upper);
            ps.setInt(5, int_random);
            ps.setString(6, grund);
            ps.setString(7, status);
            ps.executeLargeUpdate();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

}
