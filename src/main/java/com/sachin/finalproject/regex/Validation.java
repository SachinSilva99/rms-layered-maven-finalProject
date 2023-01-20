package com.sachin.finalproject.regex;


import com.sachin.finalproject.regex.Validates;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    private static Pattern price = Pattern.compile("^([0-9]\\d*(\\.\\d*[0-9])?|0\\.\\d*[0-9]+)|\\d+(\\.\\d*[0-9])?$");
    private static Pattern qtyPattern = Pattern.compile("^[0-9]{1,50}$");
    private static Pattern namePattern = Pattern.compile("^[a-zA-Z]{1,50}$");
    private static Pattern nic = Pattern.compile("^[0-9]{12}|[0-9]{9}[V|v]$");
    private static Pattern newItemName = Pattern.compile("^[a-zA-Z\\s+]{1,50}$");
    private static Pattern address = Pattern.compile("([a-zA-Z|0-9|\\s+]{3,})");
    private static Pattern phoneNumberP = Pattern.compile("^[0|+94][77|76|71|70|78|74|75|][0-9]{8}$");
    private static Pattern salary = Pattern.compile("^[0-9]{3,}$");

    public static boolean match(String field, Validates validate){
        switch (validate){
            case NIC:{
                Matcher matcher = nic.matcher(field);
                return matcher.matches();
            }
            case NAME:{
                Matcher matcher = namePattern.matcher(field);
                return matcher.matches();
            }
            case SALARY:{
                Matcher matcher = salary.matcher(field);
                return matcher.matches();
            }
            case ADDRESS:{
                Matcher matcher = address.matcher(field);
                return matcher.matches();
            }
            case PHONE_NUMBER:{
                Matcher matcher = phoneNumberP.matcher(field);
                return matcher.matches();
            }
            case NEW_ITEM_NAME:{
                Matcher matcher = newItemName.matcher(field);
                return matcher.matches();
            }
            case QTY:{
                Matcher matcher = qtyPattern.matcher(field);
                return  matcher.matches();
            }
            case PRICE:{
                Matcher matcher = price.matcher(field);
                return matcher.matches();
            }
            default:{
                return false;
            }
        }
    }
}
