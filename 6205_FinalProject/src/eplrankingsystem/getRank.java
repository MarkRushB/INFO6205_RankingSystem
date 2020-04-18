package eplrankingsystem;

import eplbasicinfo.Team;
import eplbasicinfo.TeamDirectory;

import java.util.Collections;
import java.util.Comparator;
/**
 * @author Sichen & Xianling
 */
public class getRank {
    //generate rank and print
    public static void getRank(TeamDirectory teamDirectory){
        System.out.println("-------------------Rank----------------------");

        Comparator<Team> rank = new Comparator<Team>() {
            @Override
            public int compare(Team o1, Team o2) {
                if(o1.getRankPoint() > o2.getRankPoint()){
                    return -1;
                }else if(o1.getRankPoint() < o2.getRankPoint()){
                    return 1;
                }
                return 0;
            }
        };
        Collections.sort(teamDirectory.getTeamDirectory() ,rank);
        int n=1;
            for (Team team : teamDirectory.getTeamDirectory()) {
            System.out.println(n + ":" + team.getTeamName() + "|" + team.getWin()+"|"+team.getLose()+"|"+team.getDraw()+"|"+team.getRankPoint());
            n++;
            }
    }
}
