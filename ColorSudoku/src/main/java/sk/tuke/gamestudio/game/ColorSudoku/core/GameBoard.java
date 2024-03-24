package sk.tuke.gamestudio.game.ColorSudoku.Core;

public class GameBoard {
    private Cell[][] cellBoard = new Cell[9][9];
    private GameState state;
    private Difficulty gameDifficulty;
    public GameState getState() {
        return state;
    }
    public void setState(GameState state) {
        this.state = state;
    }
    public GameBoard() {
        state = GameState.PLAYING;
    }
    public GameBoard(Difficulty difficulty){
        state = GameState.PLAYING;
        this.gameDifficulty = difficulty;
        cellBoard = generate();
    }

    public Cell[][] generate(){
        Cell[][] newBoard = new Cell[9][9];
        ColorCell colorCell = randomColor();
        while(newBoard[8][8] == null){
            for (int row = 0; row < 3; row++) {
                for (int column = 0 ; column < 3; column++) {
                    while ( !checkCell(3, 3, newBoard, colorCell)) {
                        colorCell = randomColor();
                    }
                    newBoard[row][column] = new Cell(CellState.GENERATED, colorCell);
                }
            }
            newBoard = generateThriadHorizontal(newBoard);
            newBoard = generateThriadVertical(newBoard);
        }
        makeTransparent(newBoard);
        return newBoard;
    }
    private Cell[][]generateThriadHorizontal(Cell[][] board) {
        int columnH = 3;
        for(int row = 1; row<3;row++){
            for(int column = 0; column<3; column++){
                board[0][columnH] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());//generates first row of field completely
                columnH++;
            }
        }

        columnH = 3;
        for(int row = 2; row>=0;row--){
            for(int column = 0; column<3; column++){
                if(row != 1){
                    board[1][columnH] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());//generates second row of field completely
                    columnH++;
                }
            }
        }

        columnH = 3;
        for(int row = 0; row<=1;row++){
            for(int column = 0; column<3; column++){
                board[2][columnH] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());//generates third row of field completely
                columnH++;
            }
        }
        return board;
    }

    private Cell[][]generateThriadVertical(Cell[][] board) {
        for(int column = 0; column<3; column++){ //(central-left and left-bottom) group cells 1 vertical column
            for(int row = 0; row<3; row++) {
                if(column == 1){
                    board[row + 3][0] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row + 6][2] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
                else if(column == 2){
                    board[row + 3][1] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row + 6][0] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
                else{
                    board[row+3][2] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row+6][1] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
            }
        }

        for(int column = 3; column<6; column++){ //(central-central and central-bottom) group cells 2 vertical column
            for(int row = 0; row<3; row++) {
                if(column == 3){
                    board[row + 3][4] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row + 6][5] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
                else if(column == 4){
                    board[row + 3][5] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row + 6][3] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
                else{
                    board[row+3][3] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row+6][4] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
            }
        }

        for(int column = 6; column<9; column++){ //(central-right and right-bottom) group cells 3 vertical column
            for(int row = 0; row<3; row++) {
                if(column == 6){
                    board[row + 3][7] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row + 6][8] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
                else if(column == 7){
                    board[row + 3][8] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row + 6][6] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
                else{
                    board[row+3][6] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                    board[row+6][7] = new Cell(CellState.GENERATED, board[row][column].getCurrentColor());
                }
            }
        }

        return board;
    }

    private Cell[][]makeTransparent(Cell[][] board){
        int emptyNum = (getGameDifficulty().ordinal()*15) + 15;
        int row = (int)(Math.random()*9), column =  (int)(Math.random()*9);
        for(int i = 0; i<emptyNum; i++){
            while(board[row][column].getCurrentState() != CellState.GENERATED){
                row = (int)(Math.random()*9);
                column =  (int)(Math.random()*9);
            }
            board[row][column].setCurrentState(CellState.EMPTY);
        }
        return board;
    }

    public Difficulty getGameDifficulty() {
        return gameDifficulty;
    }

    public void setGameDifficulty(Difficulty gameDifficulty) {
        this.gameDifficulty = gameDifficulty;
    }

    private ColorCell randomColor(){//changed range
        int c = (int)(Math.random()*9);
        return ColorCell.getColor(c);
    }
    private boolean checkCell(int x, int y, Cell[][]newBoard, ColorCell colorCell){
        for(int row = x-3; row<x; row++){
            for(int column = y-3; column<y; column++){
                if(newBoard[row][column]!=null && newBoard[row][column].getCurrentColor() == colorCell && newBoard[row][column].getCurrentState() == CellState.GENERATED)
                    return false;
            }
        }
        return true;
    }
    public void checkBoardState() {
        for(int row=0; row<9; row++){
            for(int column=0; column<9;column++){
                if(getCellBoard()[row][column].getCurrentState() == CellState.EMPTY)
                    return ;
            }
        }
        this.setState(GameState.SOLVED);
        return ;
    }

    public boolean isAbleToSet(int colorOrdinal, int x, int y){
        if(getCellBoard()[x][y].getCurrentColor().ordinal() != colorOrdinal)
        {
            System.out.println("You have chosen wrong color!");
            return false;
        }
        return true;
    }
    public Cell[][] getCellBoard() {
        return cellBoard;
    }
    public void setCellBoard(Cell[][] cellBoard) {
        this.cellBoard = cellBoard;
    }
}