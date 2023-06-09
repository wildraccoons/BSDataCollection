package frc.teamdata;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import javafx.scene.control.TableView;


public class SQLManager {
    HashMap<String, Integer> entries;
    private StringBuffer TeamName = new StringBuffer();
    public SQLManager (String TeamName) throws ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.TeamName.replace(0, this.TeamName.length(), TeamName);  
    }

    public SQLManager() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        entries = getEntries(conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TeamData"));
        conn.close();
    }

    public void updateScores(String DriveTrain, double AvgAuto, double AvgDefense, double AvgMobility, double AvgOffense, double AvgTotal, boolean hasWon) throws SQLException {
        boolean exists = false;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TeamData");
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
        entries = getEntries(result);
        conn.close();
    }

    public void updateFXTable(TableView<Team> teamdata, int i, String rankType) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TeamData");
        HashMap<String, String> ranks = rankTeams(result, rankType, i);
        while(result.next()) {
            teamdata.getItems().add(new Team(result.getString("TeamNumber"), result.getString("DriveTrain"), ScoreCalculator.round(result.getDouble("Auto"), i), ScoreCalculator.round(result.getDouble("Offense"), i), ScoreCalculator.round(result.getDouble("Defense"), i), ScoreCalculator.round(result.getDouble("Mobility"), i), ScoreCalculator.round(result.getDouble("Total"), i), result.getInt("WinStreak"), ranks.get(result.getString("TeamNumber"))));
        }
        entries = getEntries(result);
        conn.close();
    }


    public void updateFXTable(TableView<Team> teamdata, String rankType) throws SQLException {
        this.updateFXTable(teamdata, 3, rankType);
    }

    public void filterRows(TableView<Team> teamdata, String filter, int i, String rankType) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TeamData");
        HashMap<String, String> ranks = rankTeams(result, rankType, i);
        char[] charFilter = filter.toCharArray();
        while(result.next()) {
            char[] teamName = result.getString("TeamNumber").toCharArray(), driveTrain = result.getString("driveTrain").toCharArray(), Auto = String.valueOf(result.getDouble("Auto")).toCharArray(), Offense = String.valueOf(result.getDouble("Offense")).toCharArray(), Defense = String.valueOf(result.getDouble("Defense")).toCharArray(), Mobility = String.valueOf(result.getInt("Mobility")).toCharArray(), Total = String.valueOf(result.getDouble("Total")).toCharArray(), WinStreak = String.valueOf(result.getInt("WinStreak")).toCharArray();
            if (matchChar(teamName, charFilter) || matchChar(driveTrain, charFilter) || matchChar(Auto, charFilter) || matchChar(Offense, charFilter) || matchChar(Defense, charFilter) || matchChar(Mobility, charFilter) || matchChar(Total, charFilter) || matchChar(WinStreak, charFilter)) {
                teamdata.getItems().add(new Team(result.getString("TeamNumber"), result.getString("DriveTrain"), ScoreCalculator.round(result.getDouble("Auto"), i), ScoreCalculator.round(result.getDouble("Offense"), i), ScoreCalculator.round(result.getDouble("Defense"), i), ScoreCalculator.round(result.getDouble("Mobility"), i), ScoreCalculator.round(result.getDouble("Total"), i), result.getInt("WinStreak"), ranks.get(result.getString("TeamNumber"))));
            }
        }

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

    public boolean checkForUpdates() throws SQLException {
        boolean updated = false;
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
        HashMap<String, Integer> newEntries = getEntries(conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM TeamData"));
        if (!(entries.equals(newEntries))){
            entries = newEntries;
            updated = true;
        }
        conn.close();
        return updated;
    }



    // support methods
    private HashMap<String, Integer> getEntries(ResultSet result) throws SQLException {
        HashMap<String, Integer> entries = new HashMap<>();
        result.beforeFirst();
        while(result.next()) {
            entries.put(result.getString("TeamNumber"), result.getInt("Entries"));
        }
        return entries;
        
    }

    private boolean matchChar(char[] chars, char[] comparisonChars) {
        boolean matchFound = false;
        ArrayList<Character> listChars = new ArrayList<>();
          ArrayList<Character> listComparisonChars = new ArrayList<>();
        for (int i = 0; i <= (chars.length - comparisonChars.length); i++) {
          for (int n = 0; n < comparisonChars.length; n++) {
              listComparisonChars.add(comparisonChars[n]);
              listChars.add(chars[n+i]);
          }
          if (listChars.equals(listComparisonChars)) {
              matchFound = true;
              break;
          }
          listComparisonChars.clear();
          listChars.clear();
        }
        return matchFound;  
      }


    private static Map<String, Double> sortByValue(Map<String, Double> unsortMap)
    {
        List<Entry<String, Double>> list = new LinkedList<>(unsortMap.entrySet());

        // Sorting the list based on values
        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        return list.stream().collect(Collectors.toMap(Entry::getKey, Entry::getValue, (a, b) -> b, LinkedHashMap::new));

    }

    
    private static HashMap<String, String> rankTeams (ResultSet result, String rankType, int i) throws SQLException {
        Map<String, Double> sorter = new HashMap<String,Double>();
        while(result.next()) {
            sorter.put(result.getString("TeamNumber"), result.getDouble(rankType));
        }
        result.beforeFirst();
        sorter = sortByValue(sorter);
        HashMap<String, String> ranks = new HashMap<>();
        int iteration = 0;
        double previousStat = 0;
        for (String key: sorter.keySet()) {
            if (ScoreCalculator.round(previousStat, i) == ScoreCalculator.round(sorter.get(key), i)) {
                ranks.put(key, (String.valueOf(iteration) + " (Tied)"));
            } else {
                iteration++;
                ranks.put(key, (String.valueOf(iteration)));
            }
            System.out.println(iteration);
            previousStat = sorter.get(key);
        }
        return ranks;
    }

}