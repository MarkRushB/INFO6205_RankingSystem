package eplrankingsystem;

import eplbasicinfo.Match;
import eplbasicinfo.MatchDirectory;
import eplbasicinfo.Team;
import eplbasicinfo.TeamDirectory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * @author Sichen & Xianling
 */
public class AddData {
    public static TeamDirectory teamDirectory;
    public static MatchDirectory matchDirectory;
    public static ArrayList<Match> unplayed;
    private ReadCsvFile readTeam;
    private ReadCsvFile readMatch;
    private ReadCsvFile readUnplayed;

    public AddData() throws Exception {
        this.teamDirectory = new TeamDirectory();
        this.matchDirectory = new MatchDirectory();
        this.unplayed = new ArrayList<Match>();
        //Read CSV file
        this.readMatch = new ReadCsvFile("data/3YearsMatch.csv");
        this.readTeam = new ReadCsvFile("data/TeamInfo.csv");
        this.readUnplayed = new ReadCsvFile("data/Unmatched.csv");
    }

    public void addData() throws Exception {
        String[] getTeamNextLine= readTeam.getNextRow();
        String[] getMatchNextLine = readMatch.getNextRow();
        String[] getUnplayedNextLine = readUnplayed.getNextRow();

        //Add Team info into TeamDirectory
        while (getTeamNextLine != null){
            String name = getTeamNextLine[0];
            int win = Integer.parseInt(getTeamNextLine[1]);
            int lose = Integer.parseInt(getTeamNextLine[3]);
            int draw = Integer.parseInt(getTeamNextLine[2]);
            int rankPoint = Integer.parseInt(getTeamNextLine[4]);
            Team team = new Team(name, win, lose, draw, rankPoint);
            teamDirectory.getTeamDirectory().add(team);

            getTeamNextLine= readTeam.getNextRow();
        }
        //Add Match info into MatchDirectory
        while(getMatchNextLine != null){
            Team homeTeam = new Team();
            Team awayTeam = new Team();
            for (Team team : teamDirectory.getTeamDirectory()) {
                if(team.getTeamName().equals(getMatchNextLine[0])){
                    homeTeam = team;
                }
                if(team.getTeamName().equals(getMatchNextLine[1])){
                    awayTeam = team;
                }
            }
            char ftr = getMatchNextLine[4].charAt(0);
            int fthg = Integer.parseInt(getMatchNextLine[2]);
            int ftag = Integer.parseInt(getMatchNextLine[3]);
            Match match = new Match(homeTeam,awayTeam,fthg,ftag,ftr);
            matchDirectory.getMatchDirectory().add(match);
            homeTeam.getHomeMatchList().add(match);
            awayTeam.getAwayMatchList().add(match);

            getMatchNextLine = readMatch.getNextRow();
        }
        //Add the rest of Matches unplayed
        while(getUnplayedNextLine != null){
            Team homeTeam = new Team();
            Team awayTeam = new Team();
            for (Team team : teamDirectory.getTeamDirectory()) {
                if (team.getTeamName().equals(getUnplayedNextLine[0])){
                    homeTeam = team;
                }
                if (team.getTeamName().equals(getUnplayedNextLine[1])){
                    awayTeam = team;
                }
            }
            Match match = new Match(homeTeam, awayTeam);
            unplayed.add(match);

            getUnplayedNextLine = readUnplayed.getNextRow();
        }

    }


    public static void main(String[] args) throws Exception {
        AddData add = new AddData();
        add.addData();
        // generate probability of win
        getProbOfWin.getProbResult(teamDirectory);
        // generate Prediction
        getPrediction.getPredResult(unplayed);
        // generate Rank
        getRank.getRank(teamDirectory);
    }
//    //Test ReadCsvFile function
//    public static void main(String[] args) throws Exception {
//        ReadCsvFile rd = new ReadCsvFile("data/Team.csv");
//        for (String s : rd.getCsvHeader()) {
//            System.out.println(s);
//        }
//    }
}
