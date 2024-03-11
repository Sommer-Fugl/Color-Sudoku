package ConsoleUI;

import Core.*;

import java.util.Scanner;


public class ConsoleUI {
    public void create(GameBoard board) {
        System.out.print("    A   B   C   D   E   F   G   H   I\n");
        System.out.println("   ———————————————————————————————————");
        for(int i=0; i<9; i++){
            System.out.print((char) ('A' + i) +" | ");
            for(int ki=0; ki<9; ki++) {
//                if(board.getCellBoard()[i][ki].getCurrentState() == CellState.EMPTY)
//                    System.out.print(" " + " | ");
//                else
                System.out.print(board.getCellBoard()[i][ki].getCurrentColor().ordinal() + " | ");
            }
            System.out.print("\n   ———————————————————————————————————");
            System.out.println();
        }
    }
    public void play(){
        String row;
        Boolean justStarted = true;
        String column;
        String color;
        int x = 0, y=0;
        GameBoard board = new GameBoard();
        if(justStarted) {
            System.out.println("Welcome, to the Color Sudoku!");
            justStarted = false;
            while(board.getGameDifficulty() == null){
                System.out.println("0 — EASY " + "1 — MEDIUM " + "2 — HARD " + "3 — EXPERT");
                System.out.print("\nChoose difficulty: ");
                Scanner sc = new Scanner(System.in);
                int difNum = sc.nextInt();
                if(difNum >-1 && difNum<4) {
                    board = new GameBoard(Difficulty.getDifficulty(difNum));
                }
                else
                    System.out.println("You've chosen wrong difficulty!It's in range 0 — 3");
            }
        }
        while(board.getState() != GameState.FAILED && board.getState() != GameState.SOLVED){
            if(!justStarted) {
                create(board);
                Scanner scanner = new Scanner(System.in);

                System.out.print("\nChoose the row: ");
                row = scanner.nextLine();
                while (!row.matches(".*[a-iA-I].*")) {
                    System.out.print("\nA-I letter needed! Choose the row: ");
                    row = scanner.nextLine();
                }

                System.out.print("\nChoose the column: ");
                column = scanner.nextLine();
                while (!column.matches(".*[a-iA-I].*")) {
                    System.out.print("\nA-I letter needed! Choose the column: ");
                    column = scanner.nextLine();
                }

                System.out.print("\nChoose the color: ");
                color = scanner.nextLine();
                while(!color.matches("[0-8]+")){
                    System.out.print("\nDecimal needed! Choose the color: ");
                    color = scanner.nextLine();
                }

                x = stringToInt(row);
                y = stringToInt(column);
                int colInt = color.charAt(0)-'0';
                if(board.isAbleToSet(colInt,x,y)&& board.getCellBoard()[x][y].getCurrentState() != CellState.GENERATED){///change for score and warning message
                    //System.out.println(11111);
                    board.getCellBoard()[x][y] = null;
                    board.getCellBoard()[x][y] = new Cell(CellState.GENERATED, ColorCell.getColor(colInt));
//                    board.getCellBoard()[x][y].setCurrentState(CellState.GENERATED);
//                    board.getCellBoard()[x][y].setCurrentColor(ColorCell.getColor(colInt));
                } else if (colInt > 8){
                    System.out.println("You have chosen a transparent color! Pick another one in range 0-8");
                }
                board.checkBoardState();
            }
        }
        System.out.println("Thank you for playing Color Sudoku!)");
    }
    public int stringToInt(String string){
        return (int)string.toUpperCase().charAt(0)-65;
    }
}
