package test;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;

public class Assign2 {

    static Connection myConn = null;
    static Statement myStmt = null;
    static ResultSet myRs = null;
    static PreparedStatement pstmt = null;
    static Scanner s = new Scanner(System.in);
    static int choice;
    public static void main(String[] args) {

        String sql = "CREATE TABLE IF NOT EXISTS Employees(\n" //sql query to create table if it doesn't exist
                + "EID  INTEGER PRIMARY KEY, \n"
                + "fName TEXT NOT NULL, \n"
                + "lName TEXT NOT NULL, \n"
                + "DateOfJoining TEXT NOT NULL \n"
                +")";
        String sql2 = "INSERT INTO Employees (EID, fName, lName, DateOfJoining)" + //sql query for prepared statement to add an employee
                "VALUES (?,?,?,?);";
        String sql3 = "DELETE FROM Employees WHERE EID = ?"; //sql query for prepared statement to delete an employee
        String sql4 = "UPDATE Employees SET DateOfJoining = ? WHERE EID = ?"; //sql query for prepared statement to update employee hiring date
        String sql5 = "SELECT * FROM Employees"; //sql query for result set to display employees & get the other data for updating, deleting, and inserting
        String sql6 = "SELECT count(EID) as EID FROM Employees"; //sql query for result set to count all the EID's and use this to tell user how many are in the DB

        try {
            // 1. Get a connection to database
            myConn = DriverManager.getConnection("jdbc:sqlite:HW2.db");

            // 2. Create a statement
            myStmt = myConn.createStatement(); //ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY wanted to do this to allow for error checking but not supported by SQLITE

            //3. Execute SQL Query
            myStmt.executeUpdate(sql);

            //4. Display Main Menu
            while(choice != 6){
                System.out.print(
                        "Main Menu\n(1) Add an employee\n(2) Drop an employee\n(3) Update the hiring date\n(4) Count employees\n(5) Display all employees\n(6) Exit\nEnter your choice: "
                );
                choice = s.nextInt();
                System.out.println();
                switch (choice){
                    case 1:
                        pstmt = myConn.prepareStatement(sql2);
                        myRs = myStmt.executeQuery(sql5);
                        addEmployee(pstmt, myRs);
                        break;
                    case 2:
                        pstmt = myConn.prepareStatement(sql3);
                        myRs = myStmt.executeQuery(sql5);
                        dropEmployee(pstmt, myRs);
                        break;
                    case 3:
                        pstmt = myConn.prepareStatement(sql4);
                        myRs = myStmt.executeQuery(sql5);
                        updateDate(pstmt, myRs);
                        break;
                    case 4:
                        myRs = myStmt.executeQuery(sql6);
                        countEmployees(myRs);
                        break;
                    case 5:
                        myRs = myStmt.executeQuery(sql5);
                        displayEmployees(myRs);
                        break;
                    case 6:
                        System.out.println("End!");
                        System.exit(0);
                }
            }

        }
        catch (Exception exc) {
            exc.printStackTrace();
        }
        finally {
            try{
                if(myStmt!=null)
                    myStmt.close();
            }catch(SQLException se2){
            }// nothing we can do

            try{
                if(myConn!=null)
                    myConn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try

            try{
                if(myRs!=null)
                    myRs.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }

    }
    public static void addEmployee(PreparedStatement pstmt, ResultSet myRs){
        int id, checker;
        String fname, lname, date;
        try {
            System.out.print("EID = ");
            id = s.nextInt(); //gets ID from user
            while (myRs.next()) { //goes through rows to make sure the id isn't already in there
                checker = myRs.getInt("EID");
                while (id == checker) {
                    System.out.print("EID should be unique. Enter another EID\nEID = ");
                    id = s.nextInt();
                }
            }
            System.out.print("First Name = ");
            fname = s.next(); //gets first name from user
            System.out.print("Last Name = ");
            lname = s.next(); //gets last name from user
            System.out.print("Date of employment = ");
            date = s.next(); //gets date from user as a string
            pstmt.setInt(1,id); //inputs all the data
            pstmt.setString(2,fname);
            pstmt.setString(3,lname);
            pstmt.setString(4,date);
            pstmt.executeUpdate();
            System.out.println("Employee Added.\n");
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void dropEmployee(PreparedStatement pstmt, ResultSet myRs){
        int id, checker;
        String lname, fname, date;
        try {
            System.out.print("EID = ");
            id = s.nextInt(); //gets ID from user
            //while(notInDB == 0) {
                while (myRs.next()) {
                    checker = myRs.getInt("EID"); //gets all the value in each row
                    fname = myRs.getString("fName");
                    lname = myRs.getString("lName");
                    date = myRs.getString("DateOfJoining");
                    if (id == checker) { //if the id is equal to the current result drop the employee
                        pstmt.setInt(1, id);
                        pstmt.executeUpdate();
                        System.out.println("The following employee dropped:");
                        System.out.println(checker + ", " + fname + ", " + lname + ", " + date);
                    }
                }
                /*if (notInDB == 0) { //wanted to include this for error checking but myRs.beforefirst() to start checking again isn't supported in SQLITE
                    System.out.print("EID is not in the database. Enter a new EID.\nEID =  ");
                    id = s.nextInt(); //gets id from user
                    myRs.first();
                }
            }*/
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void updateDate(PreparedStatement pstmt, ResultSet myRs){
        int id, checker, notInDB = 0;
        String lname, fname, date;
        try {
            System.out.print("EID = ");
            id = s.nextInt(); //gets id from user
            while (myRs.next()) {
                checker = myRs.getInt("EID");
                fname = myRs.getString("fName");
                lname = myRs.getString("lName");
                date = myRs.getString("DateOfJoining");
                if (id == checker) { //if id matches current result print their info, ask for new hiring date, and update the hiring date
                    System.out.println(checker + ", " + fname + ", " + lname + ", " + date);
                    System.out.print("New hiring date = ");
                    date = s.next();
                    pstmt.setString(1, date);
                    pstmt.setInt(2, id);
                    pstmt.executeUpdate();
                }
            }
                //error checking didn't work
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void countEmployees(ResultSet myRs){ //uses query that counts the rows
        int count;
        try {
            while (myRs.next()) {
                count= myRs.getInt("EID"); //gets the count from the query and prints it to the user
                System.out.println("There are " + count + " employees in the database\n ");
            }
        }
        catch(SQLException se){
            se.printStackTrace();
        }
    }
    public static void displayEmployees(ResultSet myRs){ //uses query that select * to display all the employees
        int checker;
        String lname, fname, date;
        try {
            System.out.println("Employee List:");

            while (myRs.next()) {
                checker = myRs.getInt("EID");
                fname = myRs.getString("fName");
                lname = myRs.getString("lName");
                date = myRs.getString("DateOfJoining");

                System.out.println(checker + ", " + fname + ", " + lname + ", " + date);
            }
            System.out.println();
        }
        catch(SQLException se){
                se.printStackTrace();
            }
    }
}