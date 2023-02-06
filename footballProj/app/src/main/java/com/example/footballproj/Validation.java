package com.example.footballproj;


abstract public class Validation {

    public static String isValid(String team1 , String team2 , String goals1 , String goals2)
    {
        int goals1int , goals2int;

        try {
            goals1int = Integer.parseInt(goals1);
            goals2int = Integer.parseInt(goals2);

            if((team1.length() > 10) || (team2.length() > 8))
                return "ERROR:\nTeam name must be under 10 letters";
            if ((team1.length() <= 0) || ( team2.length() <= 0))
                return "ERROR:\nTeam name must not be empty ";
            if((goals1int < 0) || (goals2int < 0))
                return "ERROR:\nTeam goals must be a positive number";
            if((goals1int > 99 || goals2int > 99))
                return "ERROR:\nTeam goals must be under 99";


        }
        catch (NumberFormatException e){
            return "Team goals must be a number";
        }
        return "";
    }
}
