package eplbasicinfo;

import java.util.ArrayList;

/**
 * @author Sichen & Xianling
 * This TeamDirectory Class aims to store all the teams info
 */

public class TeamDirectory {
    private ArrayList<Team> teamDirectory;

    public TeamDirectory() {
        this.teamDirectory = new ArrayList<Team>();
    }

    public ArrayList<Team> getTeamDirectory() {
        return teamDirectory;
    }

    public void setTeamDirectory(ArrayList<Team> teamDirectory) {
        this.teamDirectory = teamDirectory;
    }
}

