package edu.ksu.election;

import java.rmi.Naming;
import java.util.Locale;
import java.util.Scanner;

public class ElectionClient {
    public static void main(String[] args) {
        String host = (args.length > 0) ? args[0] : "localhost";
        String url = "rmi://" + host + "/ElectionService";
        try {
            Election election = (Election) Naming.lookup(url);
            System.out.println("Connected to " + url);
            System.out.println("Commands: vote A | vote B | result | exit");
            try (Scanner sc = new Scanner(System.in)) {
                while (true) {
                    System.out.print("> ");
                    String line = sc.nextLine().trim();
                    if (line.equalsIgnoreCase("exit")) break;
                    if (line.toLowerCase(Locale.ROOT).startsWith("vote ")) {
                        String cand = line.substring(5).trim();
                        election.castVote(cand);
                        System.out.println("OK");
                    } else if (line.equalsIgnoreCase("result")) {
                        int[] r = election.getResult();
                        System.out.println("Results: A=" + r[0] + ", B=" + r[1]);
                    } else {
                        System.out.println("Unknown command");
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Client error: " + e);
            e.printStackTrace();
        }
    }
}
