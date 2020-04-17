package eplbasicinfo;

import java.util.ArrayList;

public class MatchDirectory {
    private ArrayList<Match> matchDirectory;

    public MatchDirectory() {
        this.matchDirectory = new ArrayList<Match>();
    }

    public ArrayList<Match> getMatchDirectory() {
        return matchDirectory;
    }

    public void setMatchDirectory(ArrayList<Match> matchDirectory) {
        this.matchDirectory = matchDirectory;
    }
}
