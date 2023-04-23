package test;
import java.sql.*;
import java.util.Scanner;
import java.lang.*;


public class login {
    static String email;
    static int isCAP = 0;
    public static void login() {
        try {
            String password, checks, checkE, checkDate;
            int notInDB = 0, getPass = 1;

            System.out.println("\nWelcome to the Detroit Mercy esports application! You must login to enter. \nEmail: ");
            email = esports.s.next();
            esports.myRs = esports.myStmt.executeQuery(esports.checkEmail);
            while (esports.myRs.next()) {
                checks = esports.myRs.getString("Email");
                if (checks.equals(email)) {
                    notInDB = 0;
                    break;
                } else notInDB = 1;
            }
            if (notInDB == 1) {
                System.out.println("Email was not found. Please contact esports administrator.");
                System.exit(0);
            }
            while (getPass == 1) { //loops until password is correct
                System.out.println("Password: ");
                password = esports.s.next();
                esports.myRs = esports.myStmt.executeQuery(esports.checkPassword);
                while (esports.myRs.next() && getPass == 1) {
                    checks = esports.myRs.getString("Password");
                    if (checks.equals(password)) {
                        getPass = 0;
                    }
                }
                if (getPass == 1) {
                    System.out.println("Password Incorrect! Please try again. ");
                }
            }
            esports.myRs = esports.myStmt.executeQuery(esports.viewCaptains);
            while (esports.myRs.next()) {
                checkE = esports.myRs.getString("Email");
                checkDate = esports.myRs.getString("endDate"); //makes sure there is no end date
                if (email.equals(checkE) && checkDate == null) {
                    isCAP = 1;
                    break;
                }
            }
        }catch (SQLException se) {
            se.printStackTrace();
    }
    }
}
