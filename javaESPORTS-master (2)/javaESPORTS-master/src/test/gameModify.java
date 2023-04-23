package test;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class gameModify {
    public static void addGame(PreparedStatement pstmt, ResultSet myRs){
        int numPlay;
        String id, checker, desc;
        try {
            System.out.print("Game Name: ");
            esports.s.nextLine();
            id = esports.s.nextLine(); //gets email from user
            while (esports.myRs.next()) { //checks to make sure the user is in the DB
                checker = esports.myRs.getString("GameName");
                while (id == checker) {
                    System.out.print("This game already exists please enter a new game name: ");
                    esports.s.nextLine();
                    id = esports.s.nextLine();
                }
            }
            System.out.println("Game Description:");
            //esports.s.nextLine();
            desc = esports.s.nextLine();
            System.out.println("Number of players needed to compete: ");
            numPlay = esports.s.nextInt();
            esports.pstmt.setString(1, id);
            esports.pstmt.setString(2, desc);
            esports.pstmt.setInt(3, numPlay);
            esports.pstmt.executeUpdate();
            System.out.println("Game added successfully.");
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void removeGame(PreparedStatement pstmt, ResultSet myRs){
        int   numPlay;
        String id, checker, desc, confirm;
        try {
            System.out.print("Which game is to be removed?\n1) League of Legends\n2)Rocket League ");
            esports.s.nextLine();
            id = esports.s.nextLine(); //gets email from user
            while (esports.myRs.next()) {
                checker = esports.myRs.getString("GameName"); //gets all the value in each row
                System.out.println(checker);
                desc = esports.myRs.getString("Description");
                numPlay = esports.myRs.getInt("NumPlayer");
                if (checker.equals(id)) { //if the id is equal to the current result drop the employee
                    System.out.println("Are you sure you would like to remove game? (y or n)");
                    confirm = esports.s.next();
                    if(confirm.equals("y")) {
                        esports.pstmt.setString(1, id);
                        esports.pstmt.executeUpdate();
                        System.out.println("The following Game has been removed from the system:");
                        System.out.println(checker + ", " + desc + ", " + numPlay);
                    }
                    else {
                        System.out.println("The following Game was NOT removed from the system:\n");
                        System.out.println(checker);
                    }
                    return;
                }
            }
            System.out.println("This game is not in the system.");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void addStats(PreparedStatement pstmt, ResultSet myRs){
        String statName, chosenGame = "", game;
        int gName, i = 0;
        String[] games = new String[100];
        int numStats, counter = 0;
        try{
            System.out.println("Which game would you like to add stats for");
            while(esports.myRs.next()){
                game = esports.myRs.getString("GameName");
                i++;
                games[i] = game;
                System.out.println(i + ") " + games[i]);
            }
            gName = esports.s.nextInt();
            while(counter<=i){
                if(counter == gName){
                    chosenGame = games[counter];
                    break;
                }
                counter++;
            }
            counter = 0;
            System.out.println("How many games would you like to add?");
            numStats = esports.s.nextInt();
            while( counter < numStats){
                System.out.println("What is the stat" +  "name? (no spaces, use underscore)");
                statName = esports.s.next();
                esports.pstmt.setString(1, chosenGame);
                esports.pstmt.setString(2, statName);
                esports.pstmt.executeUpdate();
                counter++;
            }
        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void removeStats(PreparedStatement pstmt, ResultSet myRs){
        String statName, chosenGame = "", game, chosenStat = "", confirm;
        int gName, i = 0;
        String[] games = new String[100];
        String[] stats = new String[50];
        int numStats, counter = 0;
        try{
            System.out.println("Which game would you like to remove stats for");
            while(esports.myRs.next()){
                game = esports.myRs.getString("GameName");
                i++;
                games[i] = game;
                System.out.println(i + ") " + games[i]);
            }
            gName = esports.s.nextInt();
            while(counter<=i){
                if(counter == gName){
                    chosenGame = games[counter];
                    break;
                }
                counter++;
            }
            i = 0;
            esports.pstmt.setString(1, chosenGame);
            esports.myRs = esports.pstmt.executeQuery();
            System.out.println("Which stat would you like to remove?");
            while(esports.myRs.next()){
                statName = esports.myRs.getString("statName");
                i++;
                stats[i] = statName;
                System.out.println(i + ") " + stats[i]);
            }
            counter = 0;
            numStats = esports.s.nextInt();
            while(counter<=i){
                if(counter == gName){
                    chosenStat = stats[counter];
                    break;
                }
                counter++;
            }
            System.out.println("Are you sure you would like to remove stat? (y or n)");
            confirm = esports.s.next();
            if(confirm.equals("y")) {
                esports.pstmt = esports.myConn.prepareStatement(esports.removeStat);
                esports.pstmt.setString(1, chosenGame);
                esports.pstmt.setString(2, chosenStat);
                System.out.println("Stat Removed!");
            }

        }catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayStats(ResultSet myRs){ //uses query that select * to display all the employees
        int counter = 0, i = 0, numGame;
        String gName = "", sName, game;
        String[] games = new String[100];
        try {
            System.out.println("Which game would you like to display stats for?");
            while(esports.myRs.next()){
                game = esports.myRs.getString("GameName");
                i++;
                games[i] = game;
                System.out.println(i + ") " + games[i]);
            }
            numGame = esports.s.nextInt();
            while(counter<=i){
                if(counter == numGame){
                    gName = games[counter];
                    break;
                }
                counter++;
            }
            esports.pstmt = esports.myConn.prepareStatement(esports.viewGameStat);
            esports.pstmt.setString(1, gName);
            esports.myRs = esports.pstmt.executeQuery();
            System.out.println("Stat List:");
            while (esports.myRs.next()) {
                sName = esports.myRs.getString("statName");
                System.out.println(gName + ", " + sName);
            }
            System.out.print("\n");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
}
