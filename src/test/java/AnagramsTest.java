import static org.junit.jupiter.api.Assertions.assertEquals;

import org.example.Anagrams;
import org.junit.jupiter.api.Test;

public class AnagramsTest {

    @Test
    void happyPath() {
        assertEquals(true, Anagrams.areAnagrams("aabb", "abab"));
    }

    @Test
    void differentLength() {
        assertEquals(false, Anagrams.areAnagrams("ab", "aabb"));
    }

    @Test
    void sameWord() {
        assertEquals(false, Anagrams.areAnagrams("abc", "abc"));
    }

}
