package com.example;

import java.io.*;
import java.net.*;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.util.*;

public class App {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String risposta = "";
            int answer;
            do {
                Scanner input = new Scanner(System.in);
                System.out.println("1) Scegli una lettera \n2) Prova ad indovinare la parola \n3) Disconnettiti");
                int option = input.nextInt();
                out.writeBytes(option + "\n");

                if (option == 1) {
                    String lettera;
                    out.writeBytes("1" + "\n");
                    System.out.print("Inserisci la lettera : ");
                    lettera = input.nextLine();
                    out.writeBytes(lettera + "\n");
                    risposta = in.readLine();
                    System.out.println(risposta);
                }
                if (option == 2) {
                    String lettera;
                    out.writeBytes("2" + "\n");
                    System.out.print("Inserisci la parola : ");
                    lettera = input.nextLine();
                    out.writeBytes(lettera + "\n");
                    risposta = in.readLine();
                    if (risposta.equals("giusto")) {
                        System.out.println("Hai indovinato ");
                        break;
                    } else if (risposta.equals("sbagliato")) {
                        System.out.println("Hai sbagliato ");
                    }
                }
                answer = option;
            } while (risposta.equals("giusto") || answer != 3);

            System.out.println("Connection closed");
            socket.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }
}
