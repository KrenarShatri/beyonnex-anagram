package org.example;

import java.util.*;

public class Graph {
    // Bidirectional graph
    public static HashMap<String, HashSet<String>> nodes = new HashMap<>();

    public static void reset() {
        nodes = new HashMap<>();
    }

    public static void add(String a, String b) {
        if (nodes.containsKey(a)) {
            // Runtime complexity: O(1)
            nodes.get(a).add(b);
            nodes.put(b, new HashSet<>(Arrays.asList(a)));
        } else if (nodes.containsKey(b)) {
            // Runtime complexity: O(1)
            nodes.get(b).add(a);
            nodes.put(a, new HashSet<>(Arrays.asList(b)));
        } else {
            // They are new words and can not be attached to some existing node
            // Go through all the nodes and attach to the one that is an anagram of A or B

            nodes.put(a, new HashSet<>(Arrays.asList(b)));
            nodes.put(b, new HashSet<>(Arrays.asList(a)));

            // Heavy operation
            // Runtime complexity: O(|V| * N), where
            // |V| - number of nodes/vertices
            // N - length(a)
            for (String s : nodes.keySet()) {
                if (Anagrams.areAnagrams(a, s)) {
                    nodes.get(s).add(a);
                    nodes.get(a).add(s);
                }
            }
        }
    }

    public static ArrayList<String> get(String a) {
        if (nodes.containsKey(a)) return new ArrayList<>(anagramsFor(a));
        else {
            // Heavy operation
            // Runtime complexity: O(|V| * N), where
            // |V| - number of nodes/vertices
            // N - length(a)
            for (String s : nodes.keySet()) {
                if (Anagrams.areAnagrams(a, s)) {
                    nodes.get(s).add(a);
                    nodes.put(a, new HashSet<>(Arrays.asList(s)));
                    return new ArrayList<>(anagramsFor(a));
                }
            }
        }

        return new ArrayList<>();
    }

    public static ArrayList<String> getAll() {
        ArrayList<String> result = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();

        for (String s : nodes.keySet()) {
            if (!visited.contains(s)) {
                visited.add(s);
                HashSet<String> allAnagramsForS = anagramsFor(s);
                visited.addAll(allAnagramsForS);
                result.add(s + " -> " + allAnagramsForS);
            }
        }

        return result;
    }

    public static HashSet<String> anagramsFor(String a) {
        HashSet<String> visited = new HashSet<>();
        HashSet<String> allAnagrams = new HashSet<>(DFS(a, visited));
        allAnagrams.remove(a);
        return allAnagrams;
    }

    public static HashSet<String> DFS(String currentNode, HashSet<String> visited) {
        if (visited.contains(currentNode)) return new HashSet<>();

        HashSet<String> newSet = new HashSet<>(List.of(currentNode));
        visited.add(currentNode);

        for (String s : nodes.get(currentNode)) {
            newSet.addAll(Graph.DFS(s, visited));
        }

        return newSet;
    }
}
