package eplbasicinfo;

/**
 * @author Sichen & Xianling
 */

public class Match {
    private Team home;  //Home Team
    private Team away;  //Away Team
    private int FTHG;   //FTHG: Full Time Home Team Goals
    private int FTAG;   //FTAG: Full Time Away Team Goals
    private char FTR;   //FTR: Full Time Result (H=Home Win, D=Draw, A=Away Win)

    public Match() {

    }

    public Match(Team home, Team away, int FTHG, int FTAG, char FTR) {
        this.home = home;
        this.away = away;
        this.FTHG = FTHG;
        this.FTAG = FTAG;
        this.FTR = FTR;
    }

    public Team getHome() {
        return home;
    }

    public void setHome(Team home) {
        this.home = home;
    }

    public Team getAway() {
        return away;
    }

    public void setAway(Team away) {
        this.away = away;
    }

    public int getFTHG() {
        return FTHG;
    }

    public void setFTHG(int FTHG) {
        this.FTHG = FTHG;
    }

    public int getFTAG() {
        return FTAG;
    }

    public void setFTAG(int FTAG) {
        this.FTAG = FTAG;
    }

    public char getFTR() {
        return FTR;
    }

    public void setFTR(char FTR) {
        this.FTR = FTR;
    }
}
