
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Paths;

import static junit.framework.TestCase.fail;

public class WordSearchTest {

    WordSearch subject;
    String puzzleFilePath;

    @Before
    public void setup() throws Exception {
        subject = new WordSearch();

        URL resource = WordSearch.class.getResource("wordsearch.csv");
        puzzleFilePath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
    }



    @Test
    public void canReadCSVFileFromProvidedFilePath() {
        try {
            subject.readFile(puzzleFilePath);
        } catch (Exception e) {
            fail("Application threw an exception attempting to read puzzle file.");
        }
    }



}
