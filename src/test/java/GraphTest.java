import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Dictionary;
import org.example.Graph;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class GraphTest {

    @BeforeEach
    void clear() {
        Graph.reset();
    }

    void happyPath() {
        Graph.add("abc", "cba");
        Graph.add("cba", "cab");

        // HashSet order is not guaranteed, so we sort the result
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("abc", "cba", "cba", "cab"));
        ArrayList<String> actual = Graph.get("abc");
        expected.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());

        assertEquals(expected, actual);
    }

    @Test
    void whenAnagramsAreNotConnected() {
        Graph.add("abc", "cba");
        Graph.add("acb", "cab");

        // HashSet order is not guaranteed, so we sort the result
        ArrayList<String> expected = new ArrayList<>(Arrays.asList("abc", "cba", "acb", "cab"));
        ArrayList<String> actual = Graph.get("bac");
        expected.sort(Comparator.naturalOrder());
        actual.sort(Comparator.naturalOrder());

        assertEquals(expected, actual);
    }
}
