package com.ivy.javajokelib;

import java.util.Random;

//Library class to provide jokes for MainActivity
public class MyJokes {
    public String getJoke(){
        //jokes pulled from http://prof.johnpile.com/
        //get a random joke and return to the caller
        Random rand = new Random();
        int  number = rand.nextInt(4) + 1;
        if(number==1){
            return "joke1: Why is it that women find C to be more attractive than Java?\n" +
                    "Because C doesn’t treat them like objects.";
        }else if(number==2){
            return "joke2: Why do Java programmers wear glasses?\n" +
                    "Because they don’t C#!";
        }else if(number==3){
            return "joke3: There’s no place like 127.0.0.1";
        }
        else {
            return "joke4: Knock, knock.\n" +
                    "Race condition.\n" +
                    "Who’s there.";
        }
    }
}
