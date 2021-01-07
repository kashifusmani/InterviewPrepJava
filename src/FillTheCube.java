import java.util.Arrays;

public class FillTheCube {
    public static void main(String[] args) {

       /* String[] row4 = {"C", "D", "C", "D"};
        String[] row3 = {"C", "C", "D", "C"};
        String[] row2 = {"D", "D", "D", "D"};
        String[] row1 = {"C", "D", "D", "D"};

        String[][] input = {row1, row2, row3, row4};*/

        String c = "C";
        String d = "D";

        String [] row7 = {c, d, d, c, d, d, d};
        String [] row6 = {c, d, d, c, d, d, d};
        String [] row5 = {d, d, d, d, d, d, c};
        String [] row4 = {d, c, d, c, d, d, d};
        String [] row3 = {d, d, d, c, d, c, d};
        String [] row2 = {c, d, d, c, d, c, c};
        String [] row1 = {c, d, c, d, c, c, c};

        String [][] input = {row1, row2, row3, row4, row5, row6, row7};

        //print(input);
        /*System.out.println("==");
        for(int elem: getEmptySpaces(input)) {
            System.out.print(elem);
        }
*/
        calculateMaxCubeSize(input);

    }

    public static void calculateMaxCubeSize(String[][] input) {
        String[][] rotated = input;
        int prevCubeSize = 1;
        for (int i = 0; i < 4; i++) {
            int[] emptySpaces = getEmptySpaces(rotated);
            prevCubeSize = Math.max(prevCubeSize, findCubeSize(emptySpaces));
            rotated = rotate(rotated);
        }

        System.out.println(prevCubeSize);
    }


    public static String[][] rotate(String[][] input) {
        String[][] result = new String[input.length][input.length];
        for (int i = 0; i < input.length; i++) {
            String[] row = new String[input.length];
            for (int j = 0; j < input.length; j++) {
                row[j] = input[input.length - 1 - j][i];
            }
            result[i] = row;
        }
        return result;
    }


    public static int[] getEmptySpaces(String[][] input) {
        int[] emptyInCols = new int[input.length];
        for (int i = 0; i < input.length; i++) {
            int emptyInCol = 0;
            for (int j = 0; j < input.length; j++) {
                if (input[j][i].equals("D")) {
                    emptyInCol++;
                }
            }
            emptyInCols[i] = emptyInCol;
        }
        return emptyInCols;
    }

    public static int findCubeSize(int[] spaces) {
        int retval = -1;
        while (true) {
            int max = getMax(spaces);
            if (nConsecutiveNs(spaces, max)) {
                retval = max;
                break;
            } else {
                int[] spaceCopy = new int[spaces.length];
                for (int i = 0; i < spaceCopy.length; i++) {
                    if (spaces[i] != max) {
                        spaceCopy[i] = spaces[i];
                    } else {
                        spaceCopy[i] = max - 1;
                    }
                }
                spaces = spaceCopy;
            }
        }
        return retval;
    }

    public static int getMax(int[] arr) {
        int ret = arr[0];
        for (int i = 1; i < arr.length; i++) {
            ret = Math.max(ret, arr[i]);
        }
        return ret;
    }

    public static boolean nConsecutiveNs(int[] spaces, int n) {
        if (n > 1) {
            for (int i = 0; i < spaces.length; i++) {
                if (i + n - 1 < spaces.length) {
                    boolean allSame = true;
                    int[] subArr = Arrays.copyOfRange(spaces, i, i + n);
                    for (int elem : subArr) {
                        if (elem != n) {
                            allSame = false;
                            break;
                        }
                    }
                    if (allSame) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

}
