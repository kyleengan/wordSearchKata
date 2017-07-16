import java.io.*;

public class WordSearch {

    public WordSearch() {

    }

    public void readFile(String filepath) throws FileNotFoundException {
        File puzzleFile = new File(filepath);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(puzzleFile));
    }

    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();


    }
}