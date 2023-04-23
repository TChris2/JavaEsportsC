package test;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;

public class esports {

    static Connection myConn = null;
    static Statement myStmt = null;
    static ResultSet myRs = null;
    static PreparedStatement pstmt = null;
    static Scanner s = new Scanner(System.in);
    static int choice;
    static String viewCaptains = "SELECT * FROM Leader";
    static String viewUsers = "SELECT * FROM User";
    static String addToTeam = "UPDATE User SET GameName = ? AND TeamID = ? WHERE Email = ?";
    static String removeFromTeam = "UPDATE User SET GameName = NULL AND TeamID = NULL WHERE Email = ?";
    static String checkEmail = "SELECT Email FROM User"; //check if the user is in the database, if not they cannot get access
    static String checkPassword = "SELECT Password FROM User"; //checks the password for the email and will be used to see if they match
    static String addPlayerToDB = "INSERT INTO User(Email, Password, fName, lName, discordName) VALUES(?,?,?,?,?)";
    static String removePlayerFromDB = "DELETE FROM User Where Email = ?";
    static String viewPlayerBasicInfo = "SELECT Email, fName, lName FROM User";
    static String addCaptain = "INSERT INTO Leader(Email, GameName, TeamID, startDate) VALUES(?,?,?,?)";
    static String removeCaptain = "UPDATE Leader Set endDate = ? WHERE Email = ?";
    static String addGame = "INSERT INTO Game(GameName, Description, NumPlayer) VALUES(?,?,?)";
    static String viewGame = "SELECT * FROM Game";
    static String removeGame = "DELETE FROM Game WHERE GameName = ?";
    static String addStat = "INSERT INTO Stat(GameName, statName) VALUES(?,?)";
    static String addTournament = "INSERT INTO Tournament(TourID, Date, Status, Season) VALUES(?,?,?,?)";
    static String getTourID = "SELECT * FROM Tournament";
    static String updateTournamentStatus = "UPDATE Tournament SET status = ? WHERE TourID = ?";
    static String viewTournament = "SELECT * FROM Tournament WHERE Season = ?";
    static String addCompetition = "INSERT INTO Competition(GameName, TeamID, OpponentName, Result, TourID) VALUES(?,?,?,?,?)";
    static String checkComp = "SELECT * FROM Competition";
    static String viewCompetition = "SELECT * FROM Competition WHERE GameName = ? ";
    static String viewTeam = "SELECT fName, lName FROM User WHERE GameName = ? AND TeamID = ? ";
    static String checkCapTeam = "SELECT GameName, TeamID FROM Leader Where Email = ?";
    static String removeStat = "DELETE FROM Stat WHERE GameName = ? AND statName = ?";
    static String viewStat = "SELECT * FROM Stat";
    static String viewGameStat = "SELECT statName from Stat WHERE GameName = ?";
    static String viewTeams = "SELECT * FROM Team WHERE GameName = ?";
    static String removeTeam = "DELETE FROM Team WHERE GameName = ? AND TeamID = ?";
    static String insertTeam = "INSERT INTO Team(GameName, TeamID) VALUES(?,?)";

