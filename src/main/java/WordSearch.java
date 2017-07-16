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
                        foundLine = foundLine.isEmpty() ? searchVerticallyDown(word,x,y) : foundLine;
                        foundLine = foundLine.isEmpty() ? searchVerticallyUp(word,x,y) : foundLine;
                        foundLine = foundLine.isEmpty() ? searchDiagonallyUpForward(word,x,y) : foundLine;
                        foundLine = foundLine.isEmpty() ? searchDiagonallyDownForward(word,x,y) : foundLine;
                        foundLine = foundLine.isEmpty() ? searchDiagonallyDownBackward(word,x,y) : foundLine;
                    }

                    if (!foundLine.isEmpty()) {
                        solution +=  System.lineSeparator() + foundLine;
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
            if ((word.charAt(x) + "").equals(line[startX + x])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX + x, startY);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }

    private String searchHorizontallyBackward(String word, int startX, int startY) {
        String[] line = puzzleText.get(startY);
        if (startX - word.length() < -1) {
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

    private String searchVerticallyDown(String word, int startX, int startY) {
        if (startY + word.length() > puzzleText.size()) {
            return "";
        }

        String returnVal = word + ": ";

        for (int y = 0; y < word.length(); y++) {
            String[] line = puzzleText.get(startY + y);

            if ((word.charAt(y) + "").equals(line[startX])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX, startY + y);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }

    private String searchVerticallyUp(String word, int startX, int startY) {
        if (startY - word.length() < -1) {
            return "";
        }

        String returnVal = word + ": ";

        for (int y = 0; y < word.length(); y++) {
            String[] line = puzzleText.get(startY - y);

            if ((word.charAt(y) + "").equals(line[startX])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX, startY - y);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }

    private String searchDiagonallyUpForward(String word, int startX, int startY) {
        if (startX + word.length() > puzzleText.get(startY).length) {
            return "";
        }
        if (startY - word.length() < -1) {
            return "";
        }

        String returnVal = word + ": ";

        for (int y = 0; y < word.length(); y++) {
            String[] line = puzzleText.get(startY - y);

            if ((word.charAt(y) + "").equals(line[startX + y])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX + y, startY - y);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }

    private String searchDiagonallyDownForward(String word, int startX, int startY) {
        if (startX + word.length() > puzzleText.get(startY).length) {
            return "";
        }
        if (startY + word.length() > puzzleText.size()) {
            return "";
        }

        String returnVal = word + ": ";

        for (int y = 0; y < word.length(); y++) {
            String[] line = puzzleText.get(startY + y);

            if ((word.charAt(y) + "").equals(line[startX + y])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX + y, startY + y);
            } else {
                returnVal = "";
                break;
            }
        }

        return returnVal;
    }

    private String searchDiagonallyDownBackward(String word, int startX, int startY) {
        if (startX - word.length() < -1) {
            return "";
        }
        if (startY + word.length() > puzzleText.size()) {
            return "";
        }

        String returnVal = word + ": ";

        for (int y = 0; y < word.length(); y++) {
            String[] line = puzzleText.get(startY + y);

            if ((word.charAt(y) + "").equals(line[startX - y])) {
                returnVal += (returnVal.endsWith(" ")? "" : "," ) + getCoordinates(startX - y, startY + y);
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