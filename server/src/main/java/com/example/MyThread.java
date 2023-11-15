package com.example;
import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class MyThread extends Thread{
    Socket client;

    public MyThread(Socket socket) {
        this.client = socket;
    }

    public void run(){
        Random rand = new Random();
        int randomNumber = rand.nextInt(101);
        System.out.println("Random Number: " + randomNumber);
        try{
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());   
            
            System.out.println("client connected");
            int clientGuess = -1;
            int guessCounter = 0;
            do{
                clientGuess = Integer.parseInt(in.readLine());
                guessCounter++;
                System.out.println("guess number " + guessCounter);
                System.out.println("number received from client: " + clientGuess);
                out.writeBytes((clientGuess < randomNumber ? 
                                "1" : 
                                clientGuess > randomNumber ?
                                "2" :
                                "3") + '\n');
            }while(clientGuess != randomNumber);
            System.out.println("client guessed correctly in " + guessCounter + " tries");
            client.close();
        }catch(Exception e){
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}
