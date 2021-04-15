package de.skyl.punish.utils;
// Coded By SkylTV //
// Copyright SkylTV //
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MySQL {
    public static Connection con;
    public static String host ;
    public static int port ;
    public static String database ;
    public static String username;
    public static String password ;



    public static void connect() {
        if(!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", username, password);
                System.out.println("[PunishSystem] Du wurdest erflogreich mit der Datenbank verbunden");
            } catch (SQLException e) {

                e.printStackTrace();
            }
        }
    }

    public static PreparedStatement getStatement(String sql) {
        if(isConnected()) {
            PreparedStatement ps;
            try {
                ps = con.prepareStatement(sql);
                return ps;
            }catch(SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    public static void disconnect() {
        if(isConnected()) {
            try {
                con.close();
                System.out.println("[PunishSystem] Du wurdest erflogreich mit der Datenbank getrennt");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


    }

    public static boolean isConnected() {
        return (con != null);
    }

}
