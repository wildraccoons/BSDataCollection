package frc.teamdata;

import java.sql.*;


import javafx.scene.control.TableView;


public class SQLManager {

    private StringBuffer TeamName = new StringBuffer();
    public SQLManager (String TeamName) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.TeamName.replace(0, this.TeamName.length(), TeamName);  
    }

    public void updateScores(String DriveTrain, double AvgAuto, double AvgDefense, double AvgMobility, double AvgOffense, double AvgTotal, boolean hasWon) throws SQLException {
        boolean exists = false;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM TeamData");
        double prevAuto = 0, prevOffense = 0, prevDefense = 0, prevMobility = 0, prevTotal = 0;
        int WinStreak = 0, Entry = 0;
        if (hasWon) {
            WinStreak = 1;
        }
        while (result.next()){
            if (result.getString("TeamNumber").equals(TeamName.toString())) {
                exists = true;
                prevAuto = result.getDouble("Auto");
                prevOffense = result.getDouble("Offense");
                prevDefense = result.getDouble("Defense");
                prevMobility = result.getDouble("Mobility");
                prevTotal = result.getDouble("Total");
                if (hasWon) {
                WinStreak += result.getInt("WinStreak");
                }
                Entry = result.getInt("Entries");
            }
        }

        if (!exists) {
            PreparedStatement sendData = conn.prepareStatement("INSERT INTO TeamData (TeamNumber, DriveTrain, Auto, Offense, Defense, Mobility, Total, WinStreak, Entries) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sendData.setString(1, TeamName.toString());
            sendData.setString(2, DriveTrain);
            sendData.setDouble(3, AvgAuto);
            sendData.setDouble(4, AvgOffense);
            sendData.setDouble(5, AvgDefense);
            sendData.setDouble(6, AvgMobility);
            sendData.setDouble(7, AvgTotal);
            sendData.setInt(8, WinStreak);
            sendData.setInt(9, 1);
            sendData.executeUpdate();
        } else if (exists) {
            AvgAuto = ((prevAuto * Entry) + AvgAuto)/(Entry + 1);
            AvgOffense = ((prevOffense * Entry) + AvgOffense)/(Entry + 1);
            AvgDefense = ((prevDefense * Entry) + AvgDefense)/(Entry + 1);
            AvgMobility = ((prevMobility * Entry) + AvgMobility)/(Entry + 1);
            AvgTotal = ((prevTotal * Entry) + AvgTotal)/(Entry + 1);
            if (WinStreak >= 10) {
                AvgTotal += 3;
            } else if (WinStreak >= 5) {
                AvgTotal += 2;
            }
            PreparedStatement updateData = conn.prepareStatement("UPDATE TeamData SET Auto = ?, Offense = ?, Defense = ?, Mobility = ?, Total = ?, Winstreak = ?, Entries = ? WHERE TeamNumber = ?");
            updateData.setDouble(1, AvgAuto);
            updateData.setDouble(2, AvgOffense);
            updateData.setDouble(3, AvgDefense);
            updateData.setDouble(4, AvgMobility);
            updateData.setDouble(5, AvgTotal);
            updateData.setInt(6, WinStreak);
            updateData.setInt(7, (Entry + 1));
            updateData.setString(8, TeamName.toString());
            updateData.executeUpdate();
        }
        conn.close();
    }

    public void updateFXTable(TableView<Team> teamdata) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        ResultSet result = conn.createStatement().executeQuery("SELECT * FROM TeamData");
        while(result.next()) {
            teamdata.getItems().add(new Team(result.getString("TeamNumber"), result.getString("DriveTrain"), result.getDouble("Auto"), result.getDouble("Offense"), result.getDouble("Defense"), result.getDouble("Mobility"), result.getDouble("Total"), result.getInt("WinStreak")));
        }
        conn.close();
    }

    public void clearAllData() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        conn.createStatement().executeUpdate("DELETE FROM TeamData");
        conn.close();
    }

    public void clearSelectedData(Object[] teams) throws SQLException{
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");

        for (Object a : teams) {
            PreparedStatement deleteData = conn.prepareStatement("DELETE FROM TeamData WHERE TeamNumber = ?");
            deleteData.setString(1, ((Team) a).getTeamNumber());
            deleteData.executeUpdate();
        }

        conn.close();
    }
}