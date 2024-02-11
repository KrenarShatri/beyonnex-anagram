package org.example;

import java.util.HashMap;

public class Anagrams {

    public static boolean areAnagrams(String a, String b) {
        if (a.length() != b.length() || a.equals(b)) return false;

        HashMap<Character, Integer> aHashMap = new HashMap<>();

        // Example
        // "aabbc" -> {"a": 2, "b": 2, "c": 1}
        for (char c : a.toCharArray()) {
            if (aHashMap.containsKey(c)) aHashMap.put(c, aHashMap.get(c) + 1);
            else aHashMap.put(c, 1);
        }

        for (char c : b.toCharArray()) {
            if (!aHashMap.containsKey(c) || aHashMap.get(c) < 1) return false;

            aHashMap.put(c, aHashMap.get(c) - 1);
        }

        return true;
    }
}
