/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpclientserverapp;

import java.io.*;
import java.net.*;

/**
 *
 * @author cemreaka
 */
public class Client {

    public static void main(String args[]) {

        Socket client = null;
        try {
            client = new Socket("localhost", 1234);
            String msg = "";
            OutputStream clientOut = client.getOutputStream();
            BufferedReader br;
            try (PrintWriter pw = new PrintWriter(clientOut, true)) {
                InputStream clientIn = client.getInputStream();
                br = new BufferedReader(new InputStreamReader(clientIn));
                BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Enter 9 numbers...");
                msg = stdIn.readLine();
                pw.println(msg);
                System.out.println("Server: " + br.readLine());
            }

            br.close();
        } catch (IOException ie) {
            ie.printStackTrace();
        } finally {
            try {
                client.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}
