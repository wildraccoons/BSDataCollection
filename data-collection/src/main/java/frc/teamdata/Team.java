package frc.teamdata;

public class Team {
    String TeamNumber, DriveTrain, Rank;
    double Auto, Offense, Defense, Mobility, Total;
    int WinStreak;
    public Team(String TeamNumber, String DriveTrain, double Auto, double Offense, double Defense, double Mobility, double Total, int WinStreak, String Rank) {
        this.TeamNumber = TeamNumber;
        this.DriveTrain = DriveTrain;
        this.Auto = Auto;
        this.Offense = Offense;
        this.Defense = Defense;
        this.Mobility = Mobility;
        this.Total = Total;
        this.WinStreak = WinStreak;
        this.Rank = Rank;
    }

    public String getTeamNumber() {
        return TeamNumber;
    }

    public void setTeamNumber(String TeamNumber) {
        this.TeamNumber.replace(this.TeamNumber, TeamNumber);
    }

    public String getDriveTrain() {
        return DriveTrain;
    }

    public void setDriveTrain() {
        this.DriveTrain.replace(this.DriveTrain, DriveTrain);
    }
    public double getAuto() {
        return Auto;
    }

    public void setAuto(double Auto) {
        this.Auto = Auto;
    }

    public double getOffense() {
        return Offense;
    }

    public void setOffense(double Offense) {
        this.Offense = Offense;
    }

    public double getDefense() {
        return Defense;
    }

    public void setDefense(double Defense) {
        this.Defense = Defense;
    }

    public double getMobility() {
        return Mobility;
    }

    public void setMobility(double Mobility) {
        this.Mobility = Mobility;
    }

    public double getTotal() {
        return Total;
    }

    public void setTotal(double Total) {
        this.Total = Total;
    }

    public int getWinStreak() {
        return WinStreak;
    }

    public void setWinStreak(int WinStreak) {
        this.WinStreak = WinStreak;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String Rank) {
        this.Rank.replace(this.Rank, Rank);
    }
}
