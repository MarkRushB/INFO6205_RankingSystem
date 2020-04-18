package eplrankingsystem;

import eplbasicinfo.Match;
import org.apache.commons.math3.distribution.NormalDistribution;

import java.util.ArrayList;
/**
 * @author Sichen & Xianling
 * this class provide predict function and getPredResult function
 */

public class getPrediction {
    //generate predict result
    public static Match predict(Match match){
        double meanHomeGoal = Calculate.getHomeMean(match.getHome(), match.getAway());
        double meanAwayGoal = Calculate.getAwayMean(match.getHome(), match.getAway());
        double varHomeGoal = Calculate.getHomeVar(match.getHome(), match.getAway());
        double varAwayGoal = Calculate.getAwayVar(match.getHome(), match.getAway());
        //after we tried so many times, we found that there is a case where the variance of goals of some teams is 0
        //under that circumstance, NormalDistribution could not work as well, so we consider 4 cases
        if(varHomeGoal != 0 && varAwayGoal != 0){
            NormalDistribution normHome = new NormalDistribution(meanHomeGoal, varHomeGoal);
            NormalDistribution normAway = new NormalDistribution(meanAwayGoal, varAwayGoal);
            int homeGoal = Math.round((float) normHome.getMean());
            int awayGoal = Math.round((float) normAway.getMean());
            char FTR;
            if(homeGoal > awayGoal){
                FTR = 'H';
                match.getHome().setWin(match.getHome().getWin() + 1);
                match.getAway().setLose(match.getAway().getLose() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 3);
            }else if(homeGoal < awayGoal){
                FTR = 'A';
                match.getHome().setLose(match.getHome().getLose() + 1);
                match.getAway().setWin(match.getAway().getWin() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 3);
            }else{
                FTR = 'D';
                match.getHome().setDraw(match.getHome().getDraw() + 1);
                match.getAway().setDraw(match.getAway().getDraw() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 1);
            }
            match.setFTR(FTR);
            match.setFTHG(homeGoal);
            match.setFTAG(awayGoal);
        }else if(varHomeGoal == 0 && varAwayGoal != 0){
            NormalDistribution normAway =new NormalDistribution(meanAwayGoal, varAwayGoal);
            int homeGoal = Math.round((float) meanHomeGoal);
            int awayGoal = Math.round((float) normAway.getMean());
            char FTR;
            if(homeGoal > awayGoal){
                FTR = 'H';
                match.getHome().setWin(match.getHome().getWin() + 1);
                match.getAway().setLose(match.getAway().getLose() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 3);
            }else if(homeGoal < awayGoal){
                FTR = 'A';
                match.getHome().setLose(match.getHome().getLose() + 1);
                match.getAway().setWin(match.getAway().getWin() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 3);
            }else{
                FTR = 'D';
                match.getHome().setDraw(match.getHome().getDraw() + 1);
                match.getAway().setDraw(match.getAway().getDraw() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 1);
            }
            match.setFTR(FTR);
            match.setFTHG(homeGoal);
            match.setFTAG(awayGoal);
        }else if(varHomeGoal != 0 && varAwayGoal == 0){
            NormalDistribution normHome = new NormalDistribution(meanHomeGoal, varHomeGoal);
            int homeGoal = Math.round((float)normHome.getMean());
            int awayGoal = Math.round((float)meanAwayGoal);
            char FTR;
            if(homeGoal > awayGoal){
                FTR = 'H';
                match.getHome().setWin(match.getHome().getWin() + 1);
                match.getAway().setLose(match.getAway().getLose() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 3);
            }else if(homeGoal < awayGoal){
                FTR = 'A';
                match.getHome().setLose(match.getHome().getLose() + 1);
                match.getAway().setWin(match.getAway().getWin() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 3);
            }else{
                FTR = 'D';
                match.getHome().setDraw(match.getHome().getDraw() + 1);
                match.getAway().setDraw(match.getAway().getDraw() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 1);
            }
            match.setFTR(FTR);
            match.setFTHG(homeGoal);
            match.setFTAG(awayGoal);
        }else {
            int homeGoal = Math.round((float) meanHomeGoal);
            int awayGoal = Math.round((float) meanAwayGoal);
            char FTR;
            if(homeGoal > awayGoal){
                FTR = 'H';
                match.getHome().setWin(match.getHome().getWin() + 1);
                match.getAway().setLose(match.getAway().getLose() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 3);
            }else if(homeGoal < awayGoal){
                FTR = 'A';
                match.getHome().setLose(match.getHome().getLose() + 1);
                match.getAway().setWin(match.getAway().getWin() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 3);
            }else{
                FTR = 'D';
                match.getHome().setDraw(match.getHome().getDraw() + 1);
                match.getAway().setDraw(match.getAway().getDraw() + 1);
                match.getHome().setRankPoint(match.getHome().getRankPoint() + 1);
                match.getAway().setRankPoint(match.getAway().getRankPoint() + 1);
            }
            match.setFTR(FTR);
            match.setFTHG(homeGoal);
            match.setFTAG(awayGoal);
        }
        return match;
    }

    //print result
    public static void getPredResult(ArrayList<Match> up){
        System.out.println("------------Match Result Simulation----------------------");
        for (Match match : up) {
            match = getPrediction.predict(match);
            System.out.println(match.getHome().getTeamName() + " " + match.getFTHG() + ":"+ match.getFTAG()+ " " + match.getAway().getTeamName() + " | "+match.getFTR());
        }
    }
}
