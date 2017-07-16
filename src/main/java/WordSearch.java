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

    public String searchWords() {
        String solution = "";

        for (String word : wordList) {
            String firstChar = "" + word.charAt(0);
            String foundLine = "";

            for (int y = 0; y < puzzleText.size(); y++) {
                String[] line = puzzleText.get(y);
                for (int x = 0; x < line.length; x++) {
                    if (firstChar.equals(line[x])) {
                        foundLine = searchHorizontallyForward(word, x, y);
                        foundLine = foundLine.isEmpty() ? searchHorizontallyBackward(word,x,y) : foundLine;
                    }

                    if (!foundLine.isEmpty()) {
                        solution += foundLine + System.lineSeparator();
                        foundLine = "";
                        break;
                    }
                }
            }
        }

        return solution;
    }

    private String searchHorizontallyForward(String word, int startX, int startY) {
        String[] line = puzzleText.get(startY);
        if (startX + word.length() > line.length) {
            return "";
        }

        String returnVal = word + ": ";

        for (int x = 0; x < word.length(); x++) {
            if ((word.charAt(x) + "").equals(line[x + startX])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(x + startX, startY);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }

    private String searchHorizontallyBackward(String word, int startX, int startY) {
        String[] line = puzzleText.get(startY);
        if (startX - word.length() < 0) {
            return "";
        }

        String returnVal = word + ": ";

        for (int x = 0; x < word.length(); x++) {
            if ((word.charAt(x) + "").equals(line[startX - x])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX - x, startY);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }



    public String letterAt(int x, int y) {
        return puzzleText.get(y)[x];
    }

    public String[] getWordList() {
        return wordList;
    }

    public ArrayList<String[]> getPuzzleText() {
        return puzzleText;
    }

    public String getCoordinates(int x, int y) {
        return "(" + x + "," + y + ")";
    }



    public static void main(String[] args) {
        WordSearch wordSearch = new WordSearch();


    }
}