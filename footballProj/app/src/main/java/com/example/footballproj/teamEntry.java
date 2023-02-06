package com.example.footballproj;

public class teamEntry implements Comparable<teamEntry> {
    public String teamName;
    public int goals;

    public teamEntry(String teamName, int teamGoals) {
        this.teamName = teamName;
        this.goals = teamGoals;
    }

    @Override
    public int compareTo(teamEntry o) {
        if (this.goals > o.goals) return 1;
        else if (this.goals == o.goals) return 0;
        else return -1;
    }

    @Override
    public String toString() {
        return teamName + "\t\t\t" + goals;
    }
}
