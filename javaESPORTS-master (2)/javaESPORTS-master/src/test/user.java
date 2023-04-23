package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class user {
    public static void displayPlayers(ResultSet myRs){ //uses query that select * to display all the employees
        String email, fname, lname;
        try {
            System.out.println("Player List:");

            while (esports.myRs.next()) {
                email = esports.myRs.getString("Email");
                fname = esports.myRs.getString("fName");
                lname = esports.myRs.getString("lName");
                if(email.equals("ADMIN")){
                    continue;
                }
                System.out.println(email + ", " + fname + ", " + lname);
            }
            System.out.println();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayGames(ResultSet myRs){ //uses query that select * to display all the employees
        int numPlay;
        String gName, desc;
        try {
            System.out.println("Captain List:\n");
            int j = 0;
            while (esports.myRs.next()) {
                j ++;
                gName = esports.myRs.getString("GameName");
                desc = esports.myRs.getString("Description");
                numPlay = esports.myRs.getInt("NumPlayer");
                System.out.println("Game " + j +":");
                System.out.println(gName + "\n" + "\n" + desc + "\n"+numPlay);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayTournaments(PreparedStatement pstmt){ //uses query that select * to display all the employees
        int tourID;
        String id, date, status, season;
        try {
            System.out.println("Enter season to view tournaments:");
            id = esports.s.nextLine();
            esports.pstmt.setString(1, id);
            esports.myRs = esports.pstmt.executeQuery();

            System.out.println("Tournaments:\n");
            while (esports.myRs.next()) {
                tourID = esports.myRs.getInt("TourID");
                date = esports.myRs.getString("Date");
                status = esports.myRs.getString("Status");
                season = esports.myRs.getString("Season");
                System.out.println(tourID + ", " + date + ", " + status + ", " + season);
            }
            System.out.println();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayCompetitions(PreparedStatement pstmt){ //uses query that select * to display all the employees
        int tID, tourID;
        String id, gName, opponentN, result;
        try {
            System.out.println("Enter game name to display competitions:");
            esports.s.nextLine();
            id = esports.s.nextLine();
            esports.pstmt.setString(1, id);
            esports.myRs = esports.pstmt.executeQuery();

            System.out.println("Competitions:\n");
            while (esports.myRs.next()) {
                gName = esports.myRs.getString("GameName");
                tID = esports.myRs.getInt("TeamID");
                opponentN = esports.myRs.getString("OpponentName");
                result = esports.myRs.getString("Result");
                tourID = esports.myRs.getInt("TourID");
                System.out.println(gName + ", " + tID + ", " + opponentN + ", " + result + ", " + tourID);
            }
            System.out.println();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayTeam(PreparedStatement pstmt){ //uses query that select * to display all the employees
        int tID;
        String gName, fName, lName;
        try {
            System.out.println("Enter game name to view teams:");
            esports.s.nextLine();
            gName= esports.s.nextLine();
            System.out.println("Which team would you like to view? (1 or 2): ");
            tID = esports.s.nextInt();
            esports.pstmt.setString(1, gName);
            esports.pstmt.setInt(2, tID);
            esports.myRs = esports.pstmt.executeQuery();

            System.out.println("Team" + tID + " in " + gName + ":\n");
            while (esports.myRs.next()) {
                fName = esports.myRs.getString("fName");
                lName = esports.myRs.getString("lName");
                System.out.println(fName + " " +lName);
            }
            System.out.println();
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void changePass(PreparedStatement pstmt){
        String email, newPass, confirm;
        try {
            System.out.println("Enter your email:\n");
            email = esports.s.next();
            System.out.println("New Password:\n");
            newPass = esports.s.next();
            System.out.println("Re-Enter New Password:\n");
            confirm = esports.s.next();
            while(newPass != confirm){
                System.out.println("Passwords do not match.");
                System.out.println("Re-Enter New Password:\n");
                confirm = esports.s.next();
            }
            esports.pstmt.setString(1, newPass);
            esports.pstmt.setString(2, email);
            esports.pstmt.executeUpdate();
             System.out.println("Password Updated!");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}
