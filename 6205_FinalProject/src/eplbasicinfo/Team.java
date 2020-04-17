package eplbasicinfo;

import java.util.ArrayList;

/**
 * @author Sichen & Xianling
 * This Team Class includes 6 attributes, which are teamName, win, lose, draw, rankPoint, teamMatchList
 */


public class Team {
    private String teamName;
    private int win;
    private int lose;
    private int draw;
    private int rankPoint;
    private ArrayList<Match> teamMatchlist;
    private ArrayList<Match> homeMatchList;
    private ArrayList<Match> awayMatchList;


    public Team(String teamName, int win, int lose, int draw, int rankPoint) {
        this.teamName = teamName;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
        this.rankPoint = rankPoint;
        this.teamMatchlist = new ArrayList<Match>();
        this.homeMatchList = new ArrayList<Match>();
        this.awayMatchList = new ArrayList<Match>();
    }

    public Team(String teamName, int win, int lose, int draw) {
        this.teamName = teamName;
        this.win = win;
        this.lose = lose;
        this.draw = draw;
    }

    public Team(String teamName) {
        this.teamName = teamName;
        this.win = 0;
        this.lose = 0;
        this. draw = 0;
        this.teamMatchlist = new ArrayList<Match>();
    }

    public Team() {
        //need to be finished
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getWin() {
        return win;
    }

    public void setWin(int win) {
        this.win = win;
    }

    public int getLose() {
        return lose;
    }

    public void setLose(int lose) {
        this.lose = lose;
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }

    public int getRankPoint() {
        return rankPoint;
    }

    public void setRankPoint(int rankPoint) {
        this.rankPoint = rankPoint;
    }

    public ArrayList<Match> getTeamMatchlist() {
        return teamMatchlist;
    }

    public void setTeamMatchlist(ArrayList<Match> teamMatchlist) {
        this.teamMatchlist = teamMatchlist;
    }

    public ArrayList<Match> getHomeMatchList() {
        return homeMatchList;
    }

    public void setHomeMatchList(ArrayList<Match> homeMatchList) {
        this.homeMatchList = homeMatchList;
    }

    public ArrayList<Match> getAwayMatchList() {
        return awayMatchList;
    }

    public void setAwayMatchList(ArrayList<Match> awayMatchList) {
        this.awayMatchList = awayMatchList;
    }

    //toSting teamName
    @Override
    public String toString() {
        return teamName;
    }
}
