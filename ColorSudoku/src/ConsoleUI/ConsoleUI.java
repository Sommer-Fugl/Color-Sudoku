package ConsoleUI;

import Core.*;

import java.util.Scanner;

public class ConsoleUI {
    private boolean playing = true;
    private boolean justStarted = true;
    public void play(){
        String row;
        String column;
        String color;
        int x=0, y=0;
        GameBoard board = new GameBoard();
        while(playing){
            if(justStarted) {
                board = StartedScreen(board);//showing start massage
                justStarted = false;
            }
            else{
                create(board);
                board = HandleInput(board);
                board.checkBoardState();
                //board.setState(GameState.SOLVED);
                if(board.getState() == GameState.SOLVED) {
                    board = askAfterGame(board);
                }
            }
        }
    }
    public void create(GameBoard board) {
        introduceColor();
        System.out.print("\u001B[95m"+"      A   B   C   D   E   F   G   H   I\n" +" \u001B[0m");
        System.out.println("\u001B[93m" +"   ————————————————————————————————————" + " \u001B[0m");
        for(int i=0; i<9; i++){
            System.out.print("\u001B[95m"+(char) ('A' + i)+" \u001B[0m" +"\u001B[93m"+" |"+" \u001B[0m");
            for(int ki=0; ki<9; ki++) {
                if(board.getCellBoard()[i][ki].getCurrentState() == CellState.EMPTY)
                    System.out.print(" " + "\u001B[93m"+"  |"+"\u001B[0m");
                else
                    System.out.print(ColorCell.colorHandler(board.getCellBoard()[i][ki].getCurrentColor().ordinal())+ "   "+"\u001B[0m" + "\u001B[93m"+"|"+"\u001B[0m");
            }
            System.out.print("\u001B[93m" +"\n   —————————————————————————————————————"+ " \u001B[0m");
            System.out.println();
        }
    }
    public void introduceColor(){
        for(int i=0; i<9; i++){
            System.out.print(ColorCell.colorHandler(i) + " " + " \u001B[0m" + " — " +i +" ");
            if(i == 4)
                System.out.println();
        }
        System.out.println();
    }
    public GameBoard HandleInput(GameBoard board){
        Scanner scanner = new Scanner(System.in);

        System.out.print("\nChoose the row: ");
        String row = scanner.nextLine();
        while (!row.matches(".*[a-iA-I].*")) {
            System.out.print("\nA-I letter needed! Choose the row: ");
            row = scanner.nextLine();
        }

        System.out.print("\nChoose the column: ");
        String column = scanner.nextLine();
        while (!column.matches(".*[a-iA-I].*")) {
            System.out.print("\nA-I letter needed! Choose the column: ");
            column = scanner.nextLine();
        }

        System.out.print("\nChoose the color: ");
        String color = scanner.nextLine();
        while(!color.matches("[0-8]+")){
            System.out.print("\nDecimal needed! Choose the color: ");
            color = scanner.nextLine();
        }

        int x = stringToInt(row);
        int y = stringToInt(column);
        int colInt = color.charAt(0)-'0';
        if(board.isAbleToSet(colInt,x,y)&& board.getCellBoard()[x][y].getCurrentState() != CellState.GENERATED){///change for score and warning message
            board.getCellBoard()[x][y] = new Cell(CellState.GENERATED, ColorCell.getColor(colInt));
        } else if (colInt > 8){
            System.out.println("You have chosen not a color! Pick another one in range 0-8");
        }
        return board;
    }

    public GameBoard askAfterGame(GameBoard board){
        create(board);
        System.out.println("You have won! Thank you for playing Color Sudoku!)");
        System.out.println("Would you like to play again the game?[y/n]");
        Scanner scanner = new Scanner(System.in);
        String sc = scanner.nextLine();
        while(!sc.matches("^[ynYN]$")){
            System.out.print("\ny/n needed!");
            sc = scanner.nextLine();
        }
        sc = sc.toLowerCase();
        if(sc.equals("y")) {
            playing = true;
            justStarted = true;
            board = new GameBoard();
        }
        else if(sc.equals("n")){
            playing = false;
            System.out.println("Thank you for playing my game!Have a good day!");
        }
        return board;
    }
    public GameBoard StartedScreen(GameBoard board){
        System.out.println("Welcome, to the Color Sudoku!");
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
        return board;
    }
    public int stringToInt(String string){
        return (int)string.toUpperCase().charAt(0)-65;
    }
}
