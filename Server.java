/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpclientserverapp;

import java.net.*;
import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author cemreaka
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket server = null;
        try {
            server = new ServerSocket(1234);
            Socket client = server.accept();
            System.out.println("Client connected: " + client.getInetAddress().getHostAddress());
            InputStream clientIn = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(clientIn));
            String clientMessage = "";
            clientMessage = br.readLine();
            System.out.println("Client: " + clientMessage);
            ArrayList<Integer> numbers = convertMessage(clientMessage);
            SharedLocation sl = new SharedLocation(3);
            SumThread t1 = new SumThread(numbers.get(0), numbers.get(1), numbers.get(2), sl);  // Since the client is sending 9 numbers specifically, I directly added these numbers
            SumThread t2 = new SumThread(numbers.get(3), numbers.get(4), numbers.get(5), sl);
            SumThread t3 = new SumThread(numbers.get(6), numbers.get(7), numbers.get(8), sl);
            ExecutorService thread_executor = Executors.newCachedThreadPool();
            thread_executor.execute(t1);
            thread_executor.execute(t2);
            thread_executor.execute(t3);
            thread_executor.shutdown();
            Thread.sleep(500);
            OutputStream clientOut = client.getOutputStream();
            PrintWriter pw = new PrintWriter(clientOut, true);
            int totalSum = sl.totalSum();
            System.out.println("Total Sum: " + totalSum);
            String ansMsg = "Total sum is " + totalSum;
            pw.println(ansMsg);
            client.close();

        } catch (IOException ie) {
            ie.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    private static ArrayList<Integer> convertMessage(String msgFromClient) {
        ArrayList<Integer> arr = new ArrayList<>();
        String[] msg = msgFromClient.split(" ");
        for (int i = 0; i < msg.length; i++) {
            arr.add(Integer.parseInt(msg[i]));
            if (arr.size() >= 9) {  //assumed that the client has to send 9 numbers
                break;
            }
        }

        return arr;
    }

}
