
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

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

    }

    private void setPuzzleFilePath(String filename) {
        URL resource = WordSearch.class.getResource(filename + ".csv");
        try {
            puzzleFilePath = Paths.get(resource.toURI()).toFile().getAbsolutePath();
        } catch (Exception e) {
            fail("Could not find file " + filename + ".csv");
        }
    }


    @Test
    public void canReadCSVFileFromProvidedFilePath() {
        setPuzzleFilePath("wordsearch");

        try {
            subject.readFile(puzzleFilePath);
        } catch (Exception e) {
            fail("Application threw an exception attempting to read puzzle file.");
        }
    }

    @Test
    public void canReadWordListFromCSVFile() throws Exception {
        setPuzzleFilePath("wordsearch");
        String[] wordList = {"BONES","KHAN","KIRK","SCOTTY","SPOCK","SULU","UHURA","CHEKHOV"};

        subject.readFile(puzzleFilePath);

        Assert.assertArrayEquals(wordList, subject.getWordList());
    }

    @Test
    public void canReadWordListFromCSVFileWithExtraCommasBecauseReasons() throws Exception {
        setPuzzleFilePath("wordsearchMalformedInput");
        String[] wordList = {"BONES","KHAN","KIRK","SCOTTY","SPOCK","SULU","UHURA","CHEKHOV"};

        subject.readFile(puzzleFilePath);

        Assert.assertArrayEquals(wordList, subject.getWordList());
    }

    @Test
    public void cannotReadWordListWithNoWords() throws Exception {
        setPuzzleFilePath("wordsearchNoWords");

        subject.readFile(puzzleFilePath);

        Assert.assertNull(subject.getWordList());
    }

    @Test
    public void canReadPuzzleTextFromCSVFile() throws Exception {
        setPuzzleFilePath("wordsearch");
        String[] letterList = {"U","M","K","H","U","R","K","I","N","V","J","O","C","W","E"};

        subject.readFile(puzzleFilePath);

        Assert.assertArrayEquals(letterList, subject.getPuzzleText().get(0));
    }

    @Test
    public void canReadPuzzleTextFromCSVFileWithExtraCommasBecauseReasons() throws Exception {
        setPuzzleFilePath("wordsearchMalformedInput");
        String[] letterList = {"U","M","K","H","U","R","K","I","N","V","J","O","C","W","E"};

        subject.readFile(puzzleFilePath);

        Assert.assertArrayEquals(letterList, subject.getPuzzleText().get(0));
    }

    @Test
    public void canOutputCoordinatesTextFromCoordinates() throws Exception {
        Assert.assertEquals("(1,0)", subject.getCoordinates(1,0));
    }

    @Test
    public void canReadLetterFromGivenCoordinates() throws Exception {
        setPuzzleFilePath("wordsearch");

        subject.readFile(puzzleFilePath);

        Assert.assertEquals("M", subject.letterAt(1,0));
    }

    @Test
    public void canSearchHorizontallyForwardForWordAndOutputResult() throws Exception {
        setPuzzleFilePath("wordsearch");

        subject.readFile(puzzleFilePath);

        Assert.assertTrue(subject.searchWords().contains("SCOTTY: (0,5),(1,5),(2,5),(3,5),(4,5),(5,5)"));
    }

    @Test
    public void canSearchHorizontallyBackwardForWordAndOutputResult() throws Exception {
        setPuzzleFilePath("wordsearch");

        subject.readFile(puzzleFilePath);

        Assert.assertTrue(subject.searchWords(), subject.searchWords().contains("KIRK: (4,7),(3,7),(2,7),(1,7)"));
    }

}
