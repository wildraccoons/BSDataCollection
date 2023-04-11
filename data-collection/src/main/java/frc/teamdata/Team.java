package frc.teamdata;

public class Team {
    String TeamNumber, Auto, Offense, Defense, Mobility, Total, WinStreak;
    public Team(String TeamNumber, String Auto, String Offense, String Defense, String Mobility, String Total, String WinStreak) {
        this.TeamNumber = TeamNumber;
        this.Auto = Auto;
        this.Offense = Offense;
        this.Defense = Defense;
        this.Mobility = Mobility;
        this.Total = Total;
        this.WinStreak = WinStreak;
    }

    public String getTeamNumber() {
        return TeamNumber;
    }

    public void setTeamNumber(String TeamNumber) {
        this.TeamNumber.replace(this.TeamNumber, TeamNumber);
    }

    public String getAuto() {
        return Auto;
    }

    public void setAuto(String Auto) {
        this.Auto.replace(this.Auto, Auto);
    }

    public String getOffense() {
        return Offense;
    }

    public void setOffense(String Offense) {
        this.Offense.replace(this.Offense, Offense);
    }

    public String getDefense() {
        return Defense;
    }

    public void setDefense(String Defense) {
        this.Defense.replace(this.Defense, Defense);
    }

    public String getMobility() {
        return Mobility;
    }

    public void setMobility(String Mobility) {
        this.Mobility.replace(this.Mobility, Mobility);
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String Total) {
        this.Total.replace(this.Total, Total);
    }

    public String getWinStreak() {
        return WinStreak;
    }

    public void setWinStreak(String WinStreak) {
        this.WinStreak.replace(this.WinStreak, WinStreak);
    }
}
