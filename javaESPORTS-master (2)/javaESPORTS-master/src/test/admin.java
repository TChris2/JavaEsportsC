package test;
import java.sql.*;
import java.lang.*;

public class admin {
    public static void playerSubMenu(){
        try {
            while(esports.choice != 4) {
                System.out.println("Modify Players Menu:\n(1) Add player to the system\n(2) Remove player from the system\n(3) Display Players\n(4) Return to Main Menu\nEnter your choice:");
                esports.choice = esports.s.nextInt();
                System.out.println();
                switch (esports.choice) {
                    case 1:
                        esports.pstmt = esports.myConn.prepareStatement(esports.addPlayerToDB);
                        esports.myRs = esports.myStmt.executeQuery(esports.checkEmail);
                        addPlayerDB(esports.pstmt, esports.myRs);
                        break;
                    case 2:
                        esports.pstmt = esports.myConn.prepareStatement(esports.removePlayerFromDB);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewUsers);
                        removePlayerDB(esports.pstmt, esports.myRs);
                        break;
                    case 3:
                        esports.myRs = esports.myStmt.executeQuery(esports.viewPlayerBasicInfo);
                        user.displayPlayers(esports.myRs);
                        break;
                    case 4:
                        return;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void captainSubMenu(){
        try {
            while(esports.choice != 4) {
                System.out.println("Modify Captains Menu:\n(1) Add captain\n(2) Remove captain\n(3) Display Captains\n(4) Return to Main Menu\nEnter your choice:");
                esports.choice = esports.s.nextInt();
                System.out.println();
                switch (esports.choice) {
                    case 1:
                        esports.pstmt = esports.myConn.prepareStatement(esports.addCaptain);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewUsers);
                        addCaptain(esports.pstmt, esports.myRs);
                        break;
                    case 2:
                        esports.pstmt = esports.myConn.prepareStatement(esports.removeCaptain);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewUsers);
                        removeCaptain(esports.pstmt, esports.myRs);
                        break;
                    case 3:
                        esports.myRs = esports.myStmt.executeQuery(esports.viewCaptains);
                        displayCaptains(esports.myRs);
                        break;
                    case 4:
                        return;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void gameSubMenu(){
        try{
            while(esports.choice != 7) {
                System.out.println("Modify Games Menu:\n(1) Add game\n(2) Remove game\n(3) Display games\n(4) Add stats\n(5) Remove stats\n(6) Display stats\n(7) Return to Main Menu\nEnter your choice:");
                esports.choice = esports.s.nextInt();
                System.out.println();
                switch (esports.choice) {
                    case 1:
                        esports.pstmt = esports.myConn.prepareStatement(esports.addGame);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        gameModify.addGame(esports.pstmt, esports.myRs);
                        break;
                    case 2:
                        esports.pstmt = esports.myConn.prepareStatement(esports.removeGame);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        gameModify.removeGame(esports.pstmt, esports.myRs);
                        break;
                    case 3:
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        user.displayGames(esports.myRs);
                        break;
                    case 4:
                        esports.pstmt = esports.myConn.prepareStatement(esports.addStat);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        gameModify.addStats(esports.pstmt, esports.myRs);
                        break;
                    case 5:
                        esports.pstmt = esports.myConn.prepareStatement(esports.viewGameStat);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        gameModify.removeStats(esports.pstmt, esports.myRs);
                        return;
                    case 6:
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        gameModify.displayStats(esports.myRs);
                        break;
                    case 7:
                        return;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void tourSubMenu(){
        try{
            int choice = 0;
            while(choice != 4) {
                System.out.println("Modify Tournaments Menu:\n(1) Add tournament\n(2) Update tournament\n(3) Display tournaments\n(4) Return to Main Menu\nEnter your choice:");
                choice = esports.s.nextInt();
                System.out.println();
                switch (esports.choice) {
                    case 1:
                        esports.pstmt = esports.myConn.prepareStatement(esports.addTournament);
                        esports.myRs = esports.myStmt.executeQuery(esports.getTourID);
                        addTournament(esports.pstmt, esports.myRs);
                        break;
                    case 2:
                        esports.pstmt = esports.myConn.prepareStatement(esports.updateTournamentStatus);
                        esports.myRs = esports.myStmt.executeQuery(esports.getTourID);
                        updateStatus(esports.pstmt, esports.myRs);
                        break;
                    case 3:
                        esports.pstmt = esports.myConn.prepareStatement(esports.viewTournament);
                        user.displayTournaments(esports.pstmt);
                        break;
                    case 4:
                        return;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void compSubMenu(){
        try{
            while(esports.choice != 3) {
                System.out.println("Modify Competitions Menu:\n(1) Add competition\n(2) Display competitions\n(3) Return to Main Menu\nEnter your choice:");
                esports.choice = esports.s.nextInt();
                System.out.println();
                switch (esports.choice) {
                    case 1:
                        esports.pstmt = esports.myConn.prepareStatement(esports.addCompetition);
                        esports.myRs = esports.myStmt.executeQuery(esports.checkComp);
                        addCompetition(esports.pstmt, esports.myRs);
                        break;
                    case 2:
                        esports.pstmt = esports.myConn.prepareStatement(esports.viewCompetition);
                        user.displayCompetitions(esports.pstmt);
                        break;
                    case 3:
                        return;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void teamSubMenu(){
        try{
            while(esports.choice != 4) {
                System.out.println("Modify teams Menu:\n(1) Create team\n(2) Delete team\n(3) Display team\n(4) Return to Main Menu\nEnter your choice:");
                esports.choice = esports.s.nextInt();
                System.out.println();
                switch (esports.choice) {
                    case 1:
                        esports.pstmt = esports.myConn.prepareStatement(esports.viewTeams);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);
                        createTeam(esports.pstmt, esports.myRs);
                        break;
                    case 2:
                        esports.pstmt = esports.myConn.prepareStatement(esports.viewTeams);
                        esports.myRs = esports.myStmt.executeQuery(esports.viewGame);

                        break;
                    case 3:
                        esports.pstmt = esports.myConn.prepareStatement(esports.viewTeam);
                        user.displayTeam(esports.pstmt);
                        break;
                    case 4:
                        return;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void addPlayerDB(PreparedStatement pstmt, ResultSet myRs) {
        int   tID;
        String id, checker, pass, fname, lname,disName, uName, gName;
        try {
            System.out.print("Email: ");
            id = esports.s.next(); //gets email from user
            while (esports.myRs.next()) { //checks to make sure the user is in the DB
                checker = esports.myRs.getString("Email");
                while (id == checker) {
                    System.out.print("Email should be unique. Enter another Email\nEmail: ");
                    id = esports.s.next();
                }
            }
            System.out.print("Password: ");
            pass = esports.s.next();
            System.out.print("First Name: ");
            fname = esports.s.next();
            System.out.print("Last Name: ");
            lname = esports.s.next();
            System.out.print("Discord Name: ");
            disName = esports.s.next();
            esports.pstmt.setString(1, id);
            esports.pstmt.setString(2, pass); //inputs all the data
            esports.pstmt.setString(3, fname);
            esports.pstmt.setString(4, lname);
            esports.pstmt.setString(5, disName);
            esports.pstmt.executeUpdate();
            System.out.println("Player Added to system.\n");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void removePlayerDB(PreparedStatement pstmt, ResultSet myRs){
        int   tID;
        String id, checker, pass, fname, lname,disName, uName, gName, confirm;
        try {
            System.out.print("Enter Email of player to be removed: ");
            id = esports.s.next(); //gets email from user
            while (esports.myRs.next()) {
                checker = esports.myRs.getString("Email"); //gets all the value in each row
                pass = esports.myRs.getString("Password");
                fname = esports.myRs.getString("fName");
                lname = esports.myRs.getString("lName");
                disName = esports.myRs.getString("discordName");
                uName = esports.myRs.getString("userName");
                gName = esports.myRs.getString("GameName");
                tID = esports.myRs.getInt("TeamID");
                if (checker.equals(id)) { //if the id is equal to the current result drop the employee
                    System.out.println("Are you sure you would like to remove player? (y or n)");
                    confirm = esports.s.next();
                    if(confirm.equals("y")) {
                        esports.pstmt.setString(1, id);
                        esports.pstmt.executeUpdate();
                        System.out.println("The following Player has been removed from the system:");
                        System.out.println(checker + ", " + fname + ", " + lname + "\n");
                    }
                    else {
                        System.out.println("The following Player was NOT removed from the system:\n");
                        System.out.println(checker + ", " + fname + ", " + lname);
                        return;
                    }
                }
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }

    public static void addCaptain(PreparedStatement pstmt, ResultSet myRs) {
        int tID, noDB = 1, game;
        String id, checker, gName, startDate;
        try {
            System.out.println("Enter email of the user to be captain: ");
            id = esports.s.next(); //gets email from user
            while (esports.myRs.next()) { //goes through rows to make sure the id isn't already in there
                checker = esports.myRs.getString("Email");
                if(checker.equals(id)) {
                    noDB = 0;
                    break;
                }
            }
            if(noDB == 0){
                esports.myRs = esports.myStmt.executeQuery(esports.viewCaptains);
                while(esports.myRs.next()){
                    checker = esports.myRs.getString("Email");
                    while (checker.equals(id)) {
                        System.out.print("The user with this email is already a captain. Enter another Email\\nEmail: ");
                        id = esports.s.next();
                    }
                }
            }
            else{
                System.out.println("This user is not in the system");
                return;
            }
            System.out.println("Which game will they be captain of?:\n1) League of Legends\n2) Rocket League");
            game = esports.s.nextInt();
            if(game == 1){
                gName = "League of Legends";
            } else gName = "Rocket League";
            System.out.println("Which team will they be captain of? (1 or 2): ");
            tID = esports.s.nextInt();
            System.out.println("When will their duties of captain begin? (MM-DD-YYYY): ");
            startDate = esports.s.next();
            esports.pstmt.setString(1, id);
            esports.pstmt.setString(2, gName);
            esports.pstmt.setInt(3, tID);
            esports.pstmt.setString(4, startDate);
            esports.pstmt.executeUpdate();
            System.out.println("Player successfully added as a captain.\n");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void removeCaptain(PreparedStatement pstmt, ResultSet myRs){
        int   tID, noDB = 1;
        String id, checker, confirm, endDate;
        try {
            System.out.println("Enter Email of captain to be removed: ");
            id = esports.s.next(); //gets email from user
            while (esports.myRs.next()) {
                checker = esports.myRs.getString("Email"); //gets all the value in each row
                if (checker.equals(id)) { //if the id is equal to the current result drop the employee
                    noDB = 0;
                    break;
                }
            }
            if(noDB == 0){
                esports.myRs = esports.myStmt.executeQuery(esports.viewCaptains);
                while(esports.myRs.next()){
                    checker = esports.myRs.getString("Email");
                    if(checker.equals(id)) {
                        System.out.println("Are you sure you would like to remove captain? (y or n)");
                        confirm = esports.s.next();
                        if(confirm.equals("y")) {
                            System.out.println("When will their duties of captain end? (MM-DD-YYYY): ");
                            endDate = esports.s.next();
                            esports.pstmt.setString(1, endDate);
                            esports.pstmt.setString(2, id);
                            esports.pstmt.executeUpdate();
                            System.out.println("The following captain has been removed:");
                            System.out.println(checker);
                        }
                        else {
                            System.out.println("The following captain was NOT removed:\n");
                            System.out.println(checker + "\n");
                            return;
                        }
                    }
                }
            }
            else{
                System.out.println("This user is not in the system\n");
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayCaptains(ResultSet myRs){ //uses query that select * to display all the employees
        int tID;
        String email, gName, sDate, eDate;
        String[][] captains = new String[5][100];
        try {
            System.out.println("Captain List:\n");
            int j = 0;
            while (esports.myRs.next()) {
                email = esports.myRs.getString("Email");
                gName = esports.myRs.getString("GameName");
                tID = esports.myRs.getInt("TeamID");
                sDate = esports.myRs.getString("startDate");
                eDate = esports.myRs.getString("endDate");
                j = j + 1;
                System.out.println(email + ", " + gName + ", " + tID + ", " +sDate + ", " + eDate);
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void addTournament(PreparedStatement pstmt, ResultSet myRs){
        int tourID = 1;
        String date,season,status;
        try {
            while (esports.myRs.next()) { //checks to make sure the user is in the DB
                tourID++; //makes the tournament id the next value
            }
            System.out.println("What is the status for the tournament? (upcoming,completed, or cancelled):");
            status = esports.s.next();
            System.out.println("When will/did the tournament occur? (MM-DD-YYYY):");
            date = esports.s.next();
            System.out.println("During which season is the competition? (YYYY):");
            season = esports.s.next();
            esports.pstmt.setInt(1, tourID);
            esports.pstmt.setString(2, date);
            esports.pstmt.setString(3, status);
            esports.pstmt.setString(4,season);
            esports.pstmt.executeUpdate();
            System.out.println("Tournament added!");
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void updateStatus(PreparedStatement pstmt, ResultSet myRs){
        int tourID;
        String date,season,status;
        try {
            while (esports.myRs.next()) { //checks to make sure the user is in the DB
                tourID = esports.myRs.getInt("TourID");
                date = esports.myRs.getString("Date");
                status = esports.myRs.getString("Status");
                season = esports.myRs.getString("Season");
            }
            System.out.println("Which tournament status would you like to update? (tourID):");
            tourID = esports.s.nextInt();
            System.out.println("What is the new status for the tournament? (upcoming,completed, or cancelled):");
            status = esports.s.next();
            esports.pstmt.setInt(1, tourID);
            esports.pstmt.setString(2, status);
            esports.pstmt.executeUpdate();
            System.out.println("Tournament updated!");
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void addCompetition(PreparedStatement pstmt, ResultSet myRs){
        int tid, tourID,checkT, checkTour;
        String gName, oName, result, checkG;
        try {
            System.out.println("Which tournament did the competition occur? (tourID): ");
            tourID = esports.s.nextInt();
            System.out.println("Which game was played at the competition?: ");
            esports.s.nextLine();
            gName = esports.s.nextLine();
            System.out.println("Which team competed at the competition?: ");
            tid = esports.s.nextInt();
            while (esports.myRs.next()) { //checks to make sure the user is in the DB
                checkG = esports.myRs.getString("GameName");
                checkT = esports.myRs.getInt("TeamID");
                oName = esports.myRs.getString("OpponentName");
                result = esports.myRs.getString("Result");
                checkTour = esports.myRs.getInt("TourID");
                while(checkG.equals(gName) && checkT == tid && checkTour == tourID){
                    System.out.println("This competition already exists. ");
                    System.out.println("Which tournament did the competition occur? (tourID): ");
                    tourID = esports.s.nextInt();
                    System.out.println("Which game was played at the competition?: ");
                    esports.s.nextLine();
                    gName = esports.s.nextLine();
                    System.out.println("Which team competed at the competition?: ");
                    tid = esports.s.nextInt();
                }
            }
            System.out.println("Who was the opponent?: ");
            esports.s.nextLine();
            oName = esports.s.nextLine();
            System.out.println("What was the result? (win or loss): ");
            result = esports.s.next();
            esports.pstmt.setString(1, gName);
            esports.pstmt.setInt(2, tid);
            esports.pstmt.setString(3, oName);
            esports.pstmt.setString(4, result);
            esports.pstmt.setInt(5, tourID);
            esports.pstmt.executeUpdate();
            System.out.println("Competition added!");
        }catch (SQLException se) {
            se.printStackTrace();
        }
    }
    public static void createTeam(PreparedStatement pstmt, ResultSet myRs){
        int counter = 0, i = 0, j, x, numGame,  maxUser = 0, users, tID;
        String gName = "", game, fName, lName, email = "";
        String[] games = new String[100];
        String[] emails = new String[100];
        Integer[] maxPlayer = new Integer[100];
        try {
            System.out.println("Which game would you like to create a team for?");
            while(esports.myRs.next()){
                game = esports.myRs.getString("GameName");
                users = esports.myRs.getInt("NumPlayer");
                i++;
                games[i] = game;
                maxPlayer[i] = users;
                System.out.println(i + ") " + games[i]);
            }
            numGame = esports.s.nextInt();
            while(counter<=i){
                if(counter == numGame){
                    gName = games[counter];
                    maxUser = maxPlayer[counter];
                    break;
                }
                counter++;
            }
            counter = 0;
            esports.pstmt.setString(1, gName);
            esports.myRs = esports.pstmt.executeQuery();
            while (esports.myRs.next()) {
                counter++;
            }
            if(counter >= 2){
                System.out.println("This game already has two teams.");
            }
            else{
                tID = counter + 1;
                esports.pstmt = esports.myConn.prepareStatement(esports.insertTeam);
                esports.pstmt.setString(1, gName);
                esports.pstmt.setInt(2, tID);
                esports.pstmt.executeQuery();
                System.out.println("Which players would you like to add to this team?");
                esports.myRs = esports.myStmt.executeQuery(esports.viewUsers);
                while (esports.myRs.next()) {
                    fName = esports.myRs.getString("fName");
                    lName = esports.myRs.getString("lName");
                    email = esports.myRs.getString("Email");
                    i++;
                    emails[i] = email;
                    System.out.println(i + ") " + emails[i]);
                }
                counter = 0; x = 0;
                System.out.println(gName + " requires " + maxUser + " players.");
                while(counter < maxUser){
                    j = esports.s.nextInt();
                    while(x<=i){
                        if(x == j){
                            email = emails[x];
                            break;
                        }
                        x++;
                    }
                    counter++;
                    esports.pstmt = esports.myConn.prepareStatement(esports.addToTeam);
                    esports.pstmt.setString(1, gName);
                    esports.pstmt.setInt(2, tID);
                    esports.pstmt.setString(3, email);
                    esports.pstmt.executeUpdate();
                    System.out.println("Player Added to team.\n");
                }
                System.out.println("Team successfully added!");
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void deleteTeam(PreparedStatement pstmt, ResultSet myRs){
        int counter = 0, i = 0, j, x, numGame, tID = 0;
        String gName = "", game, confirm;
        String[] games = new String[100];
        Integer[]teams = new Integer[2];
        try{
            System.out.println("Which game would you like to delete a team for?");
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
            esports.pstmt.setString(1, gName);
            esports.myRs = esports.pstmt.executeQuery();
            i = 0;
            while(myRs.next()){
                tID = esports.myRs.getInt("TeamID");
                i++;
                teams[i] = tID;
                System.out.println(i + ") " + teams[i]);
            }
            j = esports.s.nextInt();
            System.out.println("Are you sure you would like to remove team? (y or n)");
            confirm = esports.s.next();
            if(confirm.equals("y")) {
                esports.pstmt = esports.myConn.prepareStatement(esports.removeTeam);
                esports.pstmt.setString(1, gName);
                esports.pstmt.setInt(2, j);
                esports.pstmt.executeUpdate();
            }
            else {
                System.out.println("The following team was NOT removed:\n");
                System.out.println(gName + " " + tID);
            }

        }catch(SQLException se){
            se.printStackTrace();
        }
    }
}
