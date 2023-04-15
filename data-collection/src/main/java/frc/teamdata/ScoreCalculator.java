package frc.teamdata;

import java.sql.SQLException;

import javafx.scene.control.CheckBox;
public class ScoreCalculator {
    
    public void evaluateScore(String TeamName, String DriveTrain, boolean Won, CheckBox z1, CheckBox z2, CheckBox z3, CheckBox z4, CheckBox z5, CheckBox z6, CheckBox z7, CheckBox z8, CheckBox z9, CheckBox z10, CheckBox z11, CheckBox z12, CheckBox z13, CheckBox z14, CheckBox z15, CheckBox z16, CheckBox z17, CheckBox z18, CheckBox z19, CheckBox z20, CheckBox z21, CheckBox z22, CheckBox z23, CheckBox z24, CheckBox z25, CheckBox died) throws ClassNotFoundException, SQLException {
        int Defense = 0, Offense =0 , Mobility = 0, Auto = 0, Total = 0;
        double AvgDefense = 0, AvgOffense = 0, AvgMobility = 0, AvgAuto = 0, AvgTotal = 0;
        SQLManager team = new SQLManager(TeamName);
        z1.setAllowIndeterminate(false);
        z2.setAllowIndeterminate(false);
        z3.setAllowIndeterminate(false);
        z4.setAllowIndeterminate(false);
        z5.setAllowIndeterminate(false);
        z6.setAllowIndeterminate(false);
        z7.setAllowIndeterminate(false);
        z8.setAllowIndeterminate(false);
        z9.setAllowIndeterminate(false);
        z10.setAllowIndeterminate(false);
        z11.setAllowIndeterminate(false);
        z12.setAllowIndeterminate(false);
        z13.setAllowIndeterminate(false);
        z14.setAllowIndeterminate(false);
        z15.setAllowIndeterminate(false);
        z16.setAllowIndeterminate(false);
        z17.setAllowIndeterminate(false);
        z18.setAllowIndeterminate(false);
        z19.setAllowIndeterminate(false);
        z20.setAllowIndeterminate(false);
        z21.setAllowIndeterminate(false);
        z22.setAllowIndeterminate(false);
        z23.setAllowIndeterminate(false);
        z24.setAllowIndeterminate(false);
        z25.setAllowIndeterminate(false);
        died.setAllowIndeterminate(false);



        if (z1.isSelected()) {
          Auto += 3;  
        }

        if (z2.isSelected()) {
            Auto += 2;
        }

        if (z3.isSelected()) {
          Auto += 1;  
        }

        if (z4.isSelected()) {
           Auto += 1; 
        }

        if (z5.isSelected()) {
            Auto += 6;
        }

        if (z6.isSelected()) {
           Auto += 3; 
        }

        if (z7.isSelected()) {
          Auto += 12;  
        }

        if (z8.isSelected()) {
          Auto -= 6;  
        }

        if (z9.isSelected()) {
            Auto -= 3;
        }

        if (z10.isSelected()) {
            Auto -= 1;
        }

        if (z11.isSelected()) {
            Auto = 0;
        }

        if (z12.isSelected()) {
            Mobility -= 3;
        }

        if (z13.isSelected()) {
            Mobility -= 1;
        }

        if (z14.isSelected()) {
            Mobility += 1;
        }

        if (z15.isSelected()) {
           Mobility += 3; 
        }

        if (z16.isSelected()) {
            Offense += 2;
        }

        if (z17.isSelected()) {
            Defense += 3;
        }

        if (z18.isSelected()) {
            Offense += 5;
        }

        if (z19.isSelected()) {
            Defense += 4;
        }

        if (z20.isSelected()) {
          Offense += 3;  
        }

        if (z21.isSelected()) {
            Offense -= 1;
        }

        if (z22.isSelected()) {
            Total += 1;
        }

        if (z23.isSelected()) {
          Defense -= 1;  
        }

        if (z24.isSelected()) {
            Total -= 7;
        }

        if (z25.isSelected()) {
            Total += 1;
        }

        if (died.isSelected()) {
            Defense = 0;
            Mobility = 0;
            Offense = 0;
            Total = -1;
        }

        AvgDefense = ((double) Defense)/4;
        AvgAuto = ((double) Auto)/10;
        AvgMobility = ((double) Mobility)/3;
        AvgOffense = ((double) Offense)/5;
        AvgTotal = (AvgAuto + AvgDefense + AvgMobility + AvgOffense + Total)/4;
        team.updateScores(DriveTrain, AvgAuto, AvgDefense, AvgMobility, AvgOffense, AvgTotal, Won);
    }

    public static double round(double value, int precision) {
        int scale = (int) Math.pow(10, precision);
        return (double) Math.round(value * scale) / scale;
    }
}
