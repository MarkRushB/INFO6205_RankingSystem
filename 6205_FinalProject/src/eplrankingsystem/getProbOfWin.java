package eplrankingsystem;


import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.bcel.internal.generic.DRETURN;
import eplbasicinfo.Match;
import eplbasicinfo.Team;
import eplbasicinfo.TeamDirectory;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @author Sichen & Xianling
 */
public class getProbOfWin {
    //generate probability of Wins
    public static double getProbOfWin(Team homeTeam, Team awayTeam) {
        double meanHomeGoal;
        double meanAwayGoal;
        double varHomeGoal;
        double varAwayGoal;
        meanHomeGoal = Calculate.getHomeMean(homeTeam, awayTeam);
        meanAwayGoal = Calculate.getAwayMean(homeTeam, awayTeam);
        varHomeGoal = Calculate.getHomeVar(homeTeam, awayTeam);
        varAwayGoal = Calculate.getAwayVar(homeTeam, awayTeam);


        //Use Apache Commons Math 3.6.1 (requires Java 1.5+), downloaded from http://commons.apache.org/proper/commons-math/download_math.cgi
        //All API we used could search on this official document http://commons.apache.org/proper/commons-math/javadocs/api-3.6.1/index.html?overview-summary.html
        //------------------------------------------------------------------------------------------------------------------
        //after we tried so many times, we found that there is a case where the variance of goals of some teams is 0
        //under that circumstance, NormalDistribution could not work as well, so we consider 4 cases
        if (varHomeGoal != 0 && varAwayGoal != 0) {
            NormalDistribution norHome = new NormalDistribution(meanHomeGoal, varHomeGoal);
            NormalDistribution norAway = new NormalDistribution(meanAwayGoal, varAwayGoal);
            int times = 0;
            int numOfWin = 0;
            double round = 20000.0;
            while (times < round) {
                int simuHomeGoal = -1;
                int simuAwayGoal = -1;
                while (simuHomeGoal < 0) {
                    simuHomeGoal = Math.round((float) norHome.sample());
                }
                while (simuAwayGoal < 0) {
                    simuAwayGoal = Math.round((float) norAway.sample());
                }
                if (simuHomeGoal > simuAwayGoal) {
                    numOfWin++;
                }
                times++;
            }
            return numOfWin / round;
        } else if (varHomeGoal != 0 && varAwayGoal == 0) {
            NormalDistribution norHome = new NormalDistribution(meanHomeGoal, varHomeGoal);
            int times = 0;
            int numOfWin = 0;
            double round = 20000.0;
            while (times < round) {
                int simuHomeGoal = -1;
                int simuAwayGoal = Math.round((float) meanAwayGoal);
                while (simuHomeGoal < 0) {
                    simuHomeGoal = Math.round((float) norHome.sample());
                }
                if (simuHomeGoal > simuAwayGoal) {
                    numOfWin++;
                }
                times++;
            }
            return numOfWin / round;
        } else if (varHomeGoal == 0 && varAwayGoal != 0) {
            NormalDistribution norAway = new NormalDistribution(meanAwayGoal, varAwayGoal);
            int times = 0;
            int numOfWin = 0;
            double round = 20000.0;
            while (times < round) {
                int simuAwayGoal = -1;
                int simuHomeGoal = Math.round((float) meanHomeGoal);
                while (simuAwayGoal < 0) {
                    simuAwayGoal = Math.round((float) norAway.sample());
                }
                if (simuHomeGoal > simuAwayGoal) {
                    numOfWin++;
                }
                times++;
            }
            return numOfWin / round;
        } else {
            int simuHomeGoal = Math.round((float) meanHomeGoal);
            int simuAwayGoal = Math.round((float) meanAwayGoal);
            if (simuHomeGoal > simuAwayGoal) {
                return 1;
            } else if (simuHomeGoal < simuAwayGoal) {
                return 0;
            } else {
                return 0.5;
            }
        }
    }

    //print result
    public static void getProbResult(TeamDirectory td){
        System.out.println("-------------------Probability of home team win!----------------------");
        for (Team hometeam : td.getTeamDirectory()) {
            String homeTeamName = "";
            for (Team awayteam : td.getTeamDirectory()) {
                if(hometeam != awayteam){
                    homeTeamName = getProbOfWin.getProbOfWin(hometeam, awayteam) + "";
                    System.out.println(hometeam.getTeamName() + " | " + awayteam.getTeamName() +" | Probability:"+ homeTeamName);
                }
            }
        }
    }

}
