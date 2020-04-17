package eplrankingsystem;


import com.sun.org.apache.bcel.internal.generic.ARETURN;
import com.sun.org.apache.bcel.internal.generic.DRETURN;
import eplbasicinfo.Match;
import eplbasicinfo.Team;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * @author Sichen & Xianling
 */
public class Rank {
    public static double SelectTeam(Team homeTeam, Team awayTeam){
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
        if(varHomeGoal != 0 && varAwayGoal != 0){
            NormalDistribution norHome = new NormalDistribution(meanHomeGoal, varHomeGoal);
            NormalDistribution norAway = new NormalDistribution(meanAwayGoal, varAwayGoal);
            int times = 0;
            int numOfWin = 0;
            int simuHomeGoal = -1;
            int simuAwayGoal = -1;
            int round = 20000;
            while(times < round){
                while(simuHomeGoal < 0){
                    simuHomeGoal = Math.round((float)norHome.sample());
                }
                while(simuAwayGoal < 0){
                    simuAwayGoal = Math.round((float)norAway.sample());
                }
                if (simuHomeGoal > simuAwayGoal) {
                    numOfWin ++;
                }
                times ++;
            }
            return numOfWin / round;
        }
        return -1;
    }

    public static Match predict(Match match){
        double homeMean = Calculate.getHomeMean(match.getHome(), match.getAway());
        double awayMean = Calculate.getAwayMean(match.getHome(), match.getAway());
        double homeVar = Calculate.getHomeVar(match.getHome(), match.getAway());
        double awayVar = Calculate.getAwayVar(match.getHome(), match.getAway());

        if(homeVar != 0 && awayVar != 0){
            NormalDistribution homeNd = new NormalDistribution(homeMean, homeVar);
            NormalDistribution awayNd = new NormalDistribution(awayMean, awayVar);
            int homeGoal = Math.round((float) homeNd.getMean());
            int awayGoal = Math.round((float) awayNd.getMean());
            char result;
            if(homeGoal > awayGoal){
                result = 'H';
                match.getHome().setWin(match.getHome().getWin() + 1);
                match.getAway().setLose(match.getAway().getLose() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 3);
            }else if(homeGoal < awayGoal){
                result = 'A';
                match.getHome().setLose(match.getHome().getLose() + 1);
                match.getAway().setWin(match.getAway().getWin() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 3);
            }else{
                result = 'D';
                match.getHome().setDraw(match.getHome().getDraw() + 1);
                match.getAway().setDraw(match.getAway().getDraw() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 1);
            }
            match.setFTR(result);
            match.setFTHG(homeGoal);
            match.setFTAG(awayGoal);
        }
        return match;
    }
}
