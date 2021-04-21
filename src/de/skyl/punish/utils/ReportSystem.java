package de.skyl.punish.utils;
// Coded By SkylTV //
// Copyright SkylTV //



import de.skyl.punish.main.Main;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ReportSystem {
    private Main main;
    public static ReportSystem reportSystem;
    private String reportTable;
    public ReportSystem(Main main){
        this.main = main;
        reportSystem = this;
        reportTable = "reporttable";
    }

    public void createTable(){
        try{
            PreparedStatement ps = MySQL.getStatement("CREATE TABLE IF NOT EXISTS "+reportTable+" (Ersteller VARCHAR(100), Gemeldeter VARCHAR(100), Bearbeiter VARCHAR(100), UUID VARCHAR(100), ReportID INT(100), Grund VARCHAR(100), Status VARCHAR(100))");
            ps.executeUpdate();
        }catch(Exception ex){

        }
    }

    public void registerReport(ProxiedPlayer writer, ProxiedPlayer reportedPlayer, String status, String grund){
        try{
            PreparedStatement ps = MySQL.getStatement("INSERT INTO "+reportTable+"(Ersteller, Gemeldeter, Bearbeiter, UUID, ReportID, Grund, Status) VALUES(?, ?, ?, ?, ?, ?, ?)");
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

    public boolean isReportIDRegister(int ReportID){
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM "+ reportTable +" WHERE ReportID= ?");
            ps.setInt(1, ReportID);
            ResultSet rs = ps.executeQuery();
            boolean user = rs.first();
            rs.close();
            ps.close();
            return user;
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public String getSetting(int ReportID, String name) {
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM "+reportTable+" WHERE ReportID= ?");
            ps.setInt(1, ReportID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String setting = rs.getString(name);
            rs.close();
            ps.close();
            return setting;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "";
    }
    public int getIntSetting(int ReportID, String name) {
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM "+reportTable+" WHERE ReportID= ?");
            ps.setInt(1, ReportID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int setting = rs.getInt(name);
            rs.close();
            ps.close();
            return setting;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getReportID(ProxiedPlayer reportedPlayer, String Reason){
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM "+reportTable+" WHERE UUID AND Grund= ?,?");
            ps.setString(1, reportedPlayer.getUniqueId().toString());
            ps.setString(2, Reason);
            ResultSet rs = ps.executeQuery();
            rs.next();
            int setting = rs.getInt("ReportID");
            rs.close();
            ps.close();
            return setting;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    public boolean isReportIdNull(int ReportID){
        if(ReportID == -1){
            return false;
        }
        return true;
    }

    public String getWriter(int reportID){
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM "+reportTable+" WHERE ReportID= ?");
            ps.setInt(1, reportID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String setting = rs.getString("Ersteller");
            rs.close();
            ps.close();
            return setting;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return " ";
    }

    public String getEditor(int reportID){
        try {
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM "+reportTable+" WHERE ReportID= ?");
            ps.setInt(1, reportID);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String setting = rs.getString("Bearbeiter");
            rs.close();
            ps.close();
            return setting;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return " ";
    }


    public void setEditor(String editor, int reportID){
        try {
            PreparedStatement ps = MySQL.getStatement("UPDATE "+reportTable+" SET Bearbeiter= ? WHERE ReportID= ?");
            ps.setString(1, editor);
            ps.setInt(2, reportID);
            ps.executeLargeUpdate();
            ps.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void setStatus(int reportID, String status){
        try {
            PreparedStatement ps = MySQL.getStatement("UPDATE "+reportTable+" SET Status= ? WHERE ReportID= ?");
            ps.setString(1, status);
            ps.setInt(2, reportID);
            ps.executeLargeUpdate();
            ps.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void removeReport(int ReportID){
        try{
            PreparedStatement ps = MySQL.getStatement("DELETE FROM " + reportTable + " WHERE ReportID= " + ReportID);
            ps.executeLargeUpdate();
            ps.close();



        }catch (Exception e){

        }



    }


    public List<String> getReportedPlayers(){
        List<String> list = new ArrayList<>();
        try{
            PreparedStatement ps = MySQL.getStatement("SELECT * FROM " + reportTable);
            ResultSet rs = ps.executeQuery();
            try{
                while(rs.next()){
                    list.add(rs.getString("Gemeldeter"));
                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (Exception ex){

        }
            return list;

    }






}

