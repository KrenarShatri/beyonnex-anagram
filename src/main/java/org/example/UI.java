package org.example;

import java.util.Scanner;

public class UI {

    public static void run() {
        Scanner scanner = new Scanner(System.in);
        String line = "";
        System.out.println("Choose storage option:\n[1]Dictionary (default)\n[2]Graph");
        line = scanner.nextLine();
        Anagrams.setStorageType(line);

        do {
            System.out.println("1. Show all anagrams\n2. Show anagrams for: \n3. Add new Anagram\n[x]Exit");
            line = scanner.nextLine();

            if (line.equals("1")) {
                System.out.println(Anagrams.getAll());
            } else if (line.equals("2")) {
                System.out.println("[<]Back\nShow anagrams for:");
                line = scanner.nextLine();
                if (line.equals("<")) continue;
                System.out.println(Anagrams.get(line));
            } else if (line.equals("3")) {
                do {
                    System.out.println("[<]Back\nAdd two words with space:");
                    line = scanner.nextLine();
                    if (line.equals("<")) continue;
                    String[] words = line.split(" ");
                    Anagrams.add(words[0], words[1]);
                }
                while (!line.equals("<"));
            }

        } while (!line.equals("x"));
    }
}
