package eplrankingsystem;

import eplbasicinfo.Match;
import eplbasicinfo.MatchDirectory;
import eplbasicinfo.Team;
import eplbasicinfo.TeamDirectory;

/**
 * @author Sichen & Xianling
 */
public class AddData {
    public static TeamDirectory teamDirectory;
    public static MatchDirectory matchDirectory;
    private ReadCsvFile readTeam;
    private ReadCsvFile readMatch;

    public AddData() throws Exception {
        teamDirectory = new TeamDirectory();
        matchDirectory = new MatchDirectory();
        this.readMatch = new ReadCsvFile("data/3YearsMatch.csv");
        this.readTeam = new ReadCsvFile("data/TeamInfo.csv");
    }

    public void addData() throws Exception {
        String[] getTeamNextLine= readTeam.getNextRow();
        String[] getMatchNextLine = readMatch.getNextRow();

        //Add Team info into TeamDirectory
        while (getTeamNextLine != null){
            String name = getTeamNextLine[0];
            int win = Integer.parseInt(getTeamNextLine[1]);
            int lose = Integer.parseInt(getTeamNextLine[3]);
            int draw = Integer.parseInt(getTeamNextLine[2]);
            int rankPoint = Integer.parseInt(getTeamNextLine[5]);
            Team team = new Team(name, lose, draw, rankPoint);
            teamDirectory.getTeamDirectory().add(team);
        }
        //Add Match info into MatchDirectory
        while(getMatchNextLine != null){
            Team homeTeam = new Team();
            Team awayTeam = new Team();
            for (Team team : teamDirectory.getTeamDirectory()) {
                if(team.getTeamName().equals(getMatchNextLine[1])){
                    homeTeam = team;
                }
                if(team.getTeamName().equals(getMatchNextLine[2])){
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
        }

    }


    public static void main(String[] args) throws Exception {
        AddData add = new AddData();
        add.addData();
        System.out.println(Rank.SelectTeam(teamDirectory.getTeamDirectory().get(0),teamDirectory.getTeamDirectory().get(1)));

    }
//    //Test ReadCsvFile function
//    public static void main(String[] args) throws Exception {
//        ReadCsvFile rd = new ReadCsvFile("data/Team.csv");
//        for (String s : rd.getCsvHeader()) {
//            System.out.println(s);
//        }
//    }
}
