package main.java.sk.tuke.gamestudio.game.ColorSudoku.core;

public class Cell {
    private CellState currentState;
    private ColorCell currentColor;

    public Cell(CellState newState, ColorCell newColor) {
        this.currentState = newState;
        this.currentColor = newColor;
    }

    public ColorCell getCurrentColor() {
        return currentColor;
    }

    public void setCurrentColor(ColorCell newColor) {
        if (this.currentState == CellState.GENERATED) {
            System.out.println("Chosen cell is the generated one, it is impossible to put color!");
            return;
        }
        this.currentColor = newColor;
    }

    public void fillCellWithColor(ColorCell colorCell){
        setCurrentState(CellState.GENERATED);
        setCurrentColor(colorCell);
    }

    public CellState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(CellState currentState) {
        this.currentState = currentState;
    }

}