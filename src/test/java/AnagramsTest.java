import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Anagrams;
import org.example.Dictionary;
import org.example.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

public class AnagramsTest {

    @BeforeEach
    void clear() {
        Anagrams.reset();
        Anagrams.setStorageType("1");
    }

    @Test
    void areAnagrams() {
        assertEquals(true, Anagrams.areAnagrams("aabb", "abab"));
    }

    @Test
    void useDictionaryByDefault() {
        Anagrams.add("aabb", "abab");
        assertEquals(List.of("aabb -> [abab]"), Anagrams.getAll());
        assertEquals(List.of("aabb -> [abab]"), Dictionary.getAll());
    }

    @Test
    void setStorageToGraph() {
        Anagrams.setStorageType("2");
        Anagrams.add("aabb", "abab");
        assertEquals(List.of("aabb -> [abab]"), Anagrams.getAll());
        assertEquals(List.of("aabb -> [abab]"), Graph.getAll());
    }

    @Test
    void differentLength() {
        Anagrams.add("aabb", "ab");
        assertEquals(new ArrayList<>(), Anagrams.getAll());
    }

    @Test
    void sameWord() {
        Anagrams.add("abc", "abc");
        assertEquals(new ArrayList<>(), Anagrams.getAll());
    }

}