    public static void main(String[] args) {

        //makes changes to DB
        String changePassword = "UPDATE User SET Password = ? WHERE Email = ?";

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:sqlite:esports.db");

            // 2. Create a statement
            myStmt = myConn.createStatement(); //ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY wanted to do this to allow for error checking but not supported by SQLITE

            //3. Execute SQL Query
            myStmt.executeUpdate(tables.gameTable);
            myStmt.executeUpdate(tables.teamTable);
            myStmt.executeUpdate(tables.userTable);
            myStmt.executeUpdate(tables.tournamentTable);
            myStmt.executeUpdate(tables.competeTable);
            myStmt.executeUpdate(tables.leaderTable);
            myStmt.executeUpdate(tables.statTable);

            //4. Login
            login.login();
            //Check if admin, captain, or user
            if (login.email.equals("ADMIN")) { //make constant variable for email
                System.out.println("\nHello Admin!\n");
                while (choice != 8) {
                    System.out.print(
                            "Main Menu\n(1) Modify/View players\n" //submenus for th
                                    + "(2) Modify/View captains\n(3) Modify/View games\n(4) Modify/View tournaments\n"
                                    + "(5) Modify/View competitions\n(6) Modify/View teams\n(7) Change Password\n(8) Log out\nEnter your choice: "
                    );
                    choice = s.nextInt();
                    System.out.println();
                    switch (choice) {
                        case 1:
                            admin.playerSubMenu();
                            break;
                        case 2:
                            admin.captainSubMenu();
                            break;
                        case 3:
                            admin.gameSubMenu();
                            break;
                        case 4:
                            admin.tourSubMenu();
                            break;
                        case 5:
                            admin.compSubMenu();
                            break;
                        case 6:
                            admin.teamSubMenu();
                            break;
                        case 7:
                            pstmt = myConn.prepareStatement(changePassword);
                            user.changePass(pstmt);
                            break;
                        case 8:
                            System.out.println("Goodbye!");
                            System.exit(0);
                    }
                }
            }
            if(login.isCAP == 1){
                System.out.println("Hello Captain!\n");
                while (choice != 8) {
                    System.out.print(
                            "Main Menu\n(1) Modify team\n"
                                    + "(2) Display players\n(3) Display Games\n(4) Display Tournaments\n"
                                    + "(5) Display Competition\n(6) View team\n(7) Change Password\n(8) Log out\nEnter your choice: "
                    );
                    choice = s.nextInt();
                    System.out.println();
                    switch (choice) {
                        case 1:
                            captain.capTeamSubMenu();
                            break;
                        case 2:
                            myRs = myStmt.executeQuery(viewPlayerBasicInfo);
                            user.displayPlayers(myRs);
                            break;
                        case 3:
                            myRs = myStmt.executeQuery(viewGame);
                            user.displayGames(myRs);
                            break;
                        case 4:
                            pstmt = myConn.prepareStatement(viewTournament);
                            user.displayTournaments(pstmt);
                            break;
                        case 5:
                            pstmt = myConn.prepareStatement(viewCompetition);
                            user.displayCompetitions(pstmt);
                            break;
                        case 6:
                            pstmt = myConn.prepareStatement(viewTeam);
                            user.displayTeam(pstmt);
                            break;
                        case 7:
                            pstmt = myConn.prepareStatement(changePassword);
                            user.changePass(pstmt);
                            break;
                        case 8:
                            System.out.println("Goodbye!");
                            System.exit(0);
                    }
                }
            }
            else{
                System.out.println("Hello User!\n");
                while (choice != 7) {
                    System.out.print(
                            "Main Menu\n"
                                    + "(1) Display players\n(2) Display Games\n(3) Display Tournaments\n"
                                    + "(4) Display Competition\n(5) View team\n(6) Change Password\n(7) Log out\nEnter your choice: "
                    );
                    choice = s.nextInt();
                    System.out.println();
                    switch (choice) {
                        case 1:
                            myRs = myStmt.executeQuery(viewPlayerBasicInfo);
                            user.displayPlayers(myRs);
                            break;
                        case 2:
                            myRs = myStmt.executeQuery(viewGame);
                            user.displayGames(myRs);
                            break;
                        case 3:
                            pstmt = myConn.prepareStatement(viewTournament);
                            user.displayTournaments(pstmt);
                            break;
                        case 4:
                            pstmt = myConn.prepareStatement(viewCompetition);
                            user.displayCompetitions(pstmt);
                            break;
                        case 5:
                            pstmt = myConn.prepareStatement(viewTeam);
                            user.displayTeam(pstmt);
                            break;
                        case 6:
                            pstmt = myConn.prepareStatement(changePassword);
                            user.changePass(pstmt);
                            break;
                        case 7:
                            System.out.println("Goodbye!");
                            System.exit(0);
                        default:
                        	System.out.println("Error Invalid Input\nPlease Input A Valid Number");
                        	break;
                    }
                }
            }

        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            try {
                if (myStmt != null)
                    myStmt.close();
            } catch (SQLException se2) {
            }// nothing we can do

            try {
                if (myConn != null)
                    myConn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try

            try {
                if (myRs != null)
                    myRs.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }
    }
}