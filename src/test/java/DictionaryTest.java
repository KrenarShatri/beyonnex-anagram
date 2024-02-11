import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Anagrams;
import org.example.Dictionary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class DictionaryTest {

    @BeforeEach
    void clear() {
        Dictionary.reset();
    }

    @Test
    void happyPath() {
        Dictionary.add("abc", "cba");
        Dictionary.add("acb", "cab");

        // HashSet order is not guaranteed, so we sort the result
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("abc", "cba", "acb", "cab"));
        ArrayList<String> actual = Dictionary.get("bac");
        expected.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());

        assertEquals(expected, actual);
    }

    @Test
    void differentStrings() {
        // The dictionary doesn't check if they are anagrams
        // It just stores
        Dictionary.add("cba", "xyz");
        assertEquals(List.of("xyz"), Dictionary.get("cba"));
    }

}
