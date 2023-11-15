package com.example;

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class MyThread extends Thread {
    Socket client;

    public MyThread(Socket socket) {
        this.client = socket;
    }

    public void run() {
        Random rand = new Random();
        int randomNumber = rand.nextInt(0, 4);
        String[] parole = { "lasagna", "bologna", "catamarano", "informatica", "incendio" };
        String parolaScelta = parole[randomNumber];
        int lunghezzaParola = parolaScelta.length();

        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            System.out.println("client connected");
            System.out.println("parola scelta : " + parolaScelta);
            System.out.println("indovina la parola di " + lunghezzaParola + " lettere");

            for (int i = 0; i < lunghezzaParola; i++) {
                System.out.print("* ");
            }
            System.out.println("\n");

            String optionGame = in.readLine();

            int guessCounter = 0;
            do {

                if (optionGame.equals("1")) {
                    String lettera = in.readLine();
                    if (parolaScelta.contains(lettera)) {
                        out.writeBytes("lettera presente");
                    }
                    guessCounter++;

                }
                if (optionGame.equals("2")) {
                    System.out.println("option : " + optionGame);
                    String lettera = in.readLine();
                    if (parolaScelta.equals(lettera)) {
                        out.writeBytes("giusto");
                        System.out.println("client ha indovinato in " + guessCounter + " tentativi la parola [ "
                                + parolaScelta + " ]");
                                guessCounter++;
                    } else {
                        out.writeBytes("sbagliato");
                        guessCounter++;
                    }
                }

                guessCounter++;
            } while (optionGame != "3");
            System.out.println("Connessione chiusa");
            client.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Error during server instance");
            System.exit(1);
        }
    }
}
