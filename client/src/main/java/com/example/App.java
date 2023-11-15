package com.example;
import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.*;

public class App 
{
    public static void main( String[] args )
    {
        try{
            Socket socket = new Socket("localhost", 3000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String answer = "";
            do{
                Scanner input = new Scanner(System.in);
                System.out.println("Insert number to guess");
                String numberToGuess = input.nextLine();

                out.writeBytes(numberToGuess + "\n");
                answer = in.readLine();
                System.out.println(answer.equals("1") ? 
                                    "number too small" : 
                                    answer.equals("2") ? 
                                    "number too big" : 
                                    "YOU GUESSED THE CORRECT NUMBER!!!!"); 
                
            }while(!answer.equals("3"));
            socket.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }
}
