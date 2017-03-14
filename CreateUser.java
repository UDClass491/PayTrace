/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cps491_paytrace;

/**
 *
 * @author Logan
 */
class CreateUser {
    static String CustID = "Bob1234";
    static String BName = "Bob Smith";
    static String CC = "4012881888818888";
    static String ExpMnth = "12";
    static String ExpYr = "20";
    
    public static String getUserInfo(){
        String returnString = "|CustID~Bob1234|BName~Bob Smith|CC~4012881888818888|ExpMnth~12|ExpYr~20|";
        return returnString;
    }
}
