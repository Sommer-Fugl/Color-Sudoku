package Core;


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
        //makeTransparent(newBoard);
        return newBoard;
    }
    public Cell[][]generateThriadHorizontal(Cell[][] board) {
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

    public Cell[][]generateThriadVertical(Cell[][] board) {
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

    public Cell[][]makeTransparent(Cell[][] board){
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

    public ColorCell randomColor(){//changed range
        int c = (int)(Math.random()*9);
        return ColorCell.getColor(c);
    }
    public boolean checkCell(int x, int y, Cell[][]newBoard, ColorCell colorCell){
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
    public boolean verHorFullChecker(int x, int y, Cell[][] newBoard, ColorCell colorCell ){
        for(int column = 0; column<9; column++ ){
            if(newBoard[x][column].getCurrentState() == CellState.GENERATED && newBoard[x][column].getCurrentColor() == colorCell)
                return false;
        }
        for(int row=0; row<9; row++){
            if(newBoard[row][y].getCurrentState() == CellState.GENERATED && newBoard[row][y].getCurrentColor() == colorCell)
                return false;
        }
        return true;
    }
    public boolean isAbleToSet(int colorOrdinal, int x, int y){
        if(!verHorFullChecker(x,y,getCellBoard(),ColorCell.getColor(colorOrdinal))) {
            System.out.println("You can't set the color, it's already used in Vertical/Horizontal!");
            return false;
        }

        int rangeX=findRangeX(x);
        int rangeY=findRangeY(y);

        if(!checkCell(rangeX,rangeY,getCellBoard(),ColorCell.getColor(colorOrdinal))) {
            System.out.println("You can't set the color, it's already used in cell " + rangeX +" " + rangeY);
            return false;
        }
        return true;
    }
    public int findRangeX(int x){
        if(x<3)
            return 3;
        else if(x>=3 && x<6)
            return 6;
        else if (x>=6 && x<9)
            return 9;
        return 0;
    }
    public int findRangeY(int y){
        if(y<3)
            return 3;
        else if(y>=3 && y<6)
            return 6;
        else if (y>=6 && y<9)
            return 9;
        return 0;
    }
    public Cell[][] getCellBoard() {
        return cellBoard;
    }
    public void setCellBoard(Cell[][] cellBoard) {
        this.cellBoard = cellBoard;
    }
}