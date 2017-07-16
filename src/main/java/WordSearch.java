import java.io.*;

public class WordSearch {

    String[] wordList = null;

    public WordSearch() {

    }

    public void readFile(String filepath) throws FileNotFoundException, IOException {
        File puzzleFile = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzleFile));

        String wordLine = bufferedReader.readLine();

        if (wordLine != null && !wordLine.isEmpty()) {
            wordList = wordLine.split(",+");
        }
    }

    public String[] getWordList() {
        return wordList;
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();


    }
}