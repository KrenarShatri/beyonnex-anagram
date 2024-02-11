package org.example;

import java.util.ArrayList;
import java.util.HashMap;

public class Anagrams {


    enum StorageType {
        Dictionary,
        Graph,
    }

    private static StorageType storageType = StorageType.Dictionary;

    public static void setStorageType(String type) {
        switch (type) {
            case "2":
                storageType = StorageType.Graph;
                break;
            default:
                storageType = StorageType.Dictionary;
                break;
        }
    }

    public static void reset() {
        switch (storageType) {
            case Graph:
                Graph.reset();
                break;
            default:
                Dictionary.reset();
                break;
        }
    }

    public static void add(String a, String b) {
        if (!areAnagrams(a, b)) return;

        // Either use a dictionary or a Graph
        switch (storageType) {
            case Dictionary:
                Dictionary.add(a, b);
            case Graph:
                Graph.add(a, b);
        }
    }

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

    public static ArrayList<String> get(String a) {
        switch (storageType) {
            case Dictionary:
                return Dictionary.get(a);
            case Graph:
                return Graph.get(a);
        }
        throw new RuntimeException();
    }

    public static ArrayList<String> getAll() {
        switch (storageType) {
            case Dictionary:
                return Dictionary.getAll();
            case Graph:
                return Graph.getAll();
        }
        throw new RuntimeException();
    }
}
