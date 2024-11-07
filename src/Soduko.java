import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Soduko {

    Random rand = new Random();
    int boardSize = 4;
    int difficultLevel = 2; //Higher is harder

    public boolean generateSoduko() {

        int[][] board = new int[boardSize][boardSize];

        int value = 0;

        List<Integer> rowList = new ArrayList<>(); //Holds values in current row
        List<Integer> colList = new ArrayList<>(); //Holds values in current column
        List<Integer> matrixList = new ArrayList<>(); //Holds values in current matrix

        for (int x = 0; x < boardSize; x++) {

            for (int y = 0; y < boardSize; y++) {

                //value to add to sudoko
                value = rand.nextInt(boardSize) + 1;

                //Fill list with existing numbers for current row
                for (int valueX = 0; valueX < boardSize; valueX++) {
                    rowList.add(board[x][valueX]);
                }

                //Fill list with existing numbers for current column
                for (int valueY = 0; valueY < boardSize; valueY++) {
                    colList.add(board[valueY][y]);
                }

                //Find x-coordinate for current matrix
                int matrixCornerX = x;
                if (matrixCornerX % 2 != 0) {
                    matrixCornerX = matrixCornerX - 1;
                }

                //Find y-coordinate for current matrix
                int matrixCornerY = y;
                if (matrixCornerY % 2 != 0) {
                    matrixCornerY = matrixCornerY - 1;
                }

                //Fill list with existing numbers for current matrix
                for (int matrixX = 0; matrixX < 2; matrixX++) {
                    for (int matrixY = 0; matrixY < 2; matrixY++) {
                        int row = matrixCornerX + matrixX;
                        int col = matrixCornerY + matrixY;
                        matrixList.add(board[row][col]);
                    }
                }

                int tries = 0;
                //If current lists contain number to add, generate new number to add
                //when tried to many times, return false and start over.
                while (rowList.contains(value) || colList.contains(value) || matrixList.contains(value)) {
                    value = rand.nextInt(boardSize) + 1;
                    tries++;
                    if (tries > 50) {
                        return false;
                    }
                }

                board[x][y] = value;

                colList.clear();
                rowList.clear();
                matrixList.clear();
            }
        }

        //Solution created
        printSolution(board);

        //Blank out numbers to create puzzle
        printPuzzle(board);

        return true;

    }

    //Function for printing out solution for puzzle
    public void printSolution(int[][] board){
        System.out.println("Solution");
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                System.out.print(board[row][col] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    //Function for blanking out numbers for creating puzzle
    public void printPuzzle(int[][] board){
        System.out.println("Puzzle");
        for (int row = 0; row < boardSize; row++) {
            for (int col = 0; col < boardSize; col++) {
                int showValue = rand.nextInt(difficultLevel);
                if (showValue == 0) {
                    System.out.print(board[row][col] + " ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
    }

    public void printList(List<Integer> list){
        for (Integer integer : list) {
            System.out.print(integer + ", ");
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Soduko soduko = new Soduko();
        while (!soduko.generateSoduko()){
           //Do nothing
        }
    }


}
