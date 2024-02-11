package org.example;

import java.util.*;

public class Dictionary {

    // ("abc", "acb", "bca") -> ("abc" -> ("abc", "acb", "bca"))
    private static HashMap<String, HashSet<String>> dictionary = new HashMap<>();

    public static void reset() {
        dictionary = new HashMap<>();
    }

    public static void add(String a, String b) {
        // We assume that A and B are anagrams
        // Use the sorted string as the key
        // All anagrams will return the same sorted string
        char[] charArrayA = a.toCharArray();
        // O(N*log(N)), where N is length(A)
        Arrays.sort(charArrayA);
        String sortedA = new String(charArrayA);

        if (dictionary.containsKey(sortedA)) dictionary.get(sortedA).addAll(List.of(a, b));
        else dictionary.put(sortedA, new HashSet(List.of(a, b)));
    }

    public static ArrayList<String> get(String a) {
        char[] charArrayA = a.toCharArray();
        Arrays.sort(charArrayA);
        String sortedA = new String(charArrayA);

        if (!dictionary.containsKey(sortedA)) return new ArrayList<>();

        ArrayList<String> result = new ArrayList<>();

        for (String s : dictionary.get(sortedA)) {
            if (!s.equals(a)) result.add(s);
        }

        return result;
    }
}
