package main.java.sk.tuke.gamestudio.game.ColorSudoku.core;
public enum CellState {
    EMPTY,
    GENERATED;
    public static CellState getCellState(int code){
        for(CellState c: CellState.values()){
            if(c.ordinal() == code)
                return c;
        }
        return null;
    }
}