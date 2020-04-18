package eplbasicinfo;

import java.util.ArrayList;
/**
 * @author Sichen & Xianling
 * this MatchDirectory Class includes 1 attributs: matchDirectory
 */
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
