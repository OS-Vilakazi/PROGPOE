
package com.mycompany.progpoepartone;

import java.util.Scanner;

public class ProgPoePartOne {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Login valid = new Login();
        
        
        //Declaring Variables
        String username, password, phoneNum, firstName, lastName;
        
        //Header
        System.out.println("༺ Sign Up ༻");
        
        //inputing the users name and last name
        System.out.println("Please enter your First Name");
        firstName = input.nextLine();
        
        System.out.println("Please enter your Last Name");
        lastName = input.nextLine();
        
        //Registering a valid username
        System.out.println("Please enter a Valid username");
        username = input.nextLine();
        
        //Validate
        
        while (!valid.checkUserName(username)) {
            System.out.println("Username is not correctly formatted; please ensure that your username contains an underscore and is no more than five characters in length.");
            System.out.print("Enter Username: ");
            username = input.nextLine();
        }
        System.out.println("Username successfully captured.");
        
        //Password Registration
        System.out.println("Please create a Valid Password it must conain:\n\u2022 at least 8 characters long\n\u2022 Contain a capital letter\n\u2022 Contain a number\n\u2022 Contain a special Character.");
        password = input.nextLine();
        
        //Validate 
        while (!valid.checkPasswordComplexity(password)) {
            System.out.println("Password is not correctly formatted; please ensure that the password contains at least 8 characters, a capital letter, a number, and a special character.");
            System.out.print("Enter Password: ");
            password = input.nextLine();
        }
        System.out.println("Password successfully captured.");
        
        System.out.println("Please enter a Valid Cell phone number. ");
        phoneNum = input.nextLine();
        
        //Validate
        while (!valid.checkCellPhone(phoneNum)) {
            System.out.println("Cell phone number incorrectly formatted or does not contain international code.");
            System.out.print("Try again: ");
            phoneNum = input.nextLine();
        }
        System.out.println("Cell Phone number successfully added.");
        
        //Login VERIFICATION
        System.out.println("\n LOGIN");
        System.out.print("Enter username: ");
        String loginUser = input.nextLine();
        
        System.out.print("Enter password: ");
        String loginPass = input.nextLine();
        
        //Verifying the login info
        if (valid.loginUser(username, password, loginUser, loginPass)) {
        System.out.println("Welcome " + firstName + " " + lastName + " it is great to see you again.");
        } else {
            System.out.println("Username or password incorrect, please try again.");
        }
        
    }
}
