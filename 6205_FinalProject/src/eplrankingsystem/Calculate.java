package eplrankingsystem;

import eplbasicinfo.Match;
import eplbasicinfo.Team;

public class Calculate {
    //calculate the mean of home goals
    public static double getHomeMean(Team homeTeam, Team awayTeam) {
        int count = 0;
        double sumOfHomeGoal = 0;
        double meanOfHomeGoal = 0;
        for (Match match : homeTeam.getHomeMatchList()) {
            if (match.getAway().getTeamName() == awayTeam.getTeamName()) {
                sumOfHomeGoal += match.getFTHG();
                count++;
            }
        }
        meanOfHomeGoal = sumOfHomeGoal / count;
        return meanOfHomeGoal;
    }

    //calculate the mean of away goals
    public static double getAwayMean(Team homeTeam, Team awayTeam) {
        int count = 0;
        double sumOfAwayGoal = 0;
        double meanOfAwayGoal = 0;
        for (Match match : homeTeam.getHomeMatchList()) {
            if (match.getAway().getTeamName() == awayTeam.getTeamName()) {
                sumOfAwayGoal += match.getFTAG();
                count++;
            }
        }
        meanOfAwayGoal = sumOfAwayGoal / count;
        return meanOfAwayGoal;
    }

    //calculate the variance of home goals
    public static double getHomeVar(Team homeTeam, Team awayTeam) {
        int count = 0;
        double stdOfHomeGoal = 0;
        double varOfHomeGoal = 0;
        for (Match match : homeTeam.getHomeMatchList()) {
            if (match.getAway().getTeamName() == awayTeam.getTeamName()) {
                stdOfHomeGoal += Math.pow(match.getFTHG() - getHomeMean(homeTeam, awayTeam), 2);
                count++;
            }
        }
        varOfHomeGoal = Math.sqrt(stdOfHomeGoal / count);
        return varOfHomeGoal;
    }

    //calculate the variance of away goals
    public static double getAwayVar(Team homeTeam, Team awayTeam) {
        int count = 0;
        double stdOfAwayGoal = 0;
        double varOfAwayGoal = 0;
        for (Match match : homeTeam.getHomeMatchList()) {
            if (match.getAway().getTeamName() == awayTeam.getTeamName()) {
                stdOfAwayGoal += Math.pow(match.getFTAG() - getAwayMean(homeTeam, awayTeam), 2);
                count++;
            }
        }
        varOfAwayGoal = Math.sqrt(stdOfAwayGoal / count);
        return varOfAwayGoal;
    }
}
