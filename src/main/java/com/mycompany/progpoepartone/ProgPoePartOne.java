
package com.mycompany.progpoepartone;

import java.util.Scanner;

public class ProgPoePartOne {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Validation valid = new Validation();
        
        //Declaring Variables
        String username, password, phoneNum;
        
        System.out.println("Please enter a Valid username");
        username = input.nextLine();
        
        //Validate
        
        if(valid.checkUserName(username)){
            System.out.println("Username succesfully captured.");
        }else{
            System.out.println("Username is not correctly formatted; please ensurethat your username contains an underscore and is no more than five characters in length.");
        }
        
        System.out.println("Please create a Valid Password it must conain:\n\u2022 at least 8 characters long\n\2022 Contain a capital letter\n\2022 Contain a number\n\2022 Contain a special Character.");
        password = input.nextLine();
        
        //Validate 
        if (valid.checkPasswordComplexity(password)){
            System.out.println("Password successfully captured.");
        }else{
            System.out.println("Password is not correctly formatted;plase ensure that the password contains at least 8 charaters ,a capital letter,a num,ber and a special character.");
        }
        
        System.out.println("Please enter a Valid Cell phone number. ");
        phoneNum = input.nextLine();
        
        //Validate
        if (valid.checkCellPhone(phoneNum)){
            System.out.println("Cell Phone number successfully added.");
        }else{System.out.println("Cell phone number incorrectly formatted or does not contain international code");
        }
    }
}
