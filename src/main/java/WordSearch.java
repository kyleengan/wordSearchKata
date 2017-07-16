import java.io.*;
import java.util.ArrayList;

public class WordSearch {

    String[] wordList = null;
    ArrayList<String[]> puzzleText = null;

    public WordSearch() {

    }

    public void readFile(String filepath) throws FileNotFoundException, IOException {
        File puzzleFile = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzleFile));

        String wordLine = bufferedReader.readLine();
        String puzzleLine;

        if (wordLine != null && !wordLine.isEmpty()) {
            wordList = wordLine.split(",+");
        }

        puzzleText = new ArrayList<>();
        while ((puzzleLine = bufferedReader.readLine()) != null)
        {
            if (!puzzleLine.isEmpty()) {
                puzzleText.add(puzzleLine.split(",+"));
            }
        }
    }

    public String[] getWordList() {
        return wordList;
    }

    public ArrayList<String[]> getPuzzleText() {
        return puzzleText;
    }



    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();


    }
}