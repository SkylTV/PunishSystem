package de.skyl.punish.utils;
// Coded By SkylTV //
// Copyright SkylTV //

import de.skyl.punish.main.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
            PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS "+banTable+" (Ersteller VARCHAR(100), Gebannter VARCHAR(100), UUID VARCHAR(100), BanID INT(100), Grund VARCHAR(100), Ende VARCHAR(100))");
            ps.executeUpdate();
        }catch(Exception ex){

        }
    }

    public void registerBan(ProxiedPlayer writer, ProxiedPlayer bannedPlayer, String reason, int seconds){
        try{
            long current = System.currentTimeMillis();
            long millis = seconds*1000;
            long  end = current+millis;
            PreparedStatement ps = MySQL.getStatement("INSERT INTO "+banTable+"(Ersteller, Gebannter, UUID, BanID, Grund, Ende) VALUES(?, ?, ?, ?, ?, ?)");
            ps.setString(1, writer.getName());
            ps.setString(2, bannedPlayer.getName());
            ps.setString(3, bannedPlayer.getUniqueId().toString());
            Random random = new Random();
            int upperbound = 1000;
            int int_random = random.nextInt(upperbound);
            int upper = 5000;
            int newRandom = random.nextInt(upper);
            ps.setInt(4, int_random);
            ps.setString(5, reason);
            ps.setLong(6, end);
            ps.executeLargeUpdate();
            ps.close();
        }catch(Exception e){
            e.printStackTrace();
        }


    }

    public Long getEnd(String uuid) {
        PreparedStatement ps = MySQL.getStatement("SELECT * FROM " + banTable + " WHERE UUID= " + uuid);
        try {
            ResultSet rs = ps.executeQuery();
                while (rs.next()){
                    return  rs.getLong("Ende");
                }
            } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
