
package com.mycompany.progpoepartone;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Validation {
    
       public boolean checkUserName (String username){
        
        //declare a variable to store the pattern 
        String regex = "^(?=.*_).{1,5}$";
        
        //pattern object to compile the regex
        Pattern pattern = Pattern.compile(regex);
        
        //matcher object to match the Username to the regex
        Matcher matcher = pattern.matcher(username);
        
        //return true if pattern matches Username
        return matcher.matches();
       }
        
        public boolean checkPasswordComplexity(String password){
        
            //declare a variable to store the pattern
            String regex = "^(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()]).{8,}$";
            
            //pattern object to compile the regex
            Pattern pattern = Pattern.compile(regex);
            
            //matcher object to match the password to the regex
            Matcher matcher = pattern.matcher(password);
        
            //return true if pattern matches password
            return matcher.matches();
            }
        public Boolean checkCellPhone(String cellPhone) {
         //Starts with '+' and total length <= 10
        String regex = "^\\+(?=.{1,9}$)[0-9]+$";
    
        //pattern object to compile the regex
        Pattern pattern = Pattern.compile(regex);
        
        //matcher object to match the cellphone to the regex
        Matcher matcher = pattern.matcher(cellPhone);
        
        //return true if pattern matches password
        return matcher.matches();
        }
        public boolean loginUser(String storedUser, String storedPass, String loginUser, String loginPass) {
        return loginUser.equals(storedUser) && loginPass.equals(storedPass);
        }
    }
       
       


    

