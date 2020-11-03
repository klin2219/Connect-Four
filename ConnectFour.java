/*
* Kevin Lin
* Lab 5
* COP 3502
 */
import java.util.*;
public class ConnectFour {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        // height and length of the board
        int p1Choice, p2Choice, rowDropped, height, length;
        char p1Token = 'x', p2Token = 'o';
        boolean condition = true, winConditionOne = false,
                winConditionTwo = false, draw = true;

        // ask for user input
        System.out.println("What would you like the height of the board to be? " );
        height = scan.nextInt();
        System.out.println("What would you like the length of the board to be? ");
        length = scan.nextInt();

        // declares the board with its dimensions
        char[][] board = new char[height][length];
        // print out the board with those dimensions
        initializeBoard(board);
        printBoard(board);
        System.out.println();

        // let players know their tokens
        System.out.println("Player 1: x");
        System.out.println("Player 2: o");
        System.out.println();

        while(condition) {

            //ask user for their input on where they want to drop their token
            System.out.println("Player 1: Which column would you like to choose? ");
            p1Choice = scan.nextInt();
            rowDropped = insertChip(board, p1Choice, p1Token);
            winConditionOne = checkIfWinner(board, p1Choice, rowDropped, p1Token);
            printBoard(board);
            System.out.println();

            if(winConditionOne){
                draw = false;
                System.out.println("Player 1 won the game!");
                break;
            }

            System.out.println("Player 2: Which column would you like to choose?" );
            p2Choice = scan.nextInt();
            rowDropped = insertChip(board, p2Choice, p2Token);
            winConditionTwo = checkIfWinner(board, p2Choice, rowDropped, p2Token);
            printBoard(board);
            System.out.println();

            if(winConditionTwo){
                draw = false;
                System.out.println("Player 2 won the game!");
                break;
            }

            if(isFull(board)){
                break;
            }

        }

        if(draw){
            System.out.println("Draw. Nobody wins.");
        }



    }

    // produces the board with the inputted dimensions
    public static void initializeBoard(char[][] array){

        // row is the height and the column is the length
        for(int row = 0; row < array.length; row++){
            for(int col = 0; col < array[row].length; col++){
                array[row][col] = '-';
            }
        }

    }

    // prints what is on the board; changes with player turns
    public static void printBoard(char[][] array){

        for(int row = 0; row < array.length; row++){
            for(int col = 0; col < array[row].length; col++){
                System.out.print(array[row][col] + " ");
            }
            System.out.println();
        }

    }

    // inserts chip into the board
    public static int insertChip(char[][] array, int col, char chipType){
        int row;

        for(row = 0; row < array.length; row++){
            /* checks if there is an "o" or "x" token in that row column
             * if there isn't set the row at that column to the user's token
             */
            if (array[array.length - 1 - row][col] == 120 || array[array.length - 1 - row][col] == 111){
                continue;
            }
            else{
                array[array.length - 1 - row][col] = chipType;
                break;
            }
        }

        return row;
    }

    // checks board if there is a winner
    public static boolean checkIfWinner(char[][] array, int col, int row, char chipType){
        boolean winner = false;
        int counter = 0;

        // vertical check
        // only rows will change from bottom down
        for(int i = array.length - 1; i > array.length - 1 - row; i--){

            // keep track of the number of consecutives, reset if non-consecutive
            if(array[i][col] == chipType){
                counter++;
            }
            else{
                counter = 0;
            }

            if(counter >= 4){
                winner = true;
            }

        }

        // horizontal check
        // only columns will change from left to right
        for(int z = 0; z < array[row].length; z++){ // length of row could not matter they are all the same

            if(array[array.length - 1 - row][z] == chipType){
                counter++;
            }
            else{ // to address non-consecutives
                counter = 0;
            }

            if(counter >= 4){
                winner = true;
            }
        }

        return winner;
    }

    // checks if board is full
    public static boolean isFull(char[][] array){
        boolean condition = true;
        for(int i = 0; i < array.length; i++){
            for(int j = 0; j < array[i].length; j++){
                if(array[i][j] == 120 || array[i][j] == 111){
                    continue;
                }
                else{
                    condition = false;
                }
            }
        }

        return condition;
    }

}
